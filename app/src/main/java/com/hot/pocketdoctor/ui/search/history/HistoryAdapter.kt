package com.hot.pocketdoctor.ui.search.history

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.data.datasource.search.local.History
import com.hot.pocketdoctor.databinding.ItemSearchHistoryBinding
import com.hot.pocketdoctor.databinding.ItemSearchHistoryHeaderBinding

class HistoryAdapter(val historyDeleteClickedListener: (String) -> Unit) : ListAdapter<History, RecyclerView.ViewHolder>(diffUtil) {

    private lateinit var headerBinding: ItemSearchHistoryHeaderBinding
    private lateinit var historyBinding: ItemSearchHistoryBinding

    override fun getItemViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_CONTENT = 1

        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                TODO("Not yet implemented")
            }
        }
    }
}