package com.mafh.myregisterv2

import android.Manifest
import android.app.Activity
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
import java.text.SimpleDateFormat
import java.util.*


class Add_Expenses : AppCompatActivity() {

    val image_upload_list = arrayListOf<Uri>()
    //val image_grid = findViewById<GridLayout>(R.id.images_grid)

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

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__expenses)
        val nxt_btn=findViewById<Button>(R.id.next_btn)
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



        //val matrix = Matrix()



        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = this.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        label1.setBackgroundResource(backgroundResource)
        label2.setBackgroundResource(backgroundResource)
        label3.setBackgroundResource(backgroundResource)
        container1.visibility= View.VISIBLE

        //arrow_1.setScaleType(ImageView.ScaleType.MATRIX)
        //matrix.postRotate((180).toFloat(), (arrow_1.drawable.bounds.width()/2).toFloat(), (arrow_1.drawable.bounds.height()/2).toFloat())
        //arrow_1.imageMatrix=matrix
        //arrow_1.rotation=180f
        arrow_1.animate().rotation(180f).setDuration(300).start()
        container2.visibility= View.GONE
        container3.visibility= View.GONE
        label1.setOnClickListener {

            if (container1.isShown) {
                container1.visibility = View.GONE
                arrow_1.animate().rotation(360f).setDuration(400).start()
                //label1.setBackgroundResource(R.color.white)
            }

            else {
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
            }
            else {
                container2.visibility = View.VISIBLE
                //arrow_2.rotation=180f
                arrow_2.animate().rotation(180f).setDuration(400).start()
                //label2.setBackgroundResource(R.color.colorPrimaryDark)
            }



        }

        val btn_back = findViewById<Button>(R.id.back_btn)
        btn_back.setOnClickListener {
            val intent= Intent(this,sign_up::class.java)
            startActivity(intent)
        }





        label3.setOnClickListener {

            if (container3.isShown) {
                container3.visibility = View.GONE
                //arrow_3.rotation=0f
                arrow_3.animate().rotation(360f).setDuration(400).start()
                //label3.setBackgroundResource(R.color.white)
            }
            else {
                container3.visibility = View.VISIBLE
                //arrow_3.rotation=180f
                arrow_3.animate().rotation(180f).setDuration(400).start()
                //label3.setBackgroundResource(R.color.colorPrimaryDark)
            }


        }
        nxt_btn.setOnClickListener {
            val intent= Intent(this,viewDataTabs::class.java)
            startActivity(intent)
        }



        add_image.setOnClickListener {
            Toast.makeText(this,"Select an Image to add !! ", Toast.LENGTH_SHORT).show()
            selectImage(this)
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

        if (requestCode==1 && resultCode== Activity.RESULT_OK && data != null)
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
            //linear_layout.id=image_upload_list.size - 1
            val lin_lay_id = View.generateViewId()
            val img_btn_id = View.generateViewId()
            linear_layout.id = lin_lay_id
            linear_layout.setPadding(10,10,10,10)

            val relative_layout = RelativeLayout(this)
            relative_layout.layoutParams = ViewGroup.LayoutParams(-2,-2)
            relative_layout.setBackgroundColor(Color.WHITE)


            val imagebutton = ImageButton(this)
            //imagebutton.gravity
            imagebutton.layoutParams = ViewGroup.LayoutParams(50,50)

            imagebutton.background =  getDrawable(R.drawable.ic_close)
            imagebutton.id = img_btn_id
            imagebutton.setOnClickListener {
                //Toast.makeText(this,"clicked",Toast.LENGTH_SHORT)
                //Toast.makeText(this, "Lin Lay ID removed : $lin_lay_id",Toast.LENGTH_SHORT).show()
                layout.removeView(findViewById(lin_lay_id))
                image_upload_list.remove(image_upload_list[image_upload_list.size - 1])
                Log.d("Image2",image_upload_list.toString())
            }

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
            relative_layout.addView(imageview)
            relative_layout.addView(imagebutton)
            val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
            //lp.flexGrow = 1f
            linear_layout.addView(relative_layout)
            layout.addView(linear_layout, lp)
        }
    }



    override fun onDestroy(){
        super.onDestroy()
        Toast.makeText(this,"Bye Bye App", Toast.LENGTH_SHORT).show()

    }

    fun add_image_in_grid(layout:GridLayout,context:Context,image_upload_list:List<Uri>)
    {


    }

    class book_pages(media:String, f_r_n:String, party_num:String, packers_name:String, cli_name:String, quote_no:Int, s_d: Date, frm:String, to:String, labour_name:String, white_bag:Int, str_film:Int, roll:Int, tape:Int, rassi:Int, bubble:Int, news_papers:Int, crate_plastic:Int, blanket:Int, cartoon:Int, ac_mech_name:String, ac_mech_payment:Int, carpenter_name:String, carpenter_payment:Int, labour_charges:Int, vehicle_no:String, vehicle_charges:Int, diesel:Int, police:Int, domestic_charges:Int, b_a_t_fare:Int, goods_damage_less_pay:Int, quote_amount:Int, total_expenses:Int, exec_adv:Int, balance:Int)

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

