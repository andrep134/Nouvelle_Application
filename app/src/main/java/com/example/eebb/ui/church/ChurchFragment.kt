package com.example.eebb.ui.church

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eebb.R
import com.example.eebb.databinding.FragmentChurchBinding
import com.example.eebb.databinding.ItemHighlightBinding
import com.example.eebb.ui.common.composeEmail
import com.example.eebb.ui.common.openLink
import com.example.eebb.ui.common.openLocation

class ChurchFragment : Fragment() {

    private var _binding: FragmentChurchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[ChurchViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChurchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.visionText.text = getString(R.string.church_vision)
        binding.missionText.text = getString(R.string.church_mission)
        binding.scheduleText.text = getString(R.string.church_schedule)
        binding.addressText.text = getString(R.string.church_address)
        binding.contactText.text = getString(R.string.church_contact)

        viewModel.ministries.observe(viewLifecycleOwner) { ministries ->
            binding.ministriesContainer.removeAllViews()
            ministries.forEach { ministry ->
                val cardBinding = ItemHighlightBinding.inflate(layoutInflater, binding.ministriesContainer, false)
                cardBinding.highlightTitle.text = ministry.name
                cardBinding.highlightDetail.text = ministry.focus
                binding.ministriesContainer.addView(cardBinding.root)
            }
        }

        binding.donateButton.setOnClickListener { openLink(DONATION_URL) }
        binding.addressText.setOnClickListener { openLocation(getString(R.string.location_query)) }
        binding.contactText.setOnClickListener { composeEmail(getString(R.string.contact_email), getString(R.string.email_subject)) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val DONATION_URL = "https://www.helloasso.com/associations/eglise-bethesda"
    }
}
