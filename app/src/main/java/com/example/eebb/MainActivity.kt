package com.example.eebb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eebb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.liveButton.setOnClickListener {
            openLink("https://www.youtube.com/@BethesdaLive")
        }

        binding.giveButton.setOnClickListener {
            openLink("https://www.helloasso.com/associations/eglise-bethesda")
        }

        binding.calendarButton.setOnClickListener {
            openLink("https://calendar.google.com")
        }

        binding.podcastButton.setOnClickListener {
            openLink("https://open.spotify.com/show/bethesda-sermons")
        }

        binding.contactButton.setOnClickListener {
            openEmail("contact@bethesda-eglise.fr")
        }

        binding.locationButton.setOnClickListener {
            openMaps("Eglise Evangelique Baptiste Bethesda")
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun openEmail(address: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
        }
        startActivity(intent)
    }

    private fun openMaps(query: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${'$'}query"))
        startActivity(intent)
    }
}
