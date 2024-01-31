package com.example.foodapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentSplashBinding
import com.google.android.material.snackbar.Snackbar


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)


        binding.btnGet.setOnClickListener {
            Snackbar.make(binding.btnGet, "This fragment is empty", Snackbar.LENGTH_SHORT)
                .setAction("Retry") {
                    findNavController().navigate(R.id.actionSplashToHome)
                }
                .show()

        }

        return binding.root
    }
}