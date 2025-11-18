package com.example.eebb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.eebb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.liveActionCard.setOnClickListener {
            openLink(YOUTUBE_URL)
        }

        binding.watchNowButton.setOnClickListener {
            openLink(YOUTUBE_URL)
        }

        binding.mediaWatchNowButton.setOnClickListener {
            openLink(YOUTUBE_URL)
        }

        binding.giveActionCard.setOnClickListener {
            openLink(DONATION_URL)
        }

        binding.donationPrimaryButton.setOnClickListener {
            openLink(DONATION_URL)
        }

        binding.donationQrButton.setOnClickListener {
            openLink(DONATION_URL)
        }

        binding.calendarActionCard.setOnClickListener {
            openLink(CALENDAR_URL)
        }

        binding.calendarGoogleButton.setOnClickListener {
            openLink(CALENDAR_URL)
        }

        binding.locationButton.setOnClickListener {
            openMaps(getString(R.string.location_query))
        }

        binding.prayerSubmitButton.setOnClickListener {
            openEmail(getString(R.string.prayer_email), getString(R.string.prayer_submit))
        }

        binding.prayerReadButton.setOnClickListener {
            openEmail(getString(R.string.contact_email), getString(R.string.prayer_read))
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> scrollToSection(binding.homeContent)
                R.id.nav_calendar -> scrollToSection(binding.calendarSection)
                R.id.nav_media -> scrollToSection(binding.mediaSection)
                R.id.nav_prayer -> scrollToSection(binding.prayerSection)
                R.id.nav_church -> scrollToSection(binding.ministriesSection)
            }
            true
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun openEmail(address: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        startActivity(intent)
    }

    private fun openMaps(query: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${'$'}query"))
        startActivity(intent)
    }

    private fun scrollToSection(target: View) {
        binding.homeScroll.post {
            binding.homeScroll.smoothScrollTo(0, target.top)
        }
    }

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/@egliseevangeliquebaptisteb4650/featured"
        private const val DONATION_URL = "https://www.helloasso.com/associations/eglise-bethesda"
        private const val CALENDAR_URL = "https://calendar.google.com"
    }
}
