package com.example.android.presenter

import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.PresenterSelector
import android.util.ArrayMap

import java.util.ArrayList

/**
 * Created by MaZhihua on 2017/12/6.
 * 支持的item类型在这些配置
 */
class MyPresenterSelector : PresenterSelector() {

    /**
     * 支持的Presenter类型存储在这里
     */
    private var mPresenters: ArrayMap<Any, Presenter>? = null

    init {
        this.mPresenters = ArrayMap(3)

    }

    /**
     * 配置支持的Presenter类型
     */
    fun configSupportType(clazz: Class<*>, presenter: Presenter) {
        if (!mPresenters!!.containsKey(clazz)) {
            mPresenters?.put(clazz, presenter)
        }
    }

    /**
     * 移除支持的Presenter类型
     */
    fun removeConfig(clazz: Class<*>) {
        if (mPresenters!!.containsKey(clazz)) {
            mPresenters!!.remove(clazz)
        }
    }

    /**
     * 获取对应的Presenter
     */
    override fun getPresenter(item: Any): Presenter? {
        return mPresenters!![item.javaClass]
    }
}
