package com.example.movielistapp

import androidx.appcompat.app.AppCompatActivity
import com.example.movielistapp.Fragment_First
import com.example.movielistapp.Fragment_Second
import com.example.movielistapp.Fragment_Third
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.movielistapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    var fragmentManager: FragmentManager? = null
    var fragment1: Fragment_First? = null
    var fragment2: Fragment_Second? = null
    var fragment3: Fragment_Third? = null
    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        fragmentManager = supportFragmentManager
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            onOptionsItemSelected(
                item
            )
        }
        fragment1 = Fragment_First()
        fragment2 = Fragment_Second()
        fragment3 = Fragment_Third()
        bottomNavigationView.selectedItemId = R.id.fragmentONE
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment1!!).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fragmentONE) {
            fragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, fragment1!!)
                .commit()
            toolbar!!.title = "Fragment 1"
        } else if (item.itemId == R.id.fragmentTWO) {
            fragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, fragment2!!)
                .commit()
            toolbar!!.title = "Fragment 2"
        } else if (item.itemId == R.id.fragmentTHREE) {
            fragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, fragment3!!)
                .commit()
            toolbar!!.title = "Fragment 3"
        }
        return true
    }
}