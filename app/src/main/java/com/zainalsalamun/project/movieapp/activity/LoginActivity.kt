package com.zainalsalamun.project.movieapp.activity


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.zainalsalamun.project.movieapp.R
//import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var et_user_name = findViewById(R.id.et_user_email) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_login = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            et_user_name.setText("")
            et_password.setText("")
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        //btn_login.setOnClickListener { login() }

        fun isValidCredential(email: String, password:String): Boolean{
            return (email == "developer@coniolabs.com" && password == "Android")
        }

        fun login(){
            var email = findViewById(R.id.et_user_email) as EditText
            if(isValidCredential(email.text.toString(),et_password.text.toString())){
                Snackbar.make(btn_login,"Success Login",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show()
            }else{
                Snackbar.make(btn_login,"Invalid Credential",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show()
            }
        }


    }
}