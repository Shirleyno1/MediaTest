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
import com.shirley.videocatalogue.CategoryViewModel
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.SBSApplication
import com.shirley.videocatalogue.adapters.CategoryListAdapter
import com.shirley.videocatalogue.databinding.FragmentCategoryListBinding
import javax.inject.Inject

class CategoryListFragment : Fragment() {
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var binding: FragmentCategoryListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false)
        (activity?.application as SBSApplication).appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel = ViewModelProviders.of(this, viewModeFactory)
                .get(CategoryViewModel::class.java)
        categoryViewModel.getAllCategories()
        binding.list.adapter = CategoryListAdapter(this, ArrayList())
        categoryViewModel.categories.observe(this, Observer {
            it?.let { list ->
                (binding.list.adapter as CategoryListAdapter).data = list.sorted()
                binding.list.adapter.notifyDataSetChanged()
            }
        })
    }

    companion object {
        fun newInstance(): CategoryListFragment {
            return CategoryListFragment()
        }
    }
}