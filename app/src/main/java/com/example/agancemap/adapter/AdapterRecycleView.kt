package com.example.agancemap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.agancemap.R
import com.example.agancemap.models.InwiPosition
import com.google.android.material.chip.ChipGroup


class  AdapterRecycleView(
    val context: Context,
    var list: List<InwiPosition>,
): RecyclerView.Adapter<AdapterRecycleView.AgencyViewHolder>() {

    class AgencyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.agency_name)
        val distance = itemView.findViewById<TextView>(R.id.distance)
        val address = itemView.findViewById<TextView>(R.id.agency_address)
        val isOpen = itemView.findViewById<TextView>(R.id.is_open)
        val openSheet = itemView.findViewById<View>(R.id.open_sheet)
        val workingHours = itemView.findViewById<TextView>(R.id.working_hours)
        val tagRecycler = itemView.findViewById<ChipGroup>(R.id.tag_recycler)
        val direction = itemView.findViewById<Button>(R.id.direction)
        val call = itemView.findViewById<Button>(R.id.call)
        val item = itemView.findViewById<CardView>(R.id.card_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agance_layout, parent, false)
        return AgencyViewHolder(view)
    }
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: AgencyViewHolder, position: Int) {
         with(list[position]){
             holder.name.text=this.label
             holder.address.text=this.addressFr
             holder.isOpen.text="Ouverte"
             holder.
         }

    }

}