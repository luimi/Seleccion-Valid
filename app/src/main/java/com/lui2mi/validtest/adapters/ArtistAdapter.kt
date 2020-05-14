package com.lui2mi.validtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lui2mi.validtest.R
import com.lui2mi.validtest.models.Artist

class ArtistAdapter(val context: Context, val items: List<Artist> ) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_artist,null))
    }

    override fun getItemCount(): Int = items?.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist: Artist = items[position]
        holder.name.text = artist.name
        holder.listeners.text = "${context.getString(R.string.adapter_listeners)} ${artist.getFormatListeners()}"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var thumbnail: ImageView
        var name: TextView
        var listeners: TextView
        init {
            thumbnail = itemView.findViewById(R.id.iv_thumbnail)
            name = itemView.findViewById(R.id.tv_name)
            listeners = itemView.findViewById(R.id.tv_listeners)
        }
    }
}