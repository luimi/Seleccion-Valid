package com.lui2mi.validtest.utils

import com.lui2mi.validtest.models.ArtistResponse
import com.lui2mi.validtest.models.TrackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/2.0/?method=geo.gettopartists")
    fun getTopArtist(@Query("country") country: String, @Query("api_key") apikey: String, @Query("format") format: String, @Query("page") page: Int): Call<ArtistResponse>

    @GET("/2.0/?method=geo.gettoptracks")
    fun getTopTracks(@Query("country") country: String, @Query("api_key") apikey: String, @Query("format") format: String, @Query("page") page: Int): Call<TrackResponse>
}