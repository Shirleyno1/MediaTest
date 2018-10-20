package com.shirley.videocatalogue

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shirley.videocatalogue.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var categoryViewModel: ShiftViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SBSApplication).appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        categoryViewModel = ViewModelProviders.of(this,viewModeFactory)
                .get(ShiftViewModel::class.java)
        categoryViewModel.getAllShifts()
    }
}
