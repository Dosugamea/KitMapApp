package kit.campus.map.data

import com.google.android.gms.maps.model.LatLng
import kit.campus.map.R


object PlaceList {
    val Data = arrayOf(
        Place(
            1,
            "1号館",
            LatLng(36.530653476886094, 136.62768332536527),
            "学割がほしいときによく使うと思います",
            arrayOf("入試センター", "学割申請", "hoge", "fuga", "hage", "ping", "pong"),
            arrayOf(R.drawable.no_1)
        ),
        Place(
            2,
            "2号館",
            LatLng(36.530653476886094, 136.62768332536527),
            "学割がほしいときによく使うと思います",
            arrayOf("入試2", "入試3"),
            arrayOf(R.drawable.no_1)
        ),
        Place(
            3,
            "3号館",
            LatLng(36.530653476886094, 136.62768332536527),
            "学割がほしいときによく使うと思います",
            arrayOf("入試4", "入試5"),
            arrayOf(R.drawable.no_1)
        ),
        Place(
            4,
            "4号館",
            LatLng(36.530653476886094, 136.62768332536527),
            "学割がほしいときによく使うと思います",
            arrayOf("入試6", "入試7"),
            arrayOf(R.drawable.no_1)
        )
    )
}

