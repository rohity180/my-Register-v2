package com.mafh.myregisterv2

import android.Manifest
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login_checker_blank_activity : Activity() {

    private val CREDENTIALS_RESULT = 4342
     var checker:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


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


            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }


        check_login_status()

        Log.d("tagger","after launching password pad 4")


        setContentView(R.layout.activity_login_checker_blank_activity)


    }

    private fun check_login_status()
    {
        val uid = FirebaseAuth.getInstance().uid

        if (uid == null)
        {
            val intent = Intent(this,login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        if (uid != null)
        {

            //Toast.makeText(this,"Verified !!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Add_Expenses::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Log.d("tagger","activity got launched?")
            Log.d("tagger","after launching password pad 2")
            finish()

            /*val keyguardManager: KeyguardManager = this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            val credentialsIntent:Intent = keyguardManager.createConfirmDeviceCredentialIntent("Authentication Required", "please enter your pattern to receive your token")
            if (credentialsIntent != null) {



                startActivityForResult(credentialsIntent, CREDENTIALS_RESULT)




            }
            else {
                //no password needed
                return
            }*/

            //check_credentials()
            //Log.d("checker", "Value of checker: $checker")

            /*if (checker) {

            val intent = Intent(this,Add_Expenses::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

            }*/


        }

    }


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==CREDENTIALS_RESULT && resultCode== Activity.RESULT_OK ){



            Toast.makeText(this,"Verified !!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Add_Expenses::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Log.d("tagger","activity got launched?")
            Log.d("tagger","after launching password pad 2")
            finish()



        }


        if(requestCode==CREDENTIALS_RESULT && resultCode== Activity.RESULT_CANCELED ) {

            finish()
            Log.d("tagger","after launching password pad 3" )

        }*/





    }


