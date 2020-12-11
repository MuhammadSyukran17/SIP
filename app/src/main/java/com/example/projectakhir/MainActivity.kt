package com.example.projectakhir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard_admin.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavListener)
        var fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.v_fragment, HomeFragment())
        fr.commit()


    }


    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectFr: Fragment = HomeFragment()

        when(i.itemId){
            R.id.home -> {
                selectFr = HomeFragment()
            }
            R.id.location -> {
                selectFr = MapsFragment()
            }
            R.id.user -> {
                selectFr = UserFragment()
            }
        }

        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.v_fragment, selectFr)
        fr.commit()
        true
    }
}