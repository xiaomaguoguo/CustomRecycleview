package com.example.android.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

import com.example.android.common.logger.Log
import com.example.android.recyclerview.R

/**
 * Created by KNothing on 2017/12/6.
 */
class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textView: TextView

    init {
        v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
        textView = v.findViewById<View>(R.id.textView) as TextView
    }

    companion object {

        val TAG = ItemViewHolder::class.java.simpleName
    }
}
