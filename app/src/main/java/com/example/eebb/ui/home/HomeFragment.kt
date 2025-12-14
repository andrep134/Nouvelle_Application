package com.example.eebb.ui.home

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eebb.databinding.FragmentHomeBinding
import com.example.eebb.databinding.ItemHighlightBinding
import com.example.eebb.ui.common.composeEmail
import com.example.eebb.ui.common.openLink
import com.example.eebb.ui.common.openLocation
import com.example.eebb.ui.model.Sermon

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
            binding.nextEventCard.eventTitle.text = event.title
            binding.nextEventCard.eventSchedule.text = event.schedule
            binding.nextEventCard.eventLocation.text = event.location
            binding.nextEventCard.eventDescription.text = event.description
            binding.nextEventCard.addToCalendarButton.setOnClickListener {
                openLink("https://calendar.google.com")
            }
        }

        viewModel.sermons.observe(viewLifecycleOwner) { sermons ->
            setSermon(sermons.firstOrNull())
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
        binding.giveButton.setOnClickListener { openLink(DONATION_URL) }
        binding.prayerButton.setOnClickListener { composeEmail(PRAYER_EMAIL, getString(com.example.eebb.R.string.prayer_title)) }
        binding.sermonCta.setOnClickListener { openLink(YOUTUBE_URL) }
        binding.nextEventCard.addToCalendarButton.setOnClickListener { openLink(CALENDAR_URL) }
        binding.nextEventCard.eventLocation.setOnClickListener { openLocation(getString(com.example.eebb.R.string.location_query)) }
    }

    private fun setSermon(sermon: Sermon?) {
        sermon ?: return
        binding.sermonTitle.text = sermon.title
        binding.sermonMeta.text = "${sermon.speaker} • ${sermon.duration} • ${sermon.publishedOn}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/@egliseevangeliquebaptisteb4650/featured"
        private const val DONATION_URL = "https://www.helloasso.com/associations/eglise-bethesda"
        private const val CALENDAR_URL = "https://calendar.google.com"
        private const val PRAYER_EMAIL = "priere@eebbethesda.com"
    }
}
