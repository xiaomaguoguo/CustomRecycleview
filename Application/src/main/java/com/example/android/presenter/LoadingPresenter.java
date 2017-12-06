package com.example.android.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.recyclerview.R;

/**
 * Created by MaZhihua on 2017/12/6.
 */

public class LoadingPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new Loading(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    static class Loading extends ViewHolder{
        private ProgressBar pb;
        public Loading(View view) {
            super(view);
            pb = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}
