package com.example.eebb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.eebb.auth.AuthManager
import com.example.eebb.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!AuthManager.ensureFirebase(applicationContext)) {
            Snackbar.make(binding.root, R.string.firebase_config_error, Snackbar.LENGTH_LONG).show()
            return
        }

        runCatching {
            val navHostFragment = ensureNavHost()
            val navController = navHostFragment.navController

            binding.bottomNavigation.setupWithNavController(navController)
            binding.topAppBar.setNavigationOnClickListener {
                navController.navigate(R.id.nav_church)
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.topAppBar.title = destination.label
            }
        }.onFailure {
            Snackbar.make(binding.root, R.string.navigation_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun ensureNavHost(): NavHostFragment {
        val existingHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        if (existingHost != null) return existingHost

        val navHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commitNow()
        return navHost
    }
}
