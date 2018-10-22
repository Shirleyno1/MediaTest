package com.shirley.videocatalogue.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.SBSApplication
import com.shirley.videocatalogue.ShiftViewModel
import com.shirley.videocatalogue.adapters.CategoryListAdapter
import com.shirley.videocatalogue.databinding.FragmentCategoryListBinding
import javax.inject.Inject

class CategoryListFragment : Fragment() {
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var categoryViewModel: ShiftViewModel
    private lateinit var binding: FragmentCategoryListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false)
        (activity?.application as SBSApplication).appComponent.inject(this)
        categoryViewModel = ViewModelProviders.of(this, viewModeFactory)
                .get(ShiftViewModel::class.java)
        categoryViewModel.getAllShifts()
        binding.list.adapter = CategoryListAdapter(this, ArrayList())
        categoryViewModel.categories.observe(this, Observer {
            it?.let {  list ->
                (binding.list.adapter as CategoryListAdapter).data = list.sorted()
                binding.list.adapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    companion object {
        fun newInstance(): CategoryListFragment {
            return CategoryListFragment()
        }
    }
}