package com.lui2mi.validtest.utils

import android.content.Context
import com.lui2mi.validtest.R
import com.lui2mi.validtest.models.ArtistResponse
import com.lui2mi.validtest.models.TrackResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ApiClient(val context: Context) {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://ws.audioscrobbler.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getTopArtist(callback: (result: ArtistResponse) -> Unit) {
        Thread(Runnable {
            try{
                val call = retrofit.create(ApiService::class.java).getTopArtist("spain",context.getString(R.string.key),"json").execute()
                val result =  call.body() as ArtistResponse
                callback(result)
            }catch (e:Exception){}
        }).start()
    }
    fun getTopTracks(callback: (result: TrackResponse) -> Unit) {
        Thread(Runnable {
            try{
                val call = retrofit.create(ApiService::class.java).getTopTracks("spain",context.getString(R.string.key),"json").execute()
                val result =  call.body() as TrackResponse
                callback(result)
            }catch (e:Exception){}
        }).start()
    }
}