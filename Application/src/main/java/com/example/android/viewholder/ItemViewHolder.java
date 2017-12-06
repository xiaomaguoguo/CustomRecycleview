package com.example.android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.common.logger.Log;
import com.example.android.recyclerview.R;

/**
 * Created by KNothing on 2017/12/6.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder  {

    public static final String TAG = ItemViewHolder.class.getSimpleName();
    private final TextView textView;

    public ItemViewHolder(View v) {
        super(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
            }
        });
        textView = (TextView) v.findViewById(R.id.textView);
    }
    public TextView getTextView() {
        return textView;
    }
}
