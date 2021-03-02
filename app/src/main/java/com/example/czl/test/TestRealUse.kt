package com.example.czl.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.example.czl.R

/**kotlin实战使用*/
class TestRealUse() {

    companion object {
        fun addDetailView(context: Context?, parentView: ViewGroup) {
            var detailView = LayoutInflater.from(context).inflate(R.layout.detail_item_layout, parentView, false)
            parentView.addView(detailView)
            var detail_item_layout_title =
                detailView.findViewById<TextView>(R.id.detail_item_layout_title)
            var detail_item_layout_del =
                detailView.findViewById<TextView>(R.id.detail_item_layout_del)
            detail_item_layout_del.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    removeDetailView(p0, parentView)
                    updateDetailTitle(parentView)
                }
            })
            updateDetailTitle(parentView)
        }

        private fun updateDetailTitle(parentView: ViewGroup) {
            for (index in 0..parentView.childCount) {
                val child = parentView.getChildAt(index)
                val detail_item_layout_title =
                    child?.findViewById<TextView>(R.id.detail_item_layout_title)
                detail_item_layout_title?.setText("报销明细${index + 1}")
            }
        }

        private fun removeDetailView(view: View?, parentView: ViewGroup) {
            val detailView = view?.parent?.parent
            if (detailView != null) {
                for (item in parentView.children) {
                    if (item === detailView) {
                        parentView.removeView(detailView)
                        break
                    }
                }
            }
        }
    }
}