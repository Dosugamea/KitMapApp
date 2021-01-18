package kit.campus.map.data

import com.google.android.gms.maps.model.LatLng
import kit.campus.map.R


object PlaceList {
    val Data = arrayOf(
        Place(
            1,
            "1号館",
            LatLng(36.530653476886094, 136.62768332536527),
            "学割がほしいときによく行くと思います。",
            arrayOf("入試センター", "在学証明書/学割証 自動発行機"),
            arrayOf(R.drawable.no_1)
        ),
        Place(
            5,
            "5号館",
            LatLng(36.53102656393301, 136.62850731973245),
            "簡易郵便局がある建物です。横に長いです。簡易郵便局は5時に閉まりますので要注意。",
            arrayOf("簡易郵便局"),
            arrayOf(R.drawable.no_5)
        ),
        Place(
            7,
            "7号館",
            LatLng(36.531840920526, 136.62819525396853),
            "2020年リニューアルされた自習室がある建物です。たこやき/たいやき/クレープなどのフードトラックがよく来ます。専門実験演習等の教室はこちらになることがあります。",
            arrayOf("自習室", "プリンター(自習室)", "フードトラック"),
            arrayOf(R.drawable.no_7)
        ),
        Place(
            8,
            "8号館",
            LatLng(36.530390, 136.629309),
            "講義でよく使う建物です。2階に自己開発センター(資格試験申し込み窓口)があります。",
            arrayOf("自己開発センター"),
            arrayOf(R.drawable.no_8)
        ),
        Place(
            21,
            "21号館",
            LatLng(36.530106, 136.629148),
            "飲食店やブックセンターがある建物です。普段授業ある時はよく行くと思います。ブックセンターでは文房具やまんがタイムきららMAXも買えます。",
            arrayOf("LA TERRA(ラテラ)", "IL SOLE(イルソーレ)", "KIT ブックセンター", "KIT サービスセンター"),
            arrayOf(R.drawable.no_21)
        ),
        Place(
            23,
            "23号館",
            LatLng(36.53129852655035, 136.6294647341345),
            "講義でよく使う建物です。エスカレーターがあるかなり新しめの建物です。つながっている建物が多いので通る事が多いと思います。",
            arrayOf("数理工教育研究センター", "プリンター(23号館)"),
            arrayOf(R.drawable.no_23)
        )
    )
}

