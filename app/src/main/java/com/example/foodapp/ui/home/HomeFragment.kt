package com.example.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productItems = listOf(
        ProductItem("Fish Curry", 10.0, R.drawable.unsplash_4, false),
        ProductItem("Beef Pizza", 15.0, R.drawable.unsplash_3, true),
        ProductItem("Vagetale salad", 5.0, R.drawable.unsplash_2, false),
        ProductItem("Chicken Ball", 25.0, R.drawable.unsplash_1, false),
    )

    private val adapter = ProductAdapter(
        dataList = productItems,
        addToList = {
            productItems[it].isLiked = true

        },
        removeFromList = {
            productItems[it].isLiked = false
        },
        showDialog = {
            showDialog()
        },
        onItemClicked = { productItem ->
            setFragmentResult("productKey", bundleOf("bundleKey" to productItem))
            findNavController().navigate(R.id.actionToDetails)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.ivHome.setColorFilter(ContextCompat.getColor(requireContext(), R.color.ufo_green))
        binding.rvProducts.adapter = adapter

        setFragmentResult("countKey", bundleOf("bundleKey" to adapter.count))


        binding.ivHeart.setOnClickListener {
            setFragmentResult("productListKey", bundleOf("bundleListKey" to productItems))
            findNavController().navigate(R.id.actionToFavorite)
        }


        return binding.root
    }

    private fun showDialog() {
        if (adapter.count > 0) {
            binding.lDialog.visibility = LinearLayout.VISIBLE
            binding.tvCount.text = adapter.count.toString()
            binding.tvPrice.text = "$ " + adapter.price.toString()
        }
        if (adapter.count == 0) {
            binding.lDialog.visibility = LinearLayout.INVISIBLE
        }
    }
}