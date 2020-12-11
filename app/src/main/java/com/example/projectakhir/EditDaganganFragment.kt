package com.example.projectakhir

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectakhir.model.Dagangan
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.FirebaseDatabase

class EditDaganganFragment : Fragment() {
    var id_dagangan: String? = ""
    var namaDagangan: String? = ""
    var namaToko: String? = ""
    var alamatToko: String? = ""
    var deskripsiDagangan: String? = ""
    var harga: String? = ""

    private lateinit var tf_namaDagangan: TextView
    private lateinit var tf_namaToko: TextView
    private lateinit var tf_deskripsi: TextView

    private val database= FirebaseDatabase.getInstance().getReference("dataDagangan")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_edit_dagangan, container, false)

        tf_namaDagangan = rootView.findViewById(R.id.juduldagangan)
        tf_namaToko = rootView.findViewById(R.id.nmtoko)
        tf_deskripsi = rootView.findViewById(R.id.deskripsi)

        id_dagangan = arguments?.getString("id_dagangan")
        namaDagangan = arguments?.getString("namaDagangan")
        namaToko = arguments?.getString("namaToko")
        alamatToko = arguments?.getString("alamatToko")
        deskripsiDagangan = arguments?.getString("deskripsiDagangan")
        harga = arguments?.getString("harga")

        tf_namaDagangan.setText("$namaDagangan - $harga")
        tf_namaToko.setText(namaToko)
        tf_deskripsi.setText(deskripsiDagangan)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<MaterialButton>(R.id.simpan_dagangan2).setOnClickListener {
            database.child(id_dagangan.toString()).removeValue()

            var fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.v_fragment_dadmin1, ListDaganganFragment())
            fr?.commit()
        }
        view.findViewById<MaterialButton>(R.id.simpan_dagangan).setOnClickListener {

            val namaDagangan = tf_namaDagangan.text.toString()
            val namaToko = tf_namaToko.text.toString()
            val deskripsi = tf_deskripsi.text.toString()
            when {
                TextUtils.isEmpty(namaDagangan) -> cekTf(tf_namaDagangan, ".")
                TextUtils.isEmpty(namaToko) -> cekTf(tf_namaToko, ".")
                TextUtils.isEmpty(deskripsi) -> cekTf(tf_deskripsi, ".")
                else -> {
                    database.child(id_dagangan.toString()).setValue(
                        Dagangan(
                            id_dagangan,
                            namaDagangan,
                            namaToko,
                            deskripsi,
                            "", ""
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

    private fun cekTf(tf: TextView, msg: String){
        tf.error = "${msg} tidak boleh kosong"
        tf.requestFocus()
    }
    private fun cleanTf(){
        tf_namaDagangan.text = null
        tf_namaToko.text = null
        tf_deskripsi.text = null
    }

}

