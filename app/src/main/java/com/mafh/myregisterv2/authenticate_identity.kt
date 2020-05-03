package com.mafh.myregisterv2

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class authenticate_identity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate_identity)
        authenticate()

    }

    private fun authenticate() {
        val keyguardManager: KeyguardManager = this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val credentialsIntent: Intent = keyguardManager.createConfirmDeviceCredentialIntent("Authentication Required", "please enter your pattern to receive your token")
        if (credentialsIntent != null) {


            startActivityForResult(credentialsIntent, 1231)



        } else {
            //no password needed
            return
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1231 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Verified !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Add_Expenses::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Log.d("tagger","activity got launched?")
            Log.d("tagger","after launching password pad 2")


        } else {
            finish()

        }
    }


}
