package kit.campus.map

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kit.campus.map.ui.facility.ListByFacilityFragment
import kit.campus.map.ui.look.ListByLookFragment
import kit.campus.map.ui.map.ListByMapFragment
import kit.campus.map.ui.no.ListByNoFragment


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_maps, R.id.nav_find_by_no, R.id.nav_find_by_look, R.id.nav_find_by_facility
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { item ->
            var fragment = when (item.itemId) {
                R.id.nav_maps -> ListByMapFragment()
                R.id.nav_find_by_no -> ListByNoFragment()
                R.id.nav_find_by_look -> ListByLookFragment()
                R.id.nav_find_by_facility -> ListByFacilityFragment()
                else -> throw IllegalArgumentException()
            }
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.nav_host_fragment, fragment)
            ft.commit()
            when (item.itemId) {
                R.id.nav_maps -> supportActionBar?.setTitle("マップから探す")
                R.id.nav_find_by_no -> supportActionBar?.setTitle("番号から探す")
                R.id.nav_find_by_look -> supportActionBar?.setTitle("見た目から探す")
                R.id.nav_find_by_facility -> supportActionBar?.setTitle("施設名から探す")
                else -> throw IllegalArgumentException()
            }
            val drawer =
                findViewById<View>(R.id.drawer_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
            true
        }
        // mobile_navigation.xml の startDestを変えるとなぜかうまくいかない
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(
            R.id.nav_host_fragment,
            ListByMapFragment()
        )
        ft.commit()
        supportActionBar?.title = "マップから探す"
        navView.setCheckedItem(R.id.nav_maps)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}