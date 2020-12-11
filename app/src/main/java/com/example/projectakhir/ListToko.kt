package com.example.projectakhir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list_dagangan.*

class ListToko : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_toko)

        findViewById<ImageButton>(R.id.imageButton1).setOnClickListener(this)
        findViewById<TextView>(R.id.item1).setOnClickListener(this)
        findViewById<TextView>(R.id.item2).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageButton1 -> { startActivity(Intent(this, DashboardAdmin::class.java)) }
        }
    }
}