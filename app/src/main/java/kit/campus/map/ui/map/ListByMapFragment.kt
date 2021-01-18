package kit.campus.map.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kit.campus.map.PlaceInfoActivity
import kit.campus.map.R
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
            val bmp = Bitmap.createBitmap(160, 80, Bitmap.Config.ARGB_8888)
            val canvas1 = Canvas(bmp)
            val color = Paint()
            val rect: Rect = Rect(0, 0, 160, 80)
            rect.offset(0, 0)
            color.textSize = 42F
            color.color = Color.BLACK
            val colorWhite = Paint()
            colorWhite.color = Color.WHITE
            canvas1.drawRect(rect, colorWhite)
            canvas1.drawText(place.name, 30F, 40F, color)

            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(place.location)
                    .title("タップで情報を見る")
                    .icon(BitmapDescriptorFactory.fromBitmap(bmp))
            )
            marker.tag = place.id
        }
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(36.530205162894134, 136.62784554138173),
                17.0f
            )
        )
        googleMap.setOnInfoWindowClickListener { marker ->
            val infoActivity = Intent(activity, PlaceInfoActivity::class.java)
            infoActivity.putExtra("id", marker.tag as Int)
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