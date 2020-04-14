package com.mafh.myregisterv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Add_Expenses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__expenses)

        val label1=findViewById<LinearLayout>(R.id.layout_label_1)
        val container1=findViewById<LinearLayout>(R.id.layout_container_1)
        val label2=findViewById<LinearLayout>(R.id.layout_label_2)
        val container2=findViewById<LinearLayout>(R.id.layout_container_2)
        val label3=findViewById<LinearLayout>(R.id.layout_label_3)
        val container3=findViewById<LinearLayout>(R.id.layout_container_3)


        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = this.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        label1.setBackgroundResource(backgroundResource)
        label2.setBackgroundResource(backgroundResource)
        label3.setBackgroundResource(backgroundResource)
        container1.visibility= View.VISIBLE
        container2.visibility= View.GONE
        container3.visibility= View.GONE
        label1.setOnClickListener {

            if (container1.isShown)
                container1.visibility= View.GONE
            else
                container1.visibility= View.VISIBLE



        }
        label2.setOnClickListener {

            if (container2.isShown)
                container2.visibility= View.GONE
            else
                container2.visibility= View.VISIBLE



        }
        label3.setOnClickListener {

            if (container3.isShown)
                container3.visibility= View.GONE
            else
                container3.visibility= View.VISIBLE


        }
    }

    override fun onDestroy(){
        super.onDestroy()
        Toast.makeText(this,"Bye Bye App", Toast.LENGTH_SHORT).show()

    }

    class book_pages(media:String, f_r_n:String, party_num:String, packers_name:String, cli_name:String, quote_no:Int, s_d: Date, frm:String, to:String, labour_name:String, white_bag:Int, str_film:Int, roll:Int, tape:Int, rassi:Int, bubble:Int, news_papers:Int, crate_plastic:Int, blanket:Int, cartoon:Int, ac_mech_name:String, ac_mech_payment:Int, carpenter_name:String, carpenter_payment:Int, labour_charges:Int, vehicle_no:String, vehicle_charges:Int, diesel:Int, police:Int, domestic_charges:Int, b_a_t_fare:Int, goods_damage_less_pay:Int, quote_amont:Int, total_expenses:Int, exec_adv:Int, balance:Int)

    {
        var media:String?=""
        var f_r_n:String?=""
        var party_num:String?=""
        var packers_name:String?=""
        var cli_name:String?=""
        var quote_no:Int?=0
        var s_d: Date?= SimpleDateFormat("dd/mm/year").parse("01/01/2019")
        var frm:String?=""
        var to:String?=""
        var labour_name:String?=""
        var white_bag:Int?=0
        var str_film:Int?=0
        var roll:Int?=0
        var tape:Int?=0
        var rassi:Int?=0
        var bubble:Int?=0
        var news_papers:Int?=0
        var crate_plastic:Int?=0
        var blanket:Int?=0
        var cartoon:Int?=0
        var ac_mech_name:String?=""
        var ac_mech_payment:Int?=0
        var carpenter_name:String?=""
        var carpenter_payment:Int?=0
        var labour_charges:Int?=0
        var vehicle_no:String?=""
        var vehicle_charges:Int?=0
        var diesel:Int?=0
        var police:Int?=0
        var domestic_charges:Int?=0
        var b_a_t_fare:Int?=0
        var goods_damage_less_pay:Int?=0
        var quote_amount:Int?=0
        var total_expenses:Int?=0
        var exec_adv:Int?=0
        var balance:Int?=0

        /*fun book_pages(media:String,f_r_n:String,party_num:String,packers_name:String,cli_name:String,quote_no:Int,s_d:Date,from:String,to:String, labour_name:String, white_bag:Int, str_film:Int, roll:Int, tape:Int,rassi:Int, bubble:Int,news_papers:Int, crate_plastic:Int, blanket:Int,cartoon:Int,ac_mech_name:String,ac_mech_payment:Int,carpenter_name:String,carpenter_payment:Int,labour_charges:Int, vehicle_no:String,vehicle_charges:Int,diesel:Int,police:Int,domestic_charges:Int, b_a_t_fare:Int,goods_damage_less_pay:Int,quote_amount:Int,total_expenses:Int,exec_adv:Int,balance:Int)
        {

        }*/

        /*var media:String*/

        init {

            this.media=media
            this.f_r_n=f_r_n
            this.party_num=party_num
            this.packers_name=packers_name
            this.cli_name=cli_name
            this.quote_no=quote_no
            this.s_d=s_d
            this.frm=frm
            this.to=to
            this.labour_name=labour_name
            this.white_bag=white_bag
            this.str_film=str_film
            this.roll=roll
            this.tape=tape
            this.rassi=rassi
            this.bubble=bubble
            this.news_papers=news_papers
            this.crate_plastic=crate_plastic
            this.blanket=blanket
            this.cartoon=cartoon
            this.ac_mech_name=ac_mech_name
            this.ac_mech_payment=ac_mech_payment
            this.carpenter_name=carpenter_name
            this.carpenter_payment=carpenter_payment
            this.labour_charges=labour_charges
            this.vehicle_no=vehicle_no
            this.vehicle_charges=vehicle_charges
            this.diesel=diesel
            this.police=police
            this.domestic_charges=domestic_charges
            this.b_a_t_fare=b_a_t_fare
            this.goods_damage_less_pay=goods_damage_less_pay
            this.quote_amount=quote_amount
            this.total_expenses=total_expenses
            this.exec_adv=exec_adv
            this.balance=balance

        }

    }

}

