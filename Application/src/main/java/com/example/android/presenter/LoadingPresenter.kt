package com.example.android.presenter

import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.android.recyclerview.R

/**
 * Created by MaZhihua on 2017/12/6.
 */

class LoadingPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        return Loading(LayoutInflater.from(parent.context).inflate(R.layout.layout_loading_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {

    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {

    }

    internal class Loading(view: View) : Presenter.ViewHolder(view) {
        private val pb: ProgressBar

        init {
            pb = view.findViewById<View>(R.id.progressBar1) as ProgressBar
        }
    }
}
