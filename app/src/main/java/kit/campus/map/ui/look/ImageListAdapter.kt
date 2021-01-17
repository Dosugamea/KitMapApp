package kit.campus.map.ui.look

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import kit.campus.map.R
import kit.campus.map.data.Place

class ImageListAdapter(val context: Context, private val PlaceList: Array<Place>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val place = PlaceList[position]
        val view = LayoutInflater.from(context).inflate(R.layout.listview_layout, null)
        val imgElement = view.findViewById<ImageView>(R.id.image)
        imgElement.setImageResource(place.images[0])
        return view
    }

    override fun getItem(position: Int): Any {
        return PlaceList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return PlaceList.size
    }
}