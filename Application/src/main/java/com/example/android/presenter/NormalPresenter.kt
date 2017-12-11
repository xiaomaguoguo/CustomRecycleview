package com.example.android.presenter

import android.graphics.Color
import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import com.example.android.common.logger.Log
import com.example.android.model.Item
import com.example.android.recyclerview.R

/**
 * Created by MaZhihua on 2017/12/6.
 */

class NormalPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        view.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus -> view.setBackgroundColor(if (hasFocus) Color.parseColor("#eeaabb") else Color.parseColor("#334599")) }
        return NormalItem(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val normalItem = viewHolder as NormalItem
        val item1 = item as Item
        normalItem.textView.text = item1.i.toString() + " AAAAAAA"
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {

    }

    internal class NormalItem(v: View) : Presenter.ViewHolder(v) {
        val textView: TextView

        init {
            //                v.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) { Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
            //                }
            //            });
            textView = v.findViewById<View>(R.id.textView) as TextView
        }

        companion object {
            val TAG = NormalItem::class.java.simpleName
        }
    }

}
