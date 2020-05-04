package com.mafh.myregisterv2

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class Add_Expenses : AppCompatActivity() {


    val image_upload_list = arrayListOf<Uri>()
    //val image_grid = findViewById<GridLayout>(R.id.images_grid)

    override fun onCreate(savedInstanceState: Bundle?) {

        //check_login_status()

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


        //check_login_status()

        setContentView(R.layout.activity_add__expenses)
        authenticate()
        //finishActivity(1231)

        var calendar = Calendar.getInstance()
        var Year = calendar.get(Calendar.YEAR)
        var Month = calendar.get(Calendar.MONTH)
        var Day = calendar.get(Calendar.DAY_OF_MONTH)


        val shifting_date = findViewById<TextInputEditText>(R.id.shifting_date)

        var date = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateEditbox(calendar, shifting_date)
        }



        shifting_date.setOnClickListener {

            DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

        }


        val nxt_btn = findViewById<Button>(R.id.next_btn)
        val label1 = findViewById<LinearLayout>(R.id.layout_label_1)
        val container1 = findViewById<LinearLayout>(R.id.layout_container_1)
        val label2 = findViewById<LinearLayout>(R.id.layout_label_2)
        val container2 = findViewById<LinearLayout>(R.id.layout_container_2)
        val label3 = findViewById<LinearLayout>(R.id.layout_label_3)
        val container3 = findViewById<LinearLayout>(R.id.layout_container_3)
        val arrow_1 = findViewById<ImageView>(R.id.label_1_arrow)
        val arrow_2 = findViewById<ImageView>(R.id.label_2_arrow)
        val arrow_3 = findViewById<ImageView>(R.id.label_3_arrow)
        val add_image = findViewById<ImageButton>(R.id.add_image)
        val entry_date = findViewById<TextInputEditText>(R.id.entry_date)

        var date2 = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateEditbox(calendar, entry_date)

        }


        var temp_sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        var today_date = temp_sdf.format(Date())
        entry_date.setText(today_date)

        entry_date.setOnClickListener {
            DatePickerDialog(this, date2, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }


        //val matrix = Matrix()


        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = this.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        label1.setBackgroundResource(backgroundResource)
        label2.setBackgroundResource(backgroundResource)
        label3.setBackgroundResource(backgroundResource)
        container1.visibility = View.VISIBLE


        //arrow_1.setScaleType(ImageView.ScaleType.MATRIX)
        //matrix.postRotate((180).toFloat(), (arrow_1.drawable.bounds.width()/2).toFloat(), (arrow_1.drawable.bounds.height()/2).toFloat())
        //arrow_1.imageMatrix=matrix
        //arrow_1.rotation=180f
        arrow_1.animate().rotation(180f).setDuration(300).start()
        container2.visibility = View.GONE
        container3.visibility = View.GONE


        label1.setOnClickListener {

            if (container1.isShown) {
                container1.visibility = View.GONE
                arrow_1.animate().rotation(360f).setDuration(400).start()
                //label1.setBackgroundResource(R.color.white)
            } else {
                container1.visibility = View.VISIBLE
                //arrow_1.rotation=180f
                arrow_1.animate().rotation(180f).setDuration(400).start()
                //label1.setBackgroundResource(R.color.colorPrimaryDark)
            }


        }
        label2.setOnClickListener {

            if (container2.isShown) {
                container2.visibility = View.GONE
                //arrow_2.rotation=0f
                arrow_2.animate().rotation(360f).setDuration(400).start()
                //label2.setBackgroundResource(R.color.white)
            } else {
                container2.visibility = View.VISIBLE
                //arrow_2.rotation=180f
                arrow_2.animate().rotation(180f).setDuration(400).start()
                //label2.setBackgroundResource(R.color.colorPrimaryDark)
            }


        }

        val btn_back = findViewById<Button>(R.id.back_btn)
        btn_back.setOnClickListener {
            /*val intent= Intent(this,sign_up::class.java)
            startActivity(intent)*/
            Toast.makeText(this, "You are Signed Out Successfully", Toast.LENGTH_SHORT)
            FirebaseAuth.getInstance().signOut()
            check_login_status()


        }



        label3.setOnClickListener {

            if (container3.isShown) {
                container3.visibility = View.GONE
                //arrow_3.rotation=0f
                arrow_3.animate().rotation(360f).setDuration(400).start()
                //label3.setBackgroundResource(R.color.white)
            } else {
                container3.visibility = View.VISIBLE
                //arrow_3.rotation=180f
                arrow_3.animate().rotation(180f).setDuration(400).start()
                //label3.setBackgroundResource(R.color.colorPrimaryDark)
            }


        }
        nxt_btn.setOnClickListener {
            val intent = Intent(this, viewDataTabs::class.java)
            startActivity(intent)
        }



        add_image.setOnClickListener {
            Toast.makeText(this, "Select an Image to add !! ", Toast.LENGTH_SHORT).show()
            selectImage(this)
            Toast.makeText(this, "Image got Added !!!!", Toast.LENGTH_SHORT).show()
        }


        //Data Preparation Part Here
        val entry_date_et = findViewById<TextInputEditText>(R.id.entry_date)
        val media_et = findViewById<TextInputEditText>(R.id.media)
        val frn_et = findViewById<TextInputEditText>(R.id.f_r_n)
        val party_no_et = findViewById<TextInputEditText>(R.id.party_numbers)
        val client_name_et = findViewById<TextInputEditText>(R.id.client_name)
        val quotation_number_et = findViewById<TextInputEditText>(R.id.quotation_number)
        val shifting_date_et = findViewById<TextInputEditText>(R.id.shifting_date)
        val from_et = findViewById<TextInputEditText>(R.id.from)
        val to_et = findViewById<TextInputEditText>(R.id.to)

        //Labours and Items

        val labour_name_et = findViewById<TextInputEditText>(R.id.labour_name)
        val white_bag_q_et = findViewById<TextInputEditText>(R.id.white_bag_quantity)
        val white_bag_r_et = findViewById<TextInputEditText>(R.id.white_bag_rate)
        val stretch_film_q_et = findViewById<TextInputEditText>(R.id.stretch_film_quantity)
        val stretch_film_r_et = findViewById<TextInputEditText>(R.id.stretch_film_rate)
        val roll_q_et = findViewById<TextInputEditText>(R.id.roll_quantity)
        val roll_r_et = findViewById<TextInputEditText>(R.id.roll_rate)
        val tape_q_et = findViewById<TextInputEditText>(R.id.tape_quantity)
        val tape_r_et = findViewById<TextInputEditText>(R.id.tape_rate)
        val rassi_q_et = findViewById<TextInputEditText>(R.id.rassi_quantity)
        val rassi_r_et = findViewById<TextInputEditText>(R.id.rassi_rate)
        val bubble_q_et = findViewById<TextInputEditText>(R.id.bubble_quantity)
        val bubble_r_et = findViewById<TextInputEditText>(R.id.bubble_rate)
        val newspaper_q_et = findViewById<TextInputEditText>(R.id.newspapers_quantity)
        val newspaper_r_et = findViewById<TextInputEditText>(R.id.newspapers_rate)
        val crate_q_et = findViewById<TextInputEditText>(R.id.crate_plastic_quantity)
        val crate_r_et = findViewById<TextInputEditText>(R.id.crate_plastic_rate)
        val blanket_q_et = findViewById<TextInputEditText>(R.id.blanket_quantity)
        val blanket_r_et = findViewById<TextInputEditText>(R.id.blanket_rate)
        val cartoon_q_et = findViewById<TextInputEditText>(R.id.cartoon_quantity)
        val cartoon_r_et = findViewById<TextInputEditText>(R.id.cartoon_rate)

        val white_bag_et = findViewById<TextInputEditText>(R.id.white_bag_total)
        val str_film_et = findViewById<TextInputEditText>(R.id.stretch_film_total)
        val roll_et = findViewById<TextInputEditText>(R.id.roll_total)
        val tape_et = findViewById<TextInputEditText>(R.id.tape_total)
        val rassi_et = findViewById<TextInputEditText>(R.id.rassi_total)
        val bubble_et = findViewById<TextInputEditText>(R.id.bubble_total)
        val newspaper_et = findViewById<TextInputEditText>(R.id.newspapers_total)
        val crate_et = findViewById<TextInputEditText>(R.id.crate_plastic_total)
        val blanket_et = findViewById<TextInputEditText>(R.id.blanket_total)
        val cartoon_et = findViewById<TextInputEditText>(R.id.cartoon_total)
        
        white_bag_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,white_bag_q_et,white_bag_r_et,white_bag_et) }
        str_film_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,stretch_film_q_et,stretch_film_r_et,str_film_et) }
        roll_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,roll_q_et,roll_r_et,roll_et) }
        tape_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,tape_q_et,tape_r_et,tape_et)  }
        rassi_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,rassi_q_et,rassi_r_et,rassi_et)  }
        bubble_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,bubble_q_et,bubble_r_et,bubble_et)  }
        newspaper_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,newspaper_q_et,newspaper_r_et,newspaper_et)  }
        crate_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,crate_q_et,crate_r_et,crate_et) }
        blanket_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,blanket_q_et,blanket_r_et,blanket_et) }
        cartoon_et.setOnFocusChangeListener { v, hasFocus -> calculate_price(hasFocus,cartoon_q_et,cartoon_r_et,cartoon_et) }




    }


    private fun calculate_price(hasFocus:Boolean,quantity:TextInputEditText , rate:TextInputEditText , total:TextInputEditText)
    {
        Log.d("toucher","value of hasfocus: $hasFocus")
        if(hasFocus) {
            Log.d("toucher","crossed first if")
            if (quantity.text.toString().isBlank() || rate.text.toString().isBlank()) {
                Toast.makeText(this, "Blank or Invalid values, please check", Toast.LENGTH_SHORT).show()
                Log.d("toucher","Quantity: ${quantity.text} and rate: ${rate.text}")
                return
            }
            try {
                val q = (quantity.text.toString()).toInt()
                val r = (rate.text.toString()).toInt()
                total.setText((q*r).toString())

            }
            catch (e: Exception)
            {
                Toast.makeText(this, "Invalid values, please check \n ${e.message}", Toast.LENGTH_SHORT).show()
            }

        }
        else
        {
            Log.d("toucher","It came here")
        }
    }

    private fun selectImage(context: Context) {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 1)

        /*val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose your profile picture")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, 0)
            } else if (options[item] == "Choose from Gallery") {
                val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, 1)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1231) {
            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(this, "Verified !", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("nexa", "I am also being called")
                finish()

            }
        }


        /*if (requestCode==0 && resultCode== Activity.RESULT_OK && data != null)
        {
            var uri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri)
            Log.d("Image",uri.toString())
        }*/
        /*if (requestCode==1 && resultCode== Activity.RESULT_OK && data != null)
        {
            var uri = data.data
            //val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri)
            Log.d("Image",uri.toString())
            image_upload_list.add(uri)
            Log.d("Image2",image_upload_list.toString())

            val layout = findViewById<FlexboxLayout>(R.id.flexLayout)
            val linear_layout = LinearLayout(this)
            linear_layout.layoutParams = ViewGroup.LayoutParams(0,0)
            linear_layout.gravity = Gravity.FILL_HORIZONTAL
            linear_layout.setPadding(10,10,10,10)
            val columns = arrayOf(MediaColumns._ID)
            val cursor: Cursor? = contentResolver.query(image_upload_list[image_upload_list.size - 1], columns,null,null,null)

            cursor?.moveToFirst()
            val id: Long? = cursor!!.getLong(0)
            Log.d("ID",""+id)


            var bitmap_image = MediaStore.Images.Thumbnails.getThumbnail(contentResolver,id!!, MediaStore.Images.Thumbnails.MINI_KIND, null)
            //bitmap_image = ThumbnailUtils.extractThumbnail(bitmap, 400, 400)




            val imageview = ImageView(this)
            imageview.setImageBitmap(bitmap_image)
            imageview.layoutParams = ViewGroup.LayoutParams(210,210)
            imageview.adjustViewBounds = true
            imageview.scaleType = ImageView.ScaleType.CENTER
            val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
            //lp.flexGrow = 1f
            linear_layout.addView(imageview)
            layout.addView(linear_layout, lp)
        }*/




        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            var uri = data.data
            //val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri)
            Log.d("Image", uri.toString())
            image_upload_list.add(uri)
            Log.d("Image2", image_upload_list.toString())

            val layout = findViewById<FlexboxLayout>(R.id.flexLayout)


            val linear_layout = LinearLayout(this)
            linear_layout.layoutParams = ViewGroup.LayoutParams(0, 0)
            linear_layout.gravity = Gravity.FILL_HORIZONTAL
            //linear_layout.id=image_upload_list.size - 1
            val lin_lay_id = View.generateViewId()
            val img_btn_id = View.generateViewId()
            linear_layout.id = lin_lay_id
            linear_layout.setPadding(10, 10, 10, 10)

            val relative_layout = RelativeLayout(this)
            relative_layout.layoutParams = ViewGroup.LayoutParams(-2, -2)
            relative_layout.setBackgroundColor(Color.WHITE)


            val imagebutton = ImageButton(this)
            //imagebutton.gravity
            imagebutton.layoutParams = ViewGroup.LayoutParams(50, 50)

            imagebutton.background = getDrawable(R.drawable.ic_close)
            imagebutton.id = img_btn_id
            imagebutton.setOnClickListener {
                Toast.makeText(this,"Removed",Toast.LENGTH_SHORT).show()
                //Toast.makeText(this, "Lin Lay ID removed : $lin_lay_id",Toast.LENGTH_SHORT).show()
                layout.removeView(findViewById(lin_lay_id))
                image_upload_list.remove(image_upload_list[image_upload_list.size - 1])
                Log.d("Image2", image_upload_list.toString())
            }

            val columns = arrayOf(MediaColumns._ID)
            val cursor: Cursor? = contentResolver.query(image_upload_list[image_upload_list.size - 1], columns, null, null, null)

            cursor?.moveToFirst()
            val id: Long? = cursor!!.getLong(0)
            Log.d("ID", "" + id)


            var bitmap_image = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id!!, MediaStore.Images.Thumbnails.MINI_KIND, null)
            //bitmap_image = ThumbnailUtils.extractThumbnail(bitmap, 400, 400)


            val imageview = ImageView(this)
            imageview.setImageBitmap(bitmap_image)
            imageview.layoutParams = ViewGroup.LayoutParams(210, 210)
            imageview.adjustViewBounds = true
            imageview.scaleType = ImageView.ScaleType.CENTER
            relative_layout.addView(imageview)
            relative_layout.addView(imagebutton)
            val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
            //lp.flexGrow = 1f
            linear_layout.addView(relative_layout)
            layout.addView(linear_layout, lp)
            Log.d("Image2", "Image got added")
            cursor.close()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Bye Bye App", Toast.LENGTH_SHORT).show()

    }


    private fun updateEditbox(calendar: Calendar, textbox: TextInputEditText) {
        var myformat = "dd/MM/yyyy"
        var sdf = SimpleDateFormat(myformat, Locale.US)
        textbox.setText(sdf.format(calendar.time))


    }

    private fun check_login_status() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
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

        //check_credentials()
        //Log.d("checker", "Value of checker: $checker")

        /*if (checker) {

        val intent = Intent(this,Add_Expenses::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()

        }*/
    }

   data class book_pages(var entry_date:Long, var media: String, var f_r_n: String, var party_num: String, var packers_name: String, var cli_name: String, var quote_no: Int, var s_d: Long, var frm: String, var to: String, var labour_name: String, var white_bag: Int, var str_film: Int, var roll: Int, var tape: Int, var rassi: Int, var bubble: Int, var news_papers: Int, var crate_plastic: Int, var blanket: Int, var cartoon: Int, var ac_mech_name: String, var ac_mech_payment: Int, var carpenter_name: String, var carpenter_payment: Int, var labour_charges: Int, var vehicle_no: String, var vehicle_charges: Int, var diesel: Int, var police: Int, var domestic_charges: Int, var b_a_t_fare: Int, var goods_damage_less_pay: Int, var quote_amount: Int, var total_expenses: Int, var exec_adv: Int, var balance: Int ,var image_urls: ArrayList<String> ) {


    }

}


