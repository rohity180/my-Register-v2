package com.mafh.myregisterv2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_daily_view.*
//import kotlinx.android.synthetic.main
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [dailyViewFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class dailyViewFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val row: ArrayList<book_pages> = ArrayList()

        val uid = FirebaseAuth.getInstance().uid
        var ref = FirebaseDatabase.getInstance().getReference("/users/$uid/Register Pages/")
        var tp:Boolean = this.isAdded
        Log.d("tp","value of tp: $tp")
        if(this.isAdded) {
            recycler_daily.layoutManager = LinearLayoutManager(activity)

            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.d("db_msg", p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    //TODO("not implemented") To change body of created functions use File | Settings | File Templates.
                    for(data_snap:DataSnapshot in p0.children)
                    {
                        var page:book_pages = data_snap.getValue(book_pages::class.java) as book_pages
                        row.add(page)
                    }

                    var adapter = daily_view_adapter(row)
                    recycler_daily.adapter = adapter
                    adapter.notifyDataSetChanged()



                }
            })


            //recycler_daily.layoutManager = LinearLayoutManager(activity)


            /*val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    row.clear()
                    dataSnapshot.children.mapNotNullTo(row){
                        it.getValue<book_pages>(book_pages::class.java)
                    }
                    Log.d("itit","size of : ${row.size}")

                    activity?.runOnUiThread {
                        recycler_daily.adapter = daily_view_adapter(row)
                    }


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)*/

        }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this


        /*val row: ArrayList<book_pages> = ArrayList()

        val uid = FirebaseAuth.getInstance().uid
        var ref = FirebaseDatabase.getInstance().getReference("/users/$uid/Register Pages/")
        var tp:Boolean = this.isAdded
        Log.d("tp","value of tp: $tp")
        if(this.isAdded) {
            recycler_daily.layoutManager = LinearLayoutManager(activity)

            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.d("db_msg", p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    //TODO("not implemented") To change body of created functions use File | Settings | File Templates.
                    for(data_snap:DataSnapshot in p0.children)
                    {
                        var page:book_pages = data_snap.getValue(book_pages::class.java) as book_pages
                        row.add(page)
                    }

                    var adapter = daily_view_adapter(row)
                    recycler_daily.adapter = adapter
                    adapter.notifyDataSetChanged()



                }
            })


            //recycler_daily.layoutManager = LinearLayoutManager(activity)


            *//*val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    row.clear()
                    dataSnapshot.children.mapNotNullTo(row){
                        it.getValue<book_pages>(book_pages::class.java)
                    }
                    Log.d("itit","size of : ${row.size}")

                    activity?.runOnUiThread {
                        recycler_daily.adapter = daily_view_adapter(row)
                    }


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)*//*

        }*/
        return inflater.inflate(R.layout.fragment_daily_view, container, false)



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment dailyViewFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                dailyViewFrag().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
