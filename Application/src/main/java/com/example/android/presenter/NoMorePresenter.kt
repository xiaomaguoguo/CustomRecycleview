package com.example.android.presenter

import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.android.recyclerview.R

/**
 * Created by MaZhihua on 2017/12/6.
 */
class NoMorePresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_nomore, parent, false))
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {

    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {

    }

    internal class MyViewHolder(view: View) : Presenter.ViewHolder(view) {
        var iText: TextView

        init {
            iText = view.findViewById<View>(R.id.textView) as TextView
        }
    }
}
