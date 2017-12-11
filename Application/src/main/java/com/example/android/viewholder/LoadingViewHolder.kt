package com.example.android.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

import com.example.android.recyclerview.R

/**
 * Created by KNothing on 2017/12/6.
 */
class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var progressBar: ProgressBar

    init {
        progressBar = itemView.findViewById<View>(R.id.progressBar1) as ProgressBar
    }
}
