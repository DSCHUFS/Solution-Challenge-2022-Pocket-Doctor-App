package com.hot.pocketdoctor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMainBinding
import com.hot.pocketdoctor.databinding.ItemReportsRecyclerBinding
import com.hot.pocketdoctor.ui.home.HomeFragment
import com.hot.pocketdoctor.ui.mypage.MyPageFragment
import com.hot.pocketdoctor.ui.reports.Reports
import com.hot.pocketdoctor.ui.reports.ReportsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment by lazy { HomeFragment() }
    private val reportsFragment by lazy { ReportsFragment() }
    private val myPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavBar()
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
}