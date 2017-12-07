package com.example.android.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.common.logger.Log;
import com.example.android.model.Item;
import com.example.android.recyclerview.R;

/**
 * Created by MaZhihua on 2017/12/6.
 */

public class NormalPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new NormalItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        NormalItem normalItem = (NormalItem) viewHolder;
        Item item1 = (Item) item;
        normalItem.getTextView().setText(item1.getI()+" AAAAAAA");
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    static class NormalItem extends ViewHolder{
        public static final String TAG = NormalItem.class.getSimpleName();
        private final TextView textView;

        public NormalItem(View v) {
            super(v);
//                v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) { Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                }
//            });
            textView = (TextView) v.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }

}
