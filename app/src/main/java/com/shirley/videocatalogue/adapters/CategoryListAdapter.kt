package com.shirley.videocatalogue.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.data.Category
import com.shirley.videocatalogue.databinding.ItemCategoriesBinding
import com.shirley.videocatalogue.fragment.CategoryListFragment

class CategoryListAdapter(val context: CategoryListFragment, var data: List<Category>) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, FrameLayout(parent.context), false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: ItemCategoriesBinding? = DataBindingUtil.bind(holder.itemView)
        binding?.item = data.get(position)
        binding?.item?.items?.let {
            val itemAdapter = VideoImageListAdapter(context, it,position==0)
            binding.list.adapter = itemAdapter
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}