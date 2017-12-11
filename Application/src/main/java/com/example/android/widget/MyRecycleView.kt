package com.example.android.widget

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View

import com.example.android.callback.OnHeadFooterListener
import com.example.android.callback.OnLoadMoreListener

/**
 * Created by MaZhihua on 2017/12/7.
 * 列表控件，持续加功能中....注：不需要你的要求，你可以继承该类，重写
 */
class MyRecycleView : RecyclerView {

    /**
     * 日志
     */
    private val DEBUG = false

    /**
     * 是否正在加载
     */
    private var isLoading = false

    /**
     * 是否自动加载下一页
     */
    private var isAutoLoadMore = true

    /**
     * 是否需要请求焦点，滚动停止后，如果是翻了一页的话，需要主动请求焦点
     */
    private var ifNeedRequestFocus = false

    /**
     * 要请求焦点的item是否是第一项，true：表示用户执行了上一页操作，false:表示用户执行了下一页操作
     */
    private var ifRequestFocusFirstItem = false

    /**
     * 加载更多回调
     */
    private var mOnLoadMoreListener: OnLoadMoreListener? = null

    /**
     * 该列表控件滚动到最前或者最后的回调
     */
    var mOnHeadFooterListener: OnHeadFooterListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        when (state) {

            RecyclerView.SCROLL_STATE_IDLE//已停止滑动
            -> {

                if (isAutoLoadMore && !isLoading) { //已启动自动加载更多，并且当前处于未加载状态
                    if (childCount <= findLastVisibleItemPosition()) {
                        if (mOnLoadMoreListener != null) {
                            if (DEBUG) {
                                Log.d("KKK", "IDLE =========")
                            }
                            mOnLoadMoreListener!!.onLoadMore()
                        }
                        isLoading = true
                    }
                }

                if (ifNeedRequestFocus) { //用户在滑动到最后一个item或者最前一个item是执行翻页逻辑，则执行这里
                    val view = findViewByPosition(if (ifRequestFocusFirstItem) findFirstCompletelyVisibleItemPosition() else findLastCompletelyVisibleItemPosition())
                    if (view != null) {
                        view.requestFocus()
                        resetFocusConstant()
                    }
                }
            }


            RecyclerView.SCROLL_STATE_DRAGGING // 还在滚动的时候
            -> {
            }


            else -> {
            }
        }

    }

    /**
     * 设置加载更多回调
     * @param mOnLoadMoreListener
     */
    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    /**
     * 设置滚动到最前或者最后的回调
     * @param mOnHeadFooterListener
     */
    fun setOnHeadFooterListener(mOnHeadFooterListener: OnHeadFooterListener) {
        this.mOnHeadFooterListener = mOnHeadFooterListener
    }

    /**
     * 请求第一个可以完全看见的item的焦点
     */
    fun requestFirstItemFocus() {
        this.ifNeedRequestFocus = true
        this.ifRequestFocusFirstItem = true
    }

    /**
     * 请求最后一个可以完全看见的item的焦点
     */
    fun requestLastItemFocus() {
        this.ifNeedRequestFocus = true
        this.ifRequestFocusFirstItem = false
    }

    /**
     * 重置请求焦点相关的变量
     */
    fun resetFocusConstant() {
        this.ifNeedRequestFocus = false
        this.ifRequestFocusFirstItem = false
    }

    /**
     * 获取最后一荐可见item position
     * @return
     */
    fun findLastVisibleItemPosition(): Int {
        val layoutManager = layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            return layoutManager.findLastVisibleItemPosition()
        }
        return 0
    }

    /**
     * 获取第一个完全显示的item位置
     * @return
     */
    fun findFirstCompletelyVisibleItemPosition(): Int {
        val layoutManager = layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.findFirstCompletelyVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            return layoutManager.findFirstCompletelyVisibleItemPosition()
        }
        return 0
    }

    /**
     * 获取最后一个完全显示的item位置
     * @return
     */
    fun findLastCompletelyVisibleItemPosition(): Int {
        val layoutManager = layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.findLastCompletelyVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            return layoutManager.findLastCompletelyVisibleItemPosition()
        }
        return 0
    }

    /**
     * 根据位置获取View
     * @return
     */
    fun findViewByPosition(postition: Int): View? {
        val layoutManager = layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.findViewByPosition(postition)
        } else if (layoutManager is LinearLayoutManager) {
            return layoutManager.findViewByPosition(postition)
        }
        return null
    }


    /**
     * 设置是否自动加载更多
     * @param isAutoLoadMore, true:自动加载，false:不自动加载
     */
    fun setAutoLoadMoreEnable(isAutoLoadMore: Boolean) {
        this.isAutoLoadMore = isAutoLoadMore
    }


    /**
     * 加载完成
     */
    fun loadComplete() {
        isLoading = false
    }
}
