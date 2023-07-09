package com.example.wello

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.wello.databinding.ActivityMainBinding
import com.example.wello.ui.goals.GoalsFragment
import com.example.wello.ui.home.HomeFragment
import com.example.wello.ui.planner.PlannerFragment

import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_planner, R.id.nav_goals, R.id.nav_logout
            ), drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, HomeFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_planner -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, PlannerFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_goals -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GoalsFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}