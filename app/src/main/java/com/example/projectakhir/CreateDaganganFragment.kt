package com.example.projectakhir

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectakhir.model.Dagangan
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase

class CreateDaganganFragment : Fragment() {
    var inputText: String? = ""

    private lateinit var tf_namaDagangan: TextInputLayout
    private lateinit var tf_namaToko: TextInputLayout
    private lateinit var tf_harga: TextInputLayout
    private lateinit var tf_deskripsi: TextInputLayout

    private val database= FirebaseDatabase.getInstance().getReference("dataDagangan")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_create_dagangan, container, false)

        tf_namaDagangan = rootView.findViewById(R.id.nama_dag)
        tf_namaToko = rootView.findViewById(R.id.namatok)
        tf_deskripsi = rootView.findViewById(R.id.keterangan)
        tf_harga = rootView.findViewById(R.id.harga)

        inputText = arguments?.getString("input_txt")

        Log.d("test", "${inputText}")

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<MaterialButton>(R.id.simpan_dagangan).setOnClickListener {

            val namaDagangan = tf_namaDagangan.editText?.text.toString()
            val namaToko = tf_namaToko.editText?.text.toString()
            val deskripsi = tf_deskripsi.editText?.text.toString()
            val harga = tf_harga.editText?.text.toString()
            when {
                TextUtils.isEmpty(namaDagangan) -> cekTf(tf_namaDagangan, ".")
                TextUtils.isEmpty(namaToko) -> cekTf(tf_namaToko, ".")
                TextUtils.isEmpty(deskripsi) -> cekTf(tf_deskripsi, ".")
                TextUtils.isEmpty(harga) -> cekTf(tf_harga, ".")
                else -> {
                    val key = database.push().key
                    database.child(key.toString()).setValue(
                        Dagangan(
                            key.toString(),
                            namaDagangan,
                            namaToko,
                            deskripsi,
                            "",
                            ""
                        )
                    ).addOnSuccessListener(OnSuccessListener {
                        cleanTf()

                        var fr = fragmentManager?.beginTransaction()
                        fr?.replace(R.id.v_fragment_dadmin1, ListDaganganFragment())
                        fr?.commit()

                        activity!!.findViewById<TextView>(R.id.select)?.animate()!!.x(Float.MIN_VALUE).duration = 100;
                        activity!!.findViewById<TextView>(R.id.item2).setTextColor(activity!!.findViewById<TextView>(R.id.item1).textColors)
                        activity!!.findViewById<TextView>(R.id.select).setTextColor(Color.WHITE)

                        Toast.makeText(activity, "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    }).addOnFailureListener {
                        Toast.makeText(activity, "Data gagal disimpan", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private fun cekTf(tf: TextInputLayout, msg: String){
        tf.editText?.error = "${msg} tidak boleh kosong"
        tf.requestFocus()
    }
    private fun cleanTf(){
        tf_namaDagangan.editText!!.text = null
        tf_namaToko.editText!!.text = null
        tf_deskripsi.editText!!.text = null
        tf_harga.editText!!.text = null
    }

}

