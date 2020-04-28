package com.mafh.myregisterv2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            //requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            //MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)

            //requestPermissions(String{Manifest.permission.READ_EXTERNAL_STORAGE},)

            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            //requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 110)

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<TextInputEditText>(R.id.email_edittext)
        val password = findViewById<TextInputEditText>(R.id.password_edittext)
        val sign_in = findViewById<Button>(R.id.sign_in_button)
        val sign_up_link = findViewById<TextView>(R.id.sign_up_textview)

        sign_in.setOnClickListener {
            var em = email.text.toString()
            var pass = password.text.toString()

            if((em.isBlank() || pass.isBlank()))
            {
                Toast.makeText(this,"Please provide Email ID and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(em,pass)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                val intent = Intent(this,Add_Expenses::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK))
                                startActivity(intent)
                            }
                            else
                            {
                                return@addOnCompleteListener
                            }
                        }.addOnFailureListener {
                            Toast.makeText(this, "Sorry, Unable to Log In \n ERROR:  ${it.message}",Toast.LENGTH_SHORT).show()
                        }
            }
        }


        sign_up_link.setOnClickListener {
            val intent = Intent(this,sign_up::class.java)
            startActivity(intent)
        }



    }
}
