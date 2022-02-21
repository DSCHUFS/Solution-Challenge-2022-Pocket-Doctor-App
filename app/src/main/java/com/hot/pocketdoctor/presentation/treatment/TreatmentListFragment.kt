package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.presentation.treatment.adapter.TreatmentListAdapter

class TreatmentListFragment : Fragment() {

    private var _binding: FragmentTreatmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var treatmentListAdapter: TreatmentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTreatmentListBinding.inflate(inflater, container, false)

        initRecyclerView()

        val view = binding.root
        return view
    }

    private fun initRecyclerView() {
        treatmentListAdapter = TreatmentListAdapter()
        binding.rvTreatmentList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = treatmentListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}