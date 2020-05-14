package com.lui2mi.validtest.models

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class TrackResponse(@SerializedName("tracks") var tracks: Tracks)
data class Tracks(@SerializedName("track") var track: List<Track>)
data class Track(@SerializedName("name") var name: String, @SerializedName("duration") var duration: String, @SerializedName("listeners") var listeners: String, @SerializedName("artist") var artist: TrackArtist, @SerializedName("image") var image: List<TrackImage>){

    fun getTimeDuration(): String {
        var seconds = duration.toLong()
        if (seconds < 60) {
            return seconds.toString()
        } else {
            return DateUtils.formatElapsedTime(seconds)
        }
    }
    fun getFormatListeners(): String{
        val dec = DecimalFormat("#,###.##")
        return dec.format(listeners.toInt()).toString()
    }
}
data class TrackArtist(@SerializedName("name") var name: String)
data class TrackImage(@SerializedName("#text") var text: String, @SerializedName("size") var size: String)