package com.example.kompas.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import com.example.kompas.R
import com.example.kompas.home.view.fragment.AboutFragment
import com.example.kompas.home.view.fragment.HomeFragment
import com.example.kompas.home.view.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    //    private lateinit var binding: HomeFragment
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val aboutFragment = AboutFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(homeFragment)
        val button_navbar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        button_navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> replaceFragment(homeFragment)
                R.id.about_nav -> replaceFragment(aboutFragment)
                R.id.profile_nav -> replaceFragment(profileFragment)

            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.body_layout, fragment)
            transaction.commit()
        }
    }

}