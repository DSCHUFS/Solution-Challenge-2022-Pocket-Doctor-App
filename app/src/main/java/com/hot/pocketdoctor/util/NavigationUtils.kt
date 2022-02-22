package com.hot.pocketdoctor.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(action: Int) {
    this.findNavController().navigate(action)
}

fun Fragment.navigateWithData(navDirections: NavDirections) {
    this.findNavController().navigate(navDirections)
}