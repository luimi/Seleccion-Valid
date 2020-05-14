package com.lui2mi.validtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lui2mi.validtest.utils.ApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun getArtists(){
        ApiClient(this).getTopArtist{

        }
    }
}
