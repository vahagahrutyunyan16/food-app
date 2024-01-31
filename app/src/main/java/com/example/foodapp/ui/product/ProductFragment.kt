package com.example.foodapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentProductBinding
import com.example.foodapp.ui.home.ProductItem


class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)

        observeFragmentResult()
        binding.ivPlus.setOnClickListener {
            count++
            binding.tvCount.text = count.toString()

            if (count > 0) {
                binding.ivMinus.setColorFilter(binding.ivMinus.resources.getColor(R.color.ufo_green))
            }else{
                binding.ivMinus.setColorFilter(binding.ivMinus.resources.getColor(R.color.silver_sand))
            }
        }

        binding.ivMinus.setOnClickListener {
            if (count > 0) {
                count--
                binding.tvCount.text = count.toString()
            }
            if (count > 0) {
                binding.ivMinus.setColorFilter(binding.ivMinus.resources.getColor(R.color.ufo_green))
            }else{
                binding.ivMinus.setColorFilter(binding.ivMinus.resources.getColor(R.color.silver_sand))
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        setFragmentResultListener("countKey") { requestKey, bundle ->
            val result = bundle.getInt("bundleKey")

            binding.tvCount.text = result.toString()
        }

        return binding.root
    }

    private fun observeFragmentResult() {
        setFragmentResultListener("productKey") { requestKey, bundle ->
            val result = bundle.getParcelable<ProductItem>("bundleKey")

            result ?: return@setFragmentResultListener
            binding.tvName.text = result.name
            binding.tvPrice.text = "$ " + result.price.toString()
            binding.ivImage.setImageResource(result.imageRes)
        }
    }
}