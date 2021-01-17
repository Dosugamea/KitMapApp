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
import kit.campus.map.data.Place
import kit.campus.map.data.PlaceList


class ListByMapFragment : Fragment() {

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
        for (place in PlaceList.Data) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(place.location)
                    .title(place.name)
            )
        }
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(36.530205162894134, 136.62784554138173),
                17.0f
            )
        )
        googleMap.setOnMarkerClickListener { marker ->
            val place: Place? = PlaceList.Data.find { it.name == marker.title }
            val infoActivity = Intent(activity, PlaceInfoActivity::class.java)
            infoActivity.putExtra("id", place?.id)
            startActivity(infoActivity)
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