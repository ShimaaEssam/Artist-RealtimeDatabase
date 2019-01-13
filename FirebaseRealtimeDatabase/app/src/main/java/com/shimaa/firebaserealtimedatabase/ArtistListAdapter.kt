package com.shimaa.firebaserealtimedatabase

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ArtistListAdapter(private val context: Activity, private val artists: List<Artist>) : ArrayAdapter<Artist>(context, R.layout.list_layout, artists) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.list_layout, null, true)
        val name = listViewItem.findViewById<View>(R.id.textViewName) as TextView
        val genre = listViewItem.findViewById<View>(R.id.textViewGenre) as TextView
        val artist = artists[position]
        name.text = artist.getName()
        genre.text = artist.getGenre()
        return listViewItem

    }
}

