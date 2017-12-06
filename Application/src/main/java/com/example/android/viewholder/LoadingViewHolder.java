package com.example.android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.recyclerview.R;

/**
 * Created by KNothing on 2017/12/6.
 */
public class LoadingViewHolder extends RecyclerView.ViewHolder  {
    public ProgressBar progressBar;
    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
    }
}
