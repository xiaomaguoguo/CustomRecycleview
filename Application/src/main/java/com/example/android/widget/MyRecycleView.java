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
 * 列表控件，持续加功能中....注：不需要你的要求，你可以继承该类，重写
 */
public class MyRecycleView extends RecyclerView {

    /**
     * 日志
     */
    private boolean DEBUG = false;

    /**
     * 是否正在加载
     */
    private boolean isLoading = false;

    /**
     * 是否自动加载下一页
     */
    private boolean isAutoLoadMore = true;

    /**
     * 是否需要请求焦点，滚动停止后，如果是翻了一页的话，需要主动请求焦点
     */
    private boolean ifNeedRequestFocus = false;

    /**
     * 要请求焦点的item是否是第一项，true：表示用户执行了上一页操作，false:表示用户执行了下一页操作
     */
    private boolean ifRequestFocusFirstItem = false;

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
    public final void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        switch (state){

            case SCROLL_STATE_IDLE://已停止滑动

                if (isAutoLoadMore && !isLoading ){ //已启动自动加载更多，并且当前处于未加载状态
                    if (getChildCount() <= (findLastVisibleItemPosition())) {
                        if (mOnLoadMoreListener != null) {
                            if (DEBUG){
                                Log.d("KKK","IDLE =========");
                            }
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }

                if(ifNeedRequestFocus){ //用户在滑动到最后一个item或者最前一个item是执行翻页逻辑，则执行这里
                    View view = findViewByPosition(ifRequestFocusFirstItem ? findFirstCompletelyVisibleItemPosition() : findLastCompletelyVisibleItemPosition());
                    if(view != null){
                        view.requestFocus();
                        resetFocusConstant();
                    }
                }

                break;



            default:
                break;
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
     * 请求第一个可以完全看见的item的焦点
     */
    public void requestFirstItemFocus(){
        this.ifNeedRequestFocus = true;
        this.ifRequestFocusFirstItem = true;
    }

    /**
     * 请求最后一个可以完全看见的item的焦点
     */
    public void requestLastItemFocus(){
        this.ifNeedRequestFocus = true;
        this.ifRequestFocusFirstItem = false;
    }

    /**
     * 重置请求焦点相关的变量
     */
    public void resetFocusConstant(){
        this.ifNeedRequestFocus = false;
        this.ifRequestFocusFirstItem = false;
    }

    /**
     * 获取最后一荐可见item position
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
     * 获取第一个完全显示的item位置
     * @return
     */
    public int findFirstCompletelyVisibleItemPosition(){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager)layoutManager).findFirstCompletelyVisibleItemPosition();
        }else if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        return 0;
    }

    /**
     * 获取最后一个完全显示的item位置
     * @return
     */
    public int findLastCompletelyVisibleItemPosition(){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
        }else if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return 0;
    }

    /**
     * 根据位置获取View
     * @return
     */
    public View findViewByPosition(int postition){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager)layoutManager).findViewByPosition(postition);
        }else if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).findViewByPosition(postition);
        }
        return null;
    }



    /**
     * 设置是否自动加载更多
     * @param isAutoLoadMore, true:自动加载，false:不自动加载
     */
    public void setAutoLoadMoreEnable(boolean isAutoLoadMore){
        this.isAutoLoadMore = isAutoLoadMore;
    }


    /**
     * 加载完成
     */
    public void loadComplete() {
        isLoading = false;
    }
}
