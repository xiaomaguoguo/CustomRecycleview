package com.example.android.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.recyclerview.R;

/**
 * Created by MaZhihua on 2017/12/6.
 */
public class EmptyPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    static class MyViewHolder extends ViewHolder {
        public TextView iText;
        MyViewHolder(View view) {
            super(view);
            iText = (TextView) view.findViewById(R.id.textView);
        }
    }
}
