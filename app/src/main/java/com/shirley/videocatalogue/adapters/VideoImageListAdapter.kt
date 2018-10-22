package com.shirley.videocatalogue.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.data.VideoItem
import com.shirley.videocatalogue.databinding.ItemLandscapeBinding
import com.shirley.videocatalogue.databinding.ItemPortraitBinding
import com.shirley.videocatalogue.fragment.CategoryListFragment
import com.shirley.videocatalogue.fragment.VideoItemDetailFragment

class VideoImageListAdapter(val context: CategoryListFragment, var data: List<VideoItem>, var isFeature: Boolean = false) : RecyclerView.Adapter<VideoImageListAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as VideoItem

                val fragment = VideoItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(VideoItemDetailFragment.VIDEO_ITEM_OBJECT, item)
                    }
                }//android:style/Theme.Holo.Light.NoActionBar
            fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog)
            fragment.show(context.requireFragmentManager(),"")

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout = R.layout.item_portrait
        if (isFeature) {
            layout = R.layout.item_landscape
        }
        val itemView = LayoutInflater.from(parent.context).inflate(layout, FrameLayout(parent.context), false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val binding: ViewDataBinding? = DataBindingUtil.bind(holder.itemView)
        with(data.get(position)) {
            if (isFeature) {
                (binding as ItemLandscapeBinding).item = this
            }
            else {
                (binding as ItemPortraitBinding).item = this
            }
            with(holder.itemView) {
                tag = data.get(position)
                setOnClickListener(onClickListener)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}