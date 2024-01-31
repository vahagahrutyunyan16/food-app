package com.example.foodapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFavoriteBinding
import com.example.foodapp.ui.home.ProductAdapter
import com.example.foodapp.ui.home.ProductItem


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.ivHeart.setColorFilter(ContextCompat.getColor(requireContext(), R.color.ufo_green))

        binding.ivHome.setOnClickListener {
            findNavController().navigate(R.id.actionToHome)
        }

        observeFragmentResult()

        return binding.root
    }

    private fun initAdapter(favoriteProductItems: List<ProductItem>) {
        val adapter = ProductAdapter(
            favoriteProductItems,
            addToList = {
//                addItemToList()
            },
            removeFromList = {
//                removeItemToList()
            },
            showDialog = {},
            onItemClicked = { productItem ->
                setFragmentResult("productKey", bundleOf("bundleKey" to productItem))
                findNavController().navigate(R.id.actionFavoriteToProduct)
            }
        )

        binding.rvProducts.adapter = adapter
    }
    private fun observeFragmentResult() {
        setFragmentResultListener("productListKey") { requestKey, bundle ->
            val result = bundle.getParcelableArrayList<ProductItem>("bundleListKey")

            result ?: return@setFragmentResultListener
            initAdapter(favoriteProductItems = result)
        }
    }

//    fun addItemToList() {
//        setFragmentResultListener("productKey") { requestKey, bundle ->
//            val result = bundle.getParcelable<ProductItem>("bundleKey")
//
//            result ?: return@setFragmentResultListener
//
//            favoriteProductItems.add(result)
//        }
//    }
//
//    fun removeItemToList() {
//        setFragmentResultListener("productKey") { requestKey, bundle ->
//            val result = bundle.getParcelable<ProductItem>("bundleKey")
//
//            result ?: return@setFragmentResultListener
//
//            favoriteProductItems.remove(result)
//        }
//    }

}