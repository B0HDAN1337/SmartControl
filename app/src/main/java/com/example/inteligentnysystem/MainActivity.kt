package com.example.inteligentnysystem

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    //implement to initialize navigation
    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        

        //to search in layout
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        //Function to navigate on click
        bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId

           when(id){
               R.id.nav_Home -> { changeFragment(HomeFragment()) }
               R.id.nav_Light -> { changeFragment(LightFragment()) }
               R.id.nav_History -> { changeFragment(HistoryFragment()) }
               R.id.nav_Settings -> { changeFragment(SettingsFragment()) }
           }
            true
        }

        //Start application with HomeFragment activity
        changeFragment(HomeFragment())
    }

    //Function to change fragments in layout
    fun changeFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.fragments, fragment).commit()
    }
}