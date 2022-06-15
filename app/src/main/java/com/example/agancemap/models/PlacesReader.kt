package com.example.agancemap.models

import android.content.Context
import com.example.agancemap.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(val context: Context) {
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    /**
     * Reads the list of place JSON objects in the file places.json
     * and returns a list of Place objects
     */
    fun read(): List<InwiPosition> {
        val itemType = object : TypeToken<List<InwiPosition>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<InwiPosition>>(reader, itemType).map {
            it
        }
    }
}