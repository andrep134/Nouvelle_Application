package com.example.eebb.ui.home

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eebb.auth.AuthManager
import com.example.eebb.databinding.FragmentHomeBinding
import com.example.eebb.databinding.ItemHighlightBinding
import com.example.eebb.ui.common.composeEmail
import com.example.eebb.ui.common.openLink
import com.example.eebb.ui.common.openLocation

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.root.layoutTransition = LayoutTransition().apply {
            enableTransitionType(LayoutTransition.CHANGING)
            enableTransitionType(LayoutTransition.APPEARING)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.heroEvent.observe(viewLifecycleOwner) { event ->
            binding.eventTitle.text = event.title
            binding.eventSchedule.text = event.schedule
            binding.eventLocation.text = event.location
            binding.eventDescription.text = event.description
        }

        viewModel.highlights.observe(viewLifecycleOwner) { items ->
            binding.highlightContainer.removeAllViews()
            items.forEach { highlight ->
                val highlightBinding = ItemHighlightBinding.inflate(layoutInflater, binding.highlightContainer, false)
                highlightBinding.highlightTitle.text = highlight.title
                highlightBinding.highlightDetail.text = highlight.detail
                binding.highlightContainer.addView(highlightBinding.root)
            }
        }

        binding.verseText.text = viewModel.verse.first
        binding.verseRef.text = viewModel.verse.second

        binding.liveButton.setOnClickListener { openLink(YOUTUBE_URL) }
        binding.calendarButton.setOnClickListener { openLink(CALENDAR_URL) }
        binding.liveQuick.setOnClickListener { openLink(YOUTUBE_URL) }
        binding.calendarQuick.setOnClickListener { openLink(CALENDAR_URL) }
        binding.giveButton.setOnClickListener { openLink(DONATION_URL) }
        binding.prayerButton.setOnClickListener {
            requireLogin { composeEmail(PRAYER_EMAIL, getString(com.example.eebb.R.string.prayer_title)) }
        }
        binding.addToCalendarButton.setOnClickListener { requireLogin { openLink(CALENDAR_URL) } }
        binding.eventLocation.setOnClickListener { openLocation(getString(com.example.eebb.R.string.location_query)) }
        binding.viewVerse.setOnClickListener { openLink(BIBLE_URL) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun requireLogin(action: () -> Unit) {
        if (AuthManager.currentUser() == null) {
            findNavController().navigate(com.example.eebb.R.id.nav_login)
        } else {
            action()
        }
    }

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/@egliseevangeliquebaptisteb4650/featured"
        private const val DONATION_URL = "https://www.helloasso.com/associations/eglise-bethesda"
        private const val CALENDAR_URL = "https://calendar.google.com"
        private const val PRAYER_EMAIL = "priere@eebbethesda.com"
        private const val BIBLE_URL = "https://www.bible.com/fr/bible"
    }
}
