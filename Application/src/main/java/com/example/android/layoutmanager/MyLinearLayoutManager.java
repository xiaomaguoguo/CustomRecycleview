package com.example.android.layoutmanager;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.android.utils.PageUtils;
import com.example.android.widget.MyRecycleView;

/**
 * Created by MaZhihua on 2017/12/7.
 */
public class MyLinearLayoutManager extends LinearLayoutManager{

    private boolean DEBUG = false;

    public static final String TAG = MyLinearLayoutManager.class.getSimpleName();

    /**
     * 是否滚动到最后一个显示一半的item时直接翻页,true:是，false:否
     */
    private boolean isAutoSkipNexPrePage = true;

    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置是否滚动到最后一个显示一半的item时直接翻页,true:是，false:否
     */
    public void setEnableAutoSkipNextPrePage(boolean enable){
        this.isAutoSkipNexPrePage = enable;
    }

    @Override
    public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {

        if(isAutoSkipNexPrePage){

            int firstItemPosition = findFirstVisibleItemPosition();
            int lastItemPosition = findLastVisibleItemPosition();
            View lastItem = findViewByPosition(lastItemPosition);
            View firstItem = findViewByPosition(firstItemPosition);

            if (child == lastItem ){//滚动至下一页
                PageUtils.INSTANCE.nextPage((MyRecycleView) parent);
                if(DEBUG){
                    Log.d(TAG,"直接进入下一页");
                }
            }else if (child == firstItem) { //滚动至上一页
                PageUtils.INSTANCE.prePage((MyRecycleView) parent);
                if(DEBUG){
                    Log.d(TAG,"直接进入上一页");
                }
            }

            if(DEBUG){
                int lastVisiblePosition = findLastCompletelyVisibleItemPosition();
                Log.d(TAG,"requestChild222 lastVisiblePosition = " +lastVisiblePosition + "; lastItemPosition= " + lastItemPosition);
            }

        }
        return isAutoSkipNexPrePage || super.requestChildRectangleOnScreen(parent, child, rect, immediate, focusedChildVisible);
    }
}
