package com.example.eebb.ui.prayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eebb.R
import com.example.eebb.databinding.FragmentPrayerBinding
import com.example.eebb.ui.common.composeEmail

class PrayerFragment : Fragment() {

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[PrayerViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendPrayerButton.setOnClickListener {
            viewModel.submit()
            composeEmail(
                address = getString(R.string.prayer_email),
                subject = binding.subjectField.text?.toString().orEmpty().ifBlank { getString(R.string.prayer_title) }
            )
        }

        viewModel.submitted.observe(viewLifecycleOwner) { submitted ->
            binding.prayerConfirmation.isVisible = submitted
            if (submitted) {
                binding.subjectField.text = null
                binding.descriptionField.text = null
                binding.anonymousSwitch.isChecked = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
