package com.example.projectakhir.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhir.R
import com.example.projectakhir.model.Dagangan
import com.google.android.material.card.MaterialCardView

class DaganganAdapterAdmin(
        val context: Context,
        var clickListner: OnclickDaganganAdmin
    ):
        RecyclerView.Adapter<DaganganAdapterAdmin.DaganganViewHolder>(){

    private val dagangan: MutableList<Dagangan> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaganganViewHolder {
        return DaganganViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dagangan_admin, parent, false))
    }

    override fun onBindViewHolder(holder: DaganganAdapterAdmin.DaganganViewHolder, position: Int) {
        holder.binmodel(dagangan[position],clickListner)
    }

    override fun getItemCount(): Int {
        return dagangan.size
    }

    fun setDagangan(data: List<Dagangan>){
        dagangan.clear()
        dagangan.addAll(data)
        notifyDataSetChanged()
    }

    inner class DaganganViewHolder(item: View): RecyclerView.ViewHolder(item){

        val txtNamaDagangan: TextView = item.findViewById(R.id.namadagad)
        val txtNamaToko: TextView = item.findViewById(R.id.namatokad)
        val txtDeskripsi: TextView = item.findViewById(R.id.tv_deskripsi)
        val txtLokasi: TextView = item.findViewById(R.id.lokasiadmin)

        fun binmodel (dagangan: Dagangan, action:OnclickDaganganAdmin){
            txtNamaDagangan.text = "${dagangan.getNamaDagangan()} - ${dagangan.getHarga()}"
            txtNamaToko.text = dagangan.getNamaToko()
            txtDeskripsi.text = dagangan.getDeskripsiDagangan()
            txtLokasi.text = dagangan.getAlamatToko()
            itemView.findViewById<MaterialCardView>(R.id.datadaganganadmin).setOnClickListener{
                action.onItemClick(dagangan,adapterPosition)
            }
        }

    }
}
interface OnclickDaganganAdmin{
    fun onItemClick(item: Dagangan, position: Int)
}