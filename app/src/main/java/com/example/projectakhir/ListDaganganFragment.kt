package com.example.projectakhir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectakhir.adapter.DaganganAdapterAdmin
import com.example.projectakhir.adapter.OnclickDaganganAdmin
import com.example.projectakhir.model.Dagangan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_list_dagangan.*


class ListDaganganFragment : Fragment(), OnclickDaganganAdmin {
    lateinit var daganganAdapter: DaganganAdapterAdmin
    lateinit var comm: Communicator

    val lm = LinearLayoutManager(activity)
    val addDaganganList: MutableList<Dagangan> = mutableListOf()

    private val database= FirebaseDatabase.getInstance().getReference("dataDagangan")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_dagangan, container, false)

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        rc_dagangan_admin.layoutManager = lm
        daganganAdapter = DaganganAdapterAdmin(context!!,this)
        rc_dagangan_admin.adapter = daganganAdapter
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (h in snapshot.children) {
                    addDaganganList.add(h.getValue(Dagangan::class.java)!!)
                }
                daganganAdapter.setDagangan(addDaganganList)
            }
            override fun onCancelled(error: DatabaseError) {}
        })

    }

    override fun onItemClick(item: Dagangan, position: Int) {
        val bundle = Bundle()
        bundle.putString("id_dagangan",item.id)
        bundle.putString("namaDagangan",item.getNamaDagangan())
        bundle.putString("namaToko",item.getNamaToko())
        bundle.putString("alamatToko",item.getAlamatToko())
        bundle.putString("deskripsiDagangan",item.getDeskripsiDagangan())
        bundle.putString("harga",item.getHarga())

        val transaction = fragmentManager?.beginTransaction()
        val frag2 = EditDaganganFragment()
        frag2.arguments = bundle

        transaction?.replace(R.id.v_fragment_dadmin1, frag2)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

}