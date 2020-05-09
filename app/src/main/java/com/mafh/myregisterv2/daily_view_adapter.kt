package com.mafh.myregisterv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.daily_view_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class daily_view_adapter(var page:ArrayList<book_pages>):RecyclerView.Adapter<customViewholder>() {
    var months= listOf<String>("january","February","March","April","May","June","July","August","September","October","November","December")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewholder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val layout_inflator = LayoutInflater.from(parent?.context)
        val row = layout_inflator.inflate(R.layout.daily_view_layout,parent,false)
        return customViewholder(row)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return page.size
    }

    override fun onBindViewHolder(holder: customViewholder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val page_data:book_pages = page[position]
        val cal: Calendar = Calendar.getInstance()
        val d = Date(page_data.entry_date)
        cal.time = d
        var day:Int =cal.get(Calendar.DAY_OF_MONTH)
        var month:Int =cal.get(Calendar.MONTH)
        var year:Int = cal.get(Calendar.YEAR)
        holder.view.daily_date.setText(day.toString())
        holder.view.daily_month.setText(months[month])
        holder.view.daily_year.setText((page_data.year).toString())
        holder.view.daily_profit_loss.setText((page_data.profit).toString())


    }


}





class customViewholder(val view: View):RecyclerView.ViewHolder(view) {

}
