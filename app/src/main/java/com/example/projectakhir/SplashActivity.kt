package com.example.projectakhir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.projectakhir.adapter.DaganganAdapter

class SplashActivity : AppCompatActivity() {


    lateinit var topAnim: Animation
    lateinit var bottomAnim: Animation
    lateinit var image: ImageView
    lateinit var namatoko: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation )
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation )

        image = findViewById(R.id.bookstore)
        namatoko = findViewById(R.id.namatoko)

        image.animation = topAnim
        namatoko.animation = bottomAnim

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}