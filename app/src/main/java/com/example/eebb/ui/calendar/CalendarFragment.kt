package com.example.eebb.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eebb.databinding.FragmentCalendarBinding
import com.example.eebb.ui.common.openLink

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[CalendarViewModel::class.java] }
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventsAdapter { openLink(CALENDAR_URL) }
        binding.eventsRecycler.adapter = adapter

        viewModel.events.observe(viewLifecycleOwner) { events ->
            if (events.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
                binding.eventsRecycler.visibility = View.GONE
            } else {
                binding.emptyState.visibility = View.GONE
                binding.eventsRecycler.visibility = View.VISIBLE
                adapter.submit(events)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CALENDAR_URL = "https://calendar.google.com"
    }
}
