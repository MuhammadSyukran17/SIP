package com.example.projectakhir

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.registrasi.*
import java.util.regex.Pattern

class Regis : Fragment() {
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.registrasi, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.v_fragment, UserFragment())
            fr?.commit()

        }

        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val fullname = Fullname.text.toString().trim()
            val Username = username.text.toString().trim()
            val Email = email.text.toString().trim()
            val Password = pass.text.toString().trim()

            if (fullname.isEmpty() || Fullname.length() < 6){
                Fullname.error = "Full Name harus lebih dari 6 karakter"
                Fullname.requestFocus()
                return@setOnClickListener
            }

            if (Username.isEmpty() || username.length() < 6){
                username.error = "username harus lebih dari 6 karakter"
                username.requestFocus()
                return@setOnClickListener
            }

            if (Email.isEmpty()){
                email.error = "Email harus diisi"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                email.error = "Email tidak valid"
                email.requestFocus()
                return@setOnClickListener
            }

            if (Password.isEmpty() || pass.length() < 6){
                pass.error = "Password harus lebih dari 6 karakter"
                pass.requestFocus()
                return@setOnClickListener
            }

            registerAdmin(fullname, Username,Email, Password)
        }

    }

    private fun registerAdmin(fullname : String, Username : String, Email : String, Password : String){
        auth.createUserWithEmailAndPassword(Email, Password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    var fr = fragmentManager?.beginTransaction()
                    fr?.add(R.id.v_fragment_dadmin1, UserFragment())
                    fr?.commit()
                }
                else{
                    Toast.makeText(activity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}