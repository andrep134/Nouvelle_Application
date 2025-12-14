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
import com.example.eebb.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val email = binding.registerEmail.text?.toString()?.trim().orEmpty()
            val password = binding.registerPassword.text?.toString()?.trim().orEmpty()
            val confirm = binding.registerConfirm.text?.toString()?.trim().orEmpty()
            if (email.isBlank() || password.isBlank() || confirm.isBlank()) {
                Toast.makeText(requireContext(), getString(R.string.auth_missing_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirm) {
                Toast.makeText(requireContext(), getString(R.string.auth_password_mismatch), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val user = AuthManager.register(requireContext(), email, password)
                if (user != null) {
                    findNavController().navigateUp()
                }
            }
        }

        binding.goToLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
