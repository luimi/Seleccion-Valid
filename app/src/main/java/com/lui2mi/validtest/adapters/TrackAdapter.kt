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
import com.lui2mi.validtest.models.Track
import com.squareup.picasso.Picasso

class TrackAdapter(val context: Context, var items: List<Track> ) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_track,null))
    }

    override fun getItemCount(): Int = items?.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track: Track = items[position]
        holder.name.text = "${track.name} (${track.artist.name})"
        holder.duration.text = "${context.getString(R.string.adapter_duration)} ${track.getTimeDuration()}"
        holder.listeners.text = "${context.getString(R.string.adapter_listeners)} ${track.getFormatListeners()}"
        Picasso.get().load(track.image[0].text).into(holder.thumbnail);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var thumbnail: ImageView
        var name: TextView
        var duration: TextView
        var listeners: TextView
        init {
            thumbnail = itemView.findViewById(R.id.iv_thumbnail)
            name = itemView.findViewById(R.id.tv_name)
            duration = itemView.findViewById(R.id.tv_duration)
            listeners = itemView.findViewById(R.id.tv_listeners)
        }
    }
    fun includeList(newData: List<Track>){
        items = items.plus(newData)
    }
}