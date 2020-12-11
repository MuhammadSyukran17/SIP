package com.example.projectakhir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailDaganganFrament : Fragment() {
    var id_dagangan: String? = ""
    var namaDagangan: String? = ""
    var namaToko: String? = ""
    var alamatToko: String? = ""
    var deskripsiDagangan: String? = ""
    var harga: String? = ""
    private lateinit var tf_namaDagangan: TextView
    private lateinit var tf_namaToko: TextView
    private lateinit var tf_deskripsi: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.detail_dagangan, container, false)

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
}