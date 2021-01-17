package kit.campus.map.ui.look

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.ListFragment
import kit.campus.map.PlaceInfoActivity
import kit.campus.map.data.PlaceList


class ListByLookFragment : ListFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // アダプタを生成
        val adapter = context?.let {
            ImageListAdapter(
                it,
                PlaceList.Data
            )
        }
        // リストビューにアダプタをセット
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