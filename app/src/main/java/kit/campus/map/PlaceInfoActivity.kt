package kit.campus.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
        val usageBody: TextView = findViewById(R.id.usageBody)
        usageBody.text = place?.usage
        val lookImage: ImageView = findViewById(R.id.lookImage)
        lookImage.setImageResource(R.drawable.ic_menu_look)
        // マップを開くボタンに動作割当
        val mapBtn: Button = findViewById(R.id.openWithMap)
        mapBtn.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(browserIntent)
        }
    }
}