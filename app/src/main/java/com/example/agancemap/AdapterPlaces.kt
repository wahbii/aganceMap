package com.example.agancemap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agancemap.models.InwiPosition


class AdapterPlaces(var context: Context,var  places:List<InwiPosition>) : RecyclerView.Adapter<AdapterPlaces.ViewHolder>() {


    fun setListPlaces(listPlaces:List<InwiPosition>){
        places=listPlaces
        notifyDataSetChanged()

    }
    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var adress:TextView =itemView.findViewById(R.id.adress)
        var isopen:TextView=itemView.findViewById(R.id.isopen)
        var will_close:TextView=itemView.findViewById(R.id.whenclose)
        var name:TextView=itemView.findViewById(R.id.agence_inwi)
        var distance:TextView=itemView.findViewById(R.id.distance)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var v= LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(places.get(position)){
            holder.adress.text=addressFr
            holder.name.text=label+"-"+district.labelFr

        }
    }

    override fun getItemCount(): Int =places.size
}