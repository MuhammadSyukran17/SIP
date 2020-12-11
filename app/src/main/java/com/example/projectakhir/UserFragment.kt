package com.example.projectakhir

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regis.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.v_fragment, Regis())
            fr?.commit()
        }

        auth = FirebaseAuth.getInstance()


        login.setOnClickListener {
            val Emaillog = emailLog.text.toString().trim()
            val Passwordlog = paslog.text.toString().trim()

//            if (Emaillog.isEmpty()){
//                emailLog.error = "Email harus diisi"
//                return@setOnClickListener
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(Emaillog).matches()){
//                emailLog.error = "Email tidak valid"
//                emailLog.requestFocus()
//                return@setOnClickListener
//            }
//
//            if (Passwordlog.isEmpty() || paslog.length() < 6){
//                paslog.error = "Password harus lebih dari 6 karakter"
//                paslog.requestFocus()
//                return@setOnClickListener
//            }

            loginAdmin(Emaillog, Passwordlog)
        }


    }

    private fun loginAdmin(Emaillog: String, Passwordlog: String) {
        auth.signInWithEmailAndPassword(Emaillog, Passwordlog)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(activity, DashboardAdmin::class.java)
                    startActivity(intent)
                }else{
                        Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}