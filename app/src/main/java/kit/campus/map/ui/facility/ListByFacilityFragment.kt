package kit.campus.map.ui.facility

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import kit.campus.map.PlaceInfoActivity
import kit.campus.map.data.Place
import kit.campus.map.data.PlaceList

class ListByFacilityFragment : ListFragment() {
    var facilities: Array<String> = arrayOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (place in PlaceList.Data) {
            facilities += place.facilities
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            facilities
        )
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val placeName: String = facilities[position]
        val place: Place? = PlaceList.Data.find { it.facilities.contains(placeName) }
        val infoActivity = Intent(
            context,
            PlaceInfoActivity::class.java
        )
        infoActivity.putExtra("id", place?.id)
        startActivity(infoActivity)
    }
}