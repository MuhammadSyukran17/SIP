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

class DaganganAdapter(
    val context: Context,
    var clickListner: OnDaganganItemClickListner): RecyclerView.Adapter<DaganganAdapter.DaganganViewHolder>(){

    private val dagangan: MutableList<Dagangan> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaganganViewHolder {
        return DaganganViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dagangan, parent, false))
    }

    override fun onBindViewHolder(holder: DaganganAdapter.DaganganViewHolder, position: Int) {
        holder.binmodel(dagangan[position], clickListner)
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

        val txtNamaDagangan: TextView = item.findViewById(R.id.nmdagangan)
//        val txtNamaToko: TextView = item.findViewById(R.id.lokasidagangan)
        val txtDeskripsiDagangan: TextView = item.findViewById(R.id.deskripsidag)
//        val txtHarga: TextView = item.findViewById(R.id.tv_harga)
        val txtLokasiToko: TextView = item.findViewById(R.id.lokasidagangan)

        fun binmodel (buku: Dagangan, action:OnDaganganItemClickListner){
            txtNamaDagangan.text = buku.getNamaDagangan()
//            txtNamaPengarang.text = buku.getNamaPengerangBuku()
            txtDeskripsiDagangan.text = buku.getDeskripsiDagangan()
//            txtHarga.text = buku.getHarga()
            txtLokasiToko.text = buku.getAlamatToko()
            itemView.findViewById<MaterialCardView>(R.id.databuk).setOnClickListener{
                action.onItemClick(buku,adapterPosition)
            }
        }
    }
}
interface OnDaganganItemClickListner{
    fun onItemClick(item: Dagangan, position: Int)
}