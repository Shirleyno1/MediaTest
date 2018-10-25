package com.shirley.videocatalogue.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import android.view.WindowManager
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

        if (hasNavigationBar(requireContext())) {
            val window = dialog.window
            if (window != null) {
                val decorView = window.decorView
                decorView.setOnSystemUiVisibilityChangeListener {
                    val resources = requireContext().resources
                    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
                    if (resourceId > 0) {
                        val p = view.layoutParams as ViewGroup.MarginLayoutParams

                        p.setMargins(0, getStatusBarHeight(requireContext()), p.rightMargin, 0)
                        view.requestLayout()
                    }
                }
            }

        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        dialog.window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE//solves issue with statusbar
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

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun hasNavigationBar(context: Context): Boolean {
        var hasNavigationBar = false
        val rs = context.resources
        val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id)
        }
        try {
            //todo Warning: Accessing internal APIs via reflection is not supported and may not work on all devices or in the future
            val systemPropertiesClass = Class.forName("android.os.SystemProperties")
            val m = systemPropertiesClass.getMethod("get", String::class.java)
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
            if ("1" == navBarOverride) {
                hasNavigationBar = false
            } else if ("0" == navBarOverride) {
                hasNavigationBar = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return hasNavigationBar
    }
}
