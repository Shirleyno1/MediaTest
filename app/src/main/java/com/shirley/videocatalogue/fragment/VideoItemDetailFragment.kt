package com.shirley.videocatalogue.fragment

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.data.VideoItem
import com.shirley.videocatalogue.databinding.FragmentVideoitemDetailBinding

class VideoItemDetailFragment : DialogFragment() {

    private var videoItem: VideoItem? = null
    lateinit var binding: FragmentVideoitemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(VIDEO_ITEM_OBJECT)) {
                videoItem = it.getSerializable(VIDEO_ITEM_OBJECT) as VideoItem?
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentVideoitemDetailBinding>(inflater, R.layout.fragment_videoitem_detail, container, false)
        binding.close.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.videoItem = videoItem
        if (portrait()) {
            binding.imageUrl = videoItem?.images?.portrait
        } else {
            binding.imageUrl = videoItem?.images?.landscape
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    companion object {
        const val VIDEO_ITEM_OBJECT = "video_item_object"
    }

    fun portrait(): Boolean {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels - displayMetrics.widthPixels > 0
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return super.onCreateAnimation(transit, enter, R.style.DialogAnimation)
    }
}
