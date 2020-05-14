package com.lui2mi.validtest

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lui2mi.validtest.adapters.ArtistAdapter
import com.lui2mi.validtest.adapters.TrackAdapter
import com.lui2mi.validtest.utils.ApiClient
import com.lui2mi.validtest.utils.EndlessRecyclerViewScrollListener
import cz.kinst.jakub.view.SimpleStatefulLayout
import cz.kinst.jakub.view.StatefulLayout
import info.hoang8f.android.segmented.SegmentedGroup

class MainActivity : AppCompatActivity() {


    lateinit var list: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var tops: SegmentedGroup
    lateinit var content: SimpleStatefulLayout
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content = findViewById(R.id.sl_content)
        content.showProgress()
        list = findViewById(R.id.rv_lists)
        val manager: LinearLayoutManager = LinearLayoutManager(this)
        list.layoutManager = manager
        tops = findViewById(R.id.sb_tops)
        tops.setOnCheckedChangeListener { radioGroup, i ->
            page = 1
            when(i){
                R.id.btn_artists -> {
                    getArtists(false)
                }
                R.id.btn_tracks -> {
                    getTracks(false)
                }
            }
        }
        scrollListener =
            object : EndlessRecyclerViewScrollListener(manager) {
                override fun onLoadMore(p: Int, totalItemsCount: Int, view: RecyclerView) {
                    Log.e("page", p.toString())
                    page = p + 1
                    var id: Int = tops.checkedRadioButtonId
                    when(id){
                        R.id.btn_artists -> {
                            getArtists(true)
                        }
                        R.id.btn_tracks -> {
                            getTracks(true)
                        }
                    }
                }
            }
        list.addOnScrollListener(scrollListener)
        getArtists(false)
        getConnectionListener()
    }
    fun getArtists(isContinue: Boolean){
        ApiClient(this).getTopArtist(page){
            runOnUiThread {
                if(isContinue){
                    (adapter as ArtistAdapter).includeList(it.topartists.artist)
                }else {
                    adapter = ArtistAdapter(this, it.topartists.artist)
                    list.adapter = adapter
                }
                content.showContent()
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun getTracks(isContinue: Boolean){
        ApiClient(this).getTopTracks(page){
            runOnUiThread {
                if(isContinue){
                    (adapter as TrackAdapter).includeList(it.tracks.track)
                } else {
                    adapter = TrackAdapter(this, it.tracks.track)
                    list.adapter = adapter
                }
                content.showContent()
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun getConnectionListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager?.let {

                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        Snackbar.make(findViewById(R.id.context_view), R.string.alert_connection, Snackbar.LENGTH_SHORT)
                            .show();
                    }
                    override fun onLost(network: Network?) {
                        Snackbar.make(findViewById(R.id.context_view), R.string.alert_noconnection, Snackbar.LENGTH_SHORT)
                            .show();
                    }
                })
            }
        }
    }
}
