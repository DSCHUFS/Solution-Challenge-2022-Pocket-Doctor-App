package com.hot.pocketdoctor.presentation.treatment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hot.pocketdoctor.databinding.ActivityTreatmentListBinding

class TreatmentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTreatmentListBinding

    private val viewPager: ViewPager2 by lazy { binding.treatmentListViewPager }
    private val tabLayout: TabLayout by lazy { binding.treatmentListTabLayout }

    private lateinit var treatmentList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreatmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewPager()
        setTabLayout()
    }

    private fun setViewPager() {
        treatmentList = listOf(
            "Melancholia",
            "Panic Disorder",
            "Dementia",
            "Eating Disorder",
            "Insomnia",
            "OCD",
            "Anxiety",
            "Etc"
        )

        val treatmentAdapter = TreatmentPagerAdapter(treatmentList)
        viewPager.adapter = treatmentAdapter
    }

    private fun setTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = treatmentList[position]
        }.attach()
    }
}