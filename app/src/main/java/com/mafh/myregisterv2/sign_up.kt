package com.mafh.myregisterv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class sign_up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val email = findViewById<TextInputEditText>(R.id.email_edittext)
        val password = findViewById<TextInputEditText>(R.id.password_edittext)
        val confirm_password = findViewById<TextInputEditText>(R.id.confirm_password_edittext)
        val sign_up = findViewById<Button>(R.id.sign_up_button)
        val login_link = findViewById<TextView>(R.id.sign_in_textview)

        sign_up.setOnClickListener {
            var em = email.text.toString()
            var pass = password.text.toString()
            var conf_pass = confirm_password.text.toString()
            if(em.isBlank() || pass.isBlank() || conf_pass.isBlank())
            {
                if(em.isBlank())
                {
                    Toast.makeText(this,"Please Enter the Email",Toast.LENGTH_SHORT).show()
                }
                if(pass.isBlank())
                {
                    Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_SHORT).show()
                }
                if(conf_pass.isBlank())
                {
                    Toast.makeText(this,"You need to Confirm the Password",Toast.LENGTH_SHORT).show()
                }

                return@setOnClickListener
            }
            if(!em.isBlank() && pass.equals(conf_pass))
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(em,pass)
                        .addOnCompleteListener{
                            if (it.isSuccessful)
                            {
                                Toast.makeText(this,"User Got created UID is ${it.result?.user?.uid}",Toast.LENGTH_SHORT).show()
                                log_in(em,pass)
                            }
                            else
                            {
                                return@addOnCompleteListener
                            }
                        }.addOnFailureListener {
                            Toast.makeText(this,"Unable to create User \n Error:  ${it.message}",Toast.LENGTH_SHORT).show()
                        }
            }
        }


        login_link.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun log_in(em:String, pass:String)
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(em,pass)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val intent = Intent(this,Add_Expenses::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else
                    {
                        return@addOnCompleteListener
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Sorry, Unable to Log In \n ERROR:  ${it.message} \n Please Try Again....",Toast.LENGTH_SHORT).show()
                }
    }
}
