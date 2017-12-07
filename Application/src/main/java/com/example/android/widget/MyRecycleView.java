package com.example.android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.android.callback.OnLoadMoreListener;

/**
 * Created by MaZhihua on 2017/12/7.
 */

public class MyRecycleView extends RecyclerView {

    private boolean isLoading = false;

    private OnLoadMoreListener mOnLoadMoreListener;

    public MyRecycleView(Context context) {
        super(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return super.requestChildRectangleOnScreen(child, rect, immediate);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == SCROLL_STATE_IDLE){
            if (!isLoading && getChildCount() <= (findLastVisibleItemPosition() + 1)) {
                if (mOnLoadMoreListener != null) {
                    Log.d("KKK","IDLE =========");
                    mOnLoadMoreListener.onLoadMore();
                }
                isLoading = true;
            }
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public int findLastVisibleItemPosition(){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
        }else if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
        }
        return 0;
    }

    public void setLoaded() {
        isLoading = false;
    }
}
