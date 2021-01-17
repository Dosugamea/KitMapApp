package kit.campus.map.ui.no

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import kit.campus.map.PlaceInfoActivity
import kit.campus.map.data.PlaceList


class ListByNoFragment : ListFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_list_item_1,
            PlaceList.Data.map { it.id.toString() + "号館" }
        )
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val placeId: Int = PlaceList.Data[position].id
        val infoActivity = Intent(
            context,
            PlaceInfoActivity::class.java
        )
        infoActivity.putExtra("id", placeId)
        startActivity(infoActivity)
    }
}