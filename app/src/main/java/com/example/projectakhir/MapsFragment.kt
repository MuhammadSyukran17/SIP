package com.example.projectakhir

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectakhir.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

//    private val callback = OnMapReadyCallback { googleMap ->
//        /**
//         * Manipulates the map once available.
//         * This callback is triggered when the map is ready to be used.
//         * This is where we can add markers or lines, add listeners or move the camera.
//         * In this case, we just add a marker near Sydney, Australia.
//         * If Google Play services is not installed on the device, the user will be prompted to
//         * install it inside the SupportMapFragment. This method will only be triggered once the
//         * user has installed Google Play services and returned to the app.
//         */
//        val indo = LatLng(1.109362, 116.024364)
//        googleMap.addMarker(MarkerOptions().position(indo).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(indo))
//    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.map.getMapAsync(this)
        binding.map.onCreate(savedInstanceState)
        binding.map.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-7.953191, 112.621411)
        val sydney1 = LatLng(-7.966093, 112.622279)
        mMap.addMarker(MarkerOptions().position(sydney).title("Rumah Maul"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.addMarker(MarkerOptions().position(sydney1).title("Mboh"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1))
    }
}