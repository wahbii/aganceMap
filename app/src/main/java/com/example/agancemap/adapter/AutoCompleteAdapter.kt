package com.example.agancemap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.Nullable
import com.example.agancemap.R


class AutoCompleteAdapter  // invoke the suitable constructor of the ArrayAdapter class
    (context: Context, arrayList: List<String>) :
    ArrayAdapter<String?>(context, 0, arrayList!!) {
    override fun getView(position: Int, @Nullable convertView: View?, parent: ViewGroup): View {

        // convertView which is recyclable view
        var currentItemView = convertView

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.layout_adapter_suggestions, parent, false)
        }

        // get the position of the view from the ArrayAdapter
        val currentString: String? = getItem(position)



        // then according to the position of the view assign the desired TextView 1 for the same
        val textView1 = currentItemView?.findViewById<TextView>(com.example.agancemap.R.id.suggestions)
        textView1?.setText(currentString)

        // then according to the position of the view assign the desired TextView 2 for the same


        // then return the recyclable view
        return currentItemView!!
    }
}