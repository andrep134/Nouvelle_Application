package com.example.eebb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.eebb.auth.AuthManager
import com.example.eebb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AuthManager.ensureFirebase(applicationContext)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
                ?: NavHostFragment.create(R.navigation.nav_graph).also { navHost ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, navHost)
                        .setPrimaryNavigationFragment(navHost)
                        .commitNow()
                }
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
        binding.topAppBar.setNavigationOnClickListener {
            navController.navigate(R.id.nav_church)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.topAppBar.title = destination.label
        }
    }
}
