package com.example.android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.recyclerview.R;

/**
 * Created by KNothing on 2017/12/6.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder  {
    public ProgressBar progressBar;
    public HeaderViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
    }
}
