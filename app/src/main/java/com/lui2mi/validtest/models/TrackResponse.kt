package com.lui2mi.validtest.models

import com.google.gson.annotations.SerializedName

data class TrackResponse(@SerializedName("tracks") var tracks: Tracks)
data class Tracks(@SerializedName("track") var track: List<Track>)
data class Track(@SerializedName("name") var name: String, @SerializedName("duration") var duration: String, @SerializedName("listeners") var listeners: String, @SerializedName("artist") var artist: TrackArtist, @SerializedName("image") var image: List<TrackImage>)
data class TrackArtist(@SerializedName("name") var name: String)
data class TrackImage(@SerializedName("#text") var text: String, @SerializedName("size") var size: String)