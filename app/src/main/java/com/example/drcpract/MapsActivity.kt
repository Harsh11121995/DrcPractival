package com.example.drcpract

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.drcpract.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var binding: ActivityMapsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoomLevel = 16.0f //This goes up to 21
        val latLng = LatLng(intent.getStringExtra("lat")!!.toDouble(), intent.getStringExtra("lng")!!.toDouble())
        mMap!!.addMarker(MarkerOptions().position(latLng).title(intent.getStringExtra("title")!!))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }
}