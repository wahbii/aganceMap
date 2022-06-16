package com.example.agancemap.utils

import com.example.agancemap.MapsActivity
import com.google.android.gms.maps.model.LatLng
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

object Utils {

    fun getDistance(latLng: LatLng):Double{
      //SA-B = arc cos (sin ϕA sin ϕB + cos ϕA cos ϕB cos dλ)
        //dλ = λB – λA
        if(MapsActivity.location!=null){
            var apha=latLng.longitude-MapsActivity.location.longitude
            var distance= acos(sin(latLng.latitude)* sin(MapsActivity.location.latitude) + cos(latLng.longitude)*cos(MapsActivity.location.longitude)* cos(apha))
            val roundoff = (distance* 100.0).roundToInt() / 100.0
            return  roundoff
        }
        return  -1.00

    }
}