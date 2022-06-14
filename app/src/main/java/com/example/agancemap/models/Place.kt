package com.example.agancemap.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Place(
   val name: String,
   val latLng: LatLng,
   val address: String,
   val rating: Float
): ClusterItem{
   override fun getPosition(): LatLng {
        return latLng
   }

   override fun getTitle(): String {
      return  name
   }

   override fun getSnippet(): String =address

}