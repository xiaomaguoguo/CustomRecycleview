package com.example.android.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.util.ArrayMap;

import java.util.ArrayList;

/**
 * Created by MaZhihua on 2017/12/6.
 */

public class MyPresenterSelector extends PresenterSelector {

    private ArrayMap<Object,Presenter> mPresenters = null;

    public static final int VIEW_TYPE_HEADER = 0;

    public static final int VIEW_TYPE_ITEM = 1;

    public static final int VIEW_TYPE_LOADING = 2;

    public static final int VIEW_TYPE_FOOTER = 3;

    public MyPresenterSelector() {
        this.mPresenters = new ArrayMap<>(3);

    }

    public void addPresenter(Object obj, Presenter presenter) {
        if (!mPresenters.containsKey(obj)) {
            mPresenters.put(obj,presenter);
        }
    }

    @Override
    public Presenter getPresenter(Object item) {
        return mPresenters.get(item);
    }
}
