package com.hot.pocketdoctor.presentation.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMainBinding
import com.hot.pocketdoctor.presentation.home.HomeFragment
import com.hot.pocketdoctor.presentation.mypage.MyPageFragment
import com.hot.pocketdoctor.presentation.reports.ReportsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment by lazy { HomeFragment() }
    private val reportsFragment by lazy { ReportsFragment() }
    private val myPageFragment by lazy { MyPageFragment() }

    private val callFab by lazy { binding.mainCallFab }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavBar()
        setCallFab()
    }

    private fun checkCallPermission() {
        when {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED -> {
                showCallPopUp()
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE) -> {
                showPermissionPopUp()
            }

            else -> {
                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), CALL_REQUEST_CODE)
            }
        }
    }

    private fun showPermissionPopUp() {
        AlertDialog.Builder(this)
            .setTitle("Call Permission")
            .setMessage("We need a Call Permission for dial to 1393")
            .setPositiveButton("Accept") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), CALL_REQUEST_CODE)
            }
            .setNegativeButton("Deny") { _, _ -> }
            .create()
            .show()
    }

    private fun showCallPopUp() {
        AlertDialog.Builder(this)
            .setTitle("Call 1393")
            .setPositiveButton("Accept") { _, _ ->
                moveToDial()
            }
            .setNegativeButton("Deny") { _, _ -> }
            .create()
            .show()
    }


    private fun moveToDial() {
        val phoneNumber = "1393"
        val uri = Uri.parse("tel:${phoneNumber}")
        val callIntent = Intent(Intent.ACTION_CALL, uri)
        startActivity(callIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CALL_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCallPopUp()
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
            else -> {  }
        }
    }
    private fun setCallFab() {
        callFab.setOnClickListener {
            checkCallPermission()
        }
    }

    private fun initBottomNavBar() {
        binding.bottomNavigationMain.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> setCurrentFragment(homeFragment)
                    R.id.menu_reports -> setCurrentFragment(reportsFragment)
                    R.id.menu_myPage -> setCurrentFragment(myPageFragment)
                }
                true

            }
            selectedItemId = R.id.menu_home
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_frameLayout, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            commit()
        }
    }

    companion object {
        const val CALL_REQUEST_CODE = 1000
    }
}