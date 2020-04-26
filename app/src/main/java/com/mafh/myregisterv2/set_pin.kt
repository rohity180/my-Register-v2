package com.mafh.myregisterv2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class set_pin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)

        val setpin_1 = findViewById<EditText>(R.id.set_pin)
        val setpin_2 = findViewById<EditText>(R.id.set_pin_confirm)

        val button_setpin = findViewById<Button>(R.id.button_set_pin)

        val sp = getSharedPreferences("my_db", Context.MODE_PRIVATE)
        val e = sp.edit()

        button_setpin.setOnClickListener {
            if (setpin_1.text.toString().length == 4 && setpin_1.text.toString().length == 4)
            {

                if(setpin_1.text.toString()==setpin_2.text.toString())
                {
                    e.putString("PIN",setpin_1.text.toString())
                    e.commit()
                    var pin_value = sp.getString("PIN","ERROR")
                    Log.d("pin",pin_value)

                    // Move to Next Activity here


                }

            }
            else
            {
                Toast.makeText(this,"Entered Values Do Not Match, Please Try Again",Toast.LENGTH_SHORT).show()
                setpin_1.text.clear()
                setpin_2.text.clear()
            }
        }
    }
}
