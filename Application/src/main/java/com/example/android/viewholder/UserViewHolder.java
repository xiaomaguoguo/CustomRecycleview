package com.example.android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.recyclerview.R;

/**
 * Created by KNothing on 2017/12/6.
 */
public class UserViewHolder extends RecyclerView.ViewHolder  {
    public TextView tvName;
    public UserViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.name);
    }
}
