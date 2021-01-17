package kit.campus.map.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kit.campus.map.PlaceInfoActivity
import kit.campus.map.R


class ListByMapFragment : Fragment() {

    private val placeList = mapOf(
        "1号館" to LatLng(36.530205162894134, 136.62784554138173)
    )

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        if (
            context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
            && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                2
            )
        } else {
            googleMap.isMyLocationEnabled = true
        }
        placeList.forEach { (k, v) ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(v)
                    .title(k)
            )
        }
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(36.530205162894134, 136.62784554138173),
                17.0f
            )
        )
        googleMap.setOnMarkerClickListener { marker ->
            val venueID = marker.id
            val venueName = marker.title
            val intent = Intent(activity, PlaceInfoActivity::class.java)
            startActivity(intent)
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_by_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}