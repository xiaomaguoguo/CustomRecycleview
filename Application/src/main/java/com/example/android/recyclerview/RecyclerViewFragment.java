/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.android.User;
import com.example.android.presenter.HeaderPresenter;
import com.example.android.presenter.LoadingPresenter;
import com.example.android.presenter.MyPresenterSelector;
import com.example.android.presenter.NormalPresenter;

import java.util.ArrayList;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class RecyclerViewFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 10;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList mDataset;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        btn = (Button)rootView.findViewById(R.id.btn);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

//        mDataset = new ArrayList();
//        mDataset.add(null);
//        mAdapter = new CustomAdapter(mDataset,mRecyclerView, (LinearLayoutManager) mLayoutManager);
//        buildData();
//        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.linear_layout_rb);
        mLinearLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
            }
        });

        mGridLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.grid_layout_rb);
        mGridLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);
            }
        });

//        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override public void run() {
//                        mAdapter.hideLoading();
//                        buildData();
//                        mAdapter.loadMoreComplete();
//                    }
//                }, 5000);
//            }
//        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < DATASET_COUNT; i++) {
                    User user = new User();
                    user.setEmail("fuck@163.com");
                    user.setName("BBBBB = ");
                    mDataset.add(user);
                }
                mAdapter.notifyItemInserted(mDataset.size()-2);
            }
        });
        MyPresenterSelector presenterSelector = new MyPresenterSelector();
        presenterSelector.addPresenter(0,new HeaderPresenter());
        presenterSelector.addPresenter(1,new NormalPresenter());
        presenterSelector.addPresenter(2,new LoadingPresenter());
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(presenterSelector);
        arrayObjectAdapter.add(0);
        for(int i=0;i<20;i++){
            arrayObjectAdapter.add(1);
        }
        arrayObjectAdapter.add(2);
        mRecyclerView.setAdapter(new ItemBridgeAdapter(arrayObjectAdapter));

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }


    int j=0;
    private void buildData(){
        for (int i = 0; i < DATASET_COUNT; i++) {
            j++;
            User user = new User();
            user.setEmail("fuck@163.com");
            user.setName("KKKK = " + j);
            mDataset.add(user);
            mAdapter.notifyItemInserted(mDataset.size()-2);
        }
//        mAdapter.notifyItemInserted(mDataset.size());
//        mAdapter.notifyDataSetChanged();
//        mAdapter.notifyItemRangeInserted(mDataset.size()-2,10);
    }
}
