package com.example.projectakhir

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectakhir.adapter.DaganganAdapter
import com.example.projectakhir.adapter.OnDaganganItemClickListner
import com.example.projectakhir.model.Dagangan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), OnDaganganItemClickListner {
    lateinit var search_dagang: EditText
    lateinit var daganganAdapter: DaganganAdapter
    val lm = LinearLayoutManager(activity)
    val addDaganganList: MutableList<Dagangan> = ArrayList()

    private val database= FirebaseDatabase.getInstance().getReference("dataDagangan")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root_view = inflater.inflate(R.layout.fragment_home, container, false)
        search_dagang = root_view.findViewById(R.id.search_dagang)
        return root_view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        search_dagang.addTextChangedListener (object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

        })
    }

    private fun filterList(filterItem: String) {
        var tempList: MutableList<Dagangan> = ArrayList()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (h in snapshot.children) {
                    if (filterItem in h.getValue(Dagangan::class.java)!!.getNamaDagangan()){
                        tempList.add(h.getValue(Dagangan::class.java)!!)
                    }
                }
                daganganAdapter.setDagangan(tempList)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun initView() {
        rc_dagangan.layoutManager = lm
        daganganAdapter = DaganganAdapter(context!!,this)
        rc_dagangan.adapter = daganganAdapter
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
        val frag2 = DetailDaganganFrament()
        frag2.arguments = bundle

        transaction?.replace(R.id.v_fragment, frag2)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }


}