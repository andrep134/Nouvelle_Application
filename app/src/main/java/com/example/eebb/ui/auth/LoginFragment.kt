package com.example.eebb.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.eebb.R
import com.example.eebb.auth.AuthManager
import com.example.eebb.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text?.toString()?.trim().orEmpty()
            val password = binding.passwordInput.text?.toString()?.trim().orEmpty()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), getString(R.string.auth_missing_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val user = AuthManager.signIn(requireContext(), email, password)
                if (user != null) {
                    findNavController().navigateUp()
                }
            }
        }

        binding.goToRegister.setOnClickListener {
            findNavController().navigate(R.id.nav_register)
        }

        binding.forgotLink.setOnClickListener {
            findNavController().navigate(R.id.nav_forgot)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
