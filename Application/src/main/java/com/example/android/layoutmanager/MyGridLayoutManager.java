package com.example.android.layoutmanager;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.android.utils.PageUtils;
import com.example.android.widget.MyRecycleView;

/**
 * Created by MaZhihua on 2017/12/7.
 */

public class MyGridLayoutManager extends GridLayoutManager{

    /**
     * 日志开关
     */
    private boolean DEBUG = true;

    public static final String TAG = MyGridLayoutManager.class.getSimpleName();

    /**
     * 是否滚动到最后一个显示一半的item时直接翻页,true:是，false:否
     */
    private boolean isAutoSkipNexPrePage = true;

    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    /**
     * 设置是否滚动到最后一个显示一半的item时直接翻页,true:是，false:否
     */
    public void setEnableAutoSkipNextPrePage(boolean enable){
        this.isAutoSkipNexPrePage = enable;
    }

    @Override
    public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate) {
        if (DEBUG){
            Log.d(TAG,"这个好像没调用过");
        }
        return super.requestChildRectangleOnScreen(parent, child, rect, immediate);
    }

    @Override
    public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {

        if(isAutoSkipNexPrePage){ //滚动至下一页

            int firstItemPosition = findFirstVisibleItemPosition();
            int lastItemPosition = findLastVisibleItemPosition();
            View lastItem = findViewByPosition(lastItemPosition);
            View firstItem = findViewByPosition(firstItemPosition);
            int position = getPosition(child);

            if(position >= findLastCompletelyVisibleItemPosition() && child != findViewByPosition(findLastCompletelyVisibleItemPosition()) || child == lastItem){
                PageUtils.INSTANCE.nextPage((MyRecycleView) parent);
                if(DEBUG){
                    Log.d(TAG,"直接进入下一页");
                }
            }else if(position <= findFirstCompletelyVisibleItemPosition() && child != findViewByPosition(findFirstCompletelyVisibleItemPosition()) || child == firstItem){
                PageUtils.INSTANCE.prePage((MyRecycleView) parent);
                if(DEBUG){
                    Log.d(TAG,"直接进入上一页");
                }
            }

            if(DEBUG){
                int lastVisiblePosition = findLastCompletelyVisibleItemPosition();
                Log.d(TAG,"lastVisiblePosition = " +lastVisiblePosition + "; lastItemPosition= " + lastItemPosition + ";focusedChildVisible = " + focusedChildVisible);
            }

        }
        return isAutoSkipNexPrePage || super.requestChildRectangleOnScreen(parent, child, rect, immediate, focusedChildVisible);
    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }*/
}
