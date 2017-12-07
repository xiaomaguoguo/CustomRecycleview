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

    /**
     * 是否正在加载
     */
    private boolean isLoading = false;

    /**
     * 是否自动加载下一页
     */
    private boolean isAutoLoadMore = true;

    /**
     * 加载更多回调
     */
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
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == SCROLL_STATE_IDLE && isAutoLoadMore){
            if (!isLoading && getChildCount() <= (findLastVisibleItemPosition())) {
                if (mOnLoadMoreListener != null) {
                    Log.d("KKK","IDLE =========");
                    mOnLoadMoreListener.onLoadMore();
                }
                isLoading = true;
            }
        }
    }

    /**
     * 设置加载更多回调
     * @param mOnLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    /**
     * 查找最后一荐可见item position
     * @return
     */
    public int findLastVisibleItemPosition(){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
        }else if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
        }
        return 0;
    }


    /**
     * 加载完成
     */
    public void loadComplete() {
        isLoading = false;
    }

    /**
     * 设置是否自动加载更多
     * @param isAutoLoadMore, true:自动加载，false:不自动加载
     */
    public void setAutoLoadMoreEnable(boolean isAutoLoadMore){
        this.isAutoLoadMore = isAutoLoadMore;
    }
}
