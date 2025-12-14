package com.example.eebb.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eebb.databinding.FragmentMediaBinding
import com.example.eebb.ui.common.openLink

class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[MediaViewModel::class.java] }
    private lateinit var adapter: SermonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SermonAdapter()
        binding.mediaRecycler.adapter = adapter

        viewModel.sermons.observe(viewLifecycleOwner) { sermons ->
            if (sermons.isEmpty()) {
                binding.mediaEmpty.visibility = View.VISIBLE
                binding.mediaRecycler.visibility = View.GONE
            } else {
                binding.mediaEmpty.visibility = View.GONE
                binding.mediaRecycler.visibility = View.VISIBLE
                adapter.submit(sermons)
                sermons.firstOrNull()?.let { hero ->
                    binding.mediaHeroTitle.text = hero.title
                    binding.mediaHeroMeta.text = "${hero.speaker} • ${hero.duration}"
                }
            }
        }

        binding.watchNowButton.setOnClickListener { openLink(YOUTUBE_URL) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/@egliseevangeliquebaptisteb4650/featured"
    }
}
