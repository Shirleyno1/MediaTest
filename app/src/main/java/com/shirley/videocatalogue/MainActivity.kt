package com.shirley.videocatalogue

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.shirley.videocatalogue.fragment.CategoryListFragment

class MainActivity : FragmentActivity() {

    val categoryListFragment = CategoryListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frameLayout, categoryListFragment).commit()
        }

    }
}
