package com.example.agancemap.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agancemap.R
import com.example.agancemap.models.Shulder
import java.util.*

class AdapterWorkingHours(var context: Context,var data:List<Shulder> ):
    RecyclerView.Adapter<AdapterWorkingHours.ViewHolder>() {

    fun setList(shuldes:List<Shulder>){
        data=shuldes
        notifyDataSetChanged()
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var day: TextView =itemView.findViewById(R.id.day)
        var workingTime: TextView =itemView.findViewById(R.id.working_hours)
        var actualday=itemView.findViewById<TextView>(R.id.today)
        var workingHourstoday=itemView.findViewById<TextView>(R.id.todayworking_hours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.working_hours_adapter,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(data[position]){
            val calendar: Calendar = Calendar.getInstance()
            val daynumber: Int = calendar.get(Calendar.DAY_OF_WEEK)
            if((daynumber-2)==position){
                holder.day.visibility=View.GONE
                holder.workingHourstoday.visibility=View.VISIBLE
                holder.actualday.visibility=View.VISIBLE
                holder.workingTime.visibility=View.GONE
                holder.actualday.text=dayFr
                holder.workingHourstoday.text=openingHours[0].from +"-"+openingHours[0].to+ "AM ,"+openingHours[1].from +"-"+openingHours[1].to+ "PM"
            }else{
                holder.day.visibility=View.VISIBLE
                holder.workingHourstoday.visibility=View.GONE
                holder.actualday.visibility=View.GONE
                holder.workingTime.visibility=View.VISIBLE
                holder.day.text=dayFr
                holder.workingTime.text=openingHours[0].from +"-"+openingHours[0].to+ "AM ,"+openingHours[1].from +"-"+openingHours[1].to+ "PM"
            }
        }
    }

    override fun getItemCount(): Int =data.size

}