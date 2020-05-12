package com.mafh.myregisterv2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.daily_view_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class daily_view_adapter(val ctx:Context , var page:ArrayList<book_pages>):RecyclerView.Adapter<customViewholder>() {
    var months= listOf<String>("january","February","March","April","May","June","July","August","September","October","November","December")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewholder {
        val layout_inflator = LayoutInflater.from(ctx)
        val row = layout_inflator.inflate(R.layout.daily_view_layout,parent,false)
        return customViewholder(row)
    }

    override fun getItemCount(): Int {

        return page.size
    }

    override fun onBindViewHolder(holder: customViewholder, position: Int) {

        val page_data:book_pages = page[position]
        val cal: Calendar = Calendar.getInstance()
        val d = Date(page_data.entry_date)
        cal.time = d
        var day:Int =cal.get(Calendar.DAY_OF_MONTH)
        var month:Int =cal.get(Calendar.MONTH)
        var year:Int = cal.get(Calendar.YEAR)
        if(day<10)
        {
            holder.view.daily_date.setText("0${day.toString()}")
        }
        else {
            holder.view.daily_date.setText(day.toString())
        }
        holder.view.daily_month.setText(months[month])
        holder.view.daily_year.setText((page_data.year).toString())
        if(page_data.profit<=0)
        {
            holder.view.daily_profit_loss.setText((page_data.profit * -1).toString())
            holder.view.daily_profit_loss.setTextColor(Color.rgb(205,92,92))
            holder.view.d_calendar_ic.setImageResource(R.drawable.ic_calendar_red)
            holder.view.dailyview_chart.setImageResource(R.drawable.ic_line_chart_red)
        }
        else{
            holder.view.daily_profit_loss.setText((page_data.profit).toString())
            holder.view.daily_profit_loss.setTextColor(Color.rgb(141,191,139))
            holder.view.d_calendar_ic.setImageResource(R.drawable.ic_calendar_green)
            holder.view.dailyview_chart.setImageResource(R.drawable.ic_line_chart_green)

        }

        fun newer()
        {
            Toast.makeText(ctx,"Your Profit: ${page_data.profit}",Toast.LENGTH_SHORT).show()
        }


    }


}





class customViewholder(val view: View):RecyclerView.ViewHolder(view) {

}
