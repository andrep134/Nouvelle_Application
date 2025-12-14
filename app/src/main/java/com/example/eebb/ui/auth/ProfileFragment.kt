package com.example.eebb.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eebb.R
import com.example.eebb.auth.AuthManager
import com.example.eebb.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = AuthManager.currentUser()
        if (user == null) {
            findNavController().navigate(R.id.nav_login)
            return
        }
        binding.profileEmail.text = getString(R.string.auth_logged_in_as) + "\n" + (user.email ?: "")

        binding.logoutButton.setOnClickListener {
            AuthManager.signOut()
            findNavController().navigate(R.id.nav_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
