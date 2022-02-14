package com.hot.pocketdoctor.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(action: Int) {
    this.findNavController().navigate(action)
}