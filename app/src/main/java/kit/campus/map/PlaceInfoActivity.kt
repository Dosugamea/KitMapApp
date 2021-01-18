package kit.campus.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kit.campus.map.data.PlaceList


class PlaceInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_info)
        // 渡されたIDから場所情報取得
        val rcv: Intent = intent
        val placeId = rcv.getIntExtra("id", 1)
        val place = PlaceList.Data.find { it.id == placeId }
        // 場所情報に応じて内容変更
        supportActionBar?.title = place?.name
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val usageBody: TextView = findViewById(R.id.usageBody)
        usageBody.text = place?.usage
        usageBody.movementMethod = ScrollingMovementMethod()
        val facilityBody: TextView = findViewById(R.id.facilityBody)
        facilityBody.movementMethod = ScrollingMovementMethod()
        facilityBody.text = place?.facilities?.reduce { tmp, value -> tmp + "\n" + value }
        val lookImage: ImageView = findViewById(R.id.lookImage)
        if (place != null) {
            lookImage.setImageResource(place.images[0])
        }
        // マップを開くボタンに動作割当
        val mapBtn: Button = findViewById(R.id.openWithMap)
        mapBtn.setOnClickListener {
            val browserIntent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "https://www.google.com/maps/search/?api=1&query="
                                + place?.location?.latitude.toString()
                                + ","
                                + place?.location?.longitude.toString()
                    )
                )
            startActivity(browserIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}