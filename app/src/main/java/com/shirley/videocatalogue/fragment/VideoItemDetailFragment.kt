package com.shirley.videocatalogue.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shirley.videocatalogue.R
import com.shirley.videocatalogue.data.VideoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_videoitem_detail.view.*

class VideoItemDetailFragment : DialogFragment() {

    private var item: VideoItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(VIDEO_ITEM_OBJECT)) {
                item = it.getSerializable(VIDEO_ITEM_OBJECT) as VideoItem?
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_videoitem_detail, container, false)
        item?.let {
            rootView.title.text = it.title
            rootView.year.text = it.year
            rootView.description.text = it.description
            with(if (portrait()) it.images.portrait else it.images.landscape) {
                this.let {
                    Picasso.get()
                            .load(it)
                            .fit()
                            .placeholder(R.drawable.image_default)
                            .into(rootView.image)
                }
            }
        }
        rootView.close.setOnClickListener{
            dismiss()
        }

        return rootView
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
}
