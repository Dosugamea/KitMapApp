package kit.campus.map.data

import com.google.android.gms.maps.model.LatLng

class Place(
    val id: Int,
    val name: String,
    val location: LatLng,
    val usage: String,
    val facilities: Array<String>,
    val images: Array<String>
) {
}
