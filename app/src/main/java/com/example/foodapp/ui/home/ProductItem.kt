package com.example.foodapp.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductItem(
    val name: String,
    val price: Double,
    val imageRes: Int,
    var isLiked: Boolean
): Parcelable
