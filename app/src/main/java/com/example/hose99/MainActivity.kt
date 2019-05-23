package com.example.hose99

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.conten_main.*
import android.view.MotionEvent
import android.widget.Toast
import android.opengl.ETC1.getWidth
import android.view.View
import android.view.View.OnTouchListener
import kotlinx.android.synthetic.main.rate_booking.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        (map as SupportMapFragment).getMapAsync(this)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    fun onActivity() {
        val intent = Intent(this,History::class.java)
        startActivity(intent)
    }

//    fun aa() {
//        ratingBar.setOnTouchListener(object : OnTouchListener {
//            override fun onTouch(v: View, event: MotionEvent): Boolean {
//                if (event.action == MotionEvent.ACTION_UP) {
//                    val touchPositionX = event.x
//                    val width = ratingBar.getWidth()
//                    val starsf = touchPositionX / width * 5.0f
//                    val stars = starsf.toInt() + 1.0f
//                    ratingBar.setRating(stars)
//
//                    Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
//                    v.setPressed(false)
//                }
//                if (event.action == MotionEvent.ACTION_DOWN) {
//                    v.setPressed(true)
//                }
//
//                if (event.action == MotionEvent.ACTION_CANCEL) {
//                    v.setPressed(false)
//                }
//
//
//
//
//                return true
//            }
//        })
//    }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                onActivity()
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_sos -> {

            }
            R.id.nav_tools -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
