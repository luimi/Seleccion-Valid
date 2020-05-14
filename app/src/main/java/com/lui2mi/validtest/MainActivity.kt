package com.lui2mi.validtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lui2mi.validtest.adapters.ArtistAdapter
import com.lui2mi.validtest.adapters.TrackAdapter
import com.lui2mi.validtest.utils.ApiClient
import info.hoang8f.android.segmented.SegmentedGroup

class MainActivity : AppCompatActivity() {


    lateinit var list: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.rv_lists)
        list.layoutManager = LinearLayoutManager(this)
        findViewById<SegmentedGroup>(R.id.sb_tops).setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.btn_artists -> {
                    getArtists()
                }
                R.id.btn_tracks -> {
                    getTracks()
                }
            }
        }
        getArtists()

    }
    fun getArtists(){
        ApiClient(this).getTopArtist{
            runOnUiThread {
                adapter = ArtistAdapter(this, it.topartists.artist)
                list.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun getTracks(){
        ApiClient(this).getTopTracks{
            runOnUiThread {
                adapter = TrackAdapter(this, it.tracks.track)
                list.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

}
