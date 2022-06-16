package com.example.agancemap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.agancemap.models.InwiPosition
import com.example.agancemap.models.Place
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(private val context: Context):GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker): View? {
        val place=p0?.tag as  InwiPosition ?: return null
        val view = LayoutInflater.from(context).inflate(
            R.layout.marker_info_contents, null
        )
        view.findViewById<TextView>(
            R.id.text_view_title
        ).text = place.label
        view.findViewById<TextView>(
            R.id.text_view_address
        ).text = place.addressFr


        return view
    }

    override fun getInfoWindow(p0: Marker): View? {
       return null
    }

}