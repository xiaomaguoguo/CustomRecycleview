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
import android.os.Handler;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.bftv.fui.recycleview.R;
import com.bftv.fui.recycleview.callback.OnLoadMoreListener;
import com.bftv.fui.recycleview.layoutmanager.MyGridLayoutManager;
import com.bftv.fui.recycleview.layoutmanager.MyLinearLayoutManager;
import com.bftv.fui.recycleview.model.Footer;
import com.bftv.fui.recycleview.model.Header;
import com.bftv.fui.recycleview.model.Item;
import com.bftv.fui.recycleview.presenter.HeaderPresenter;
import com.bftv.fui.recycleview.presenter.LoadingPresenter;
import com.bftv.fui.recycleview.presenter.MyPresenterSelector;
import com.bftv.fui.recycleview.presenter.NormalPresenter;
import com.bftv.fui.recycleview.widget.MyRecycleView;

public class RecyclerViewFragment2 extends Fragment {

    private static final int SPAN_COUNT = 4;

    protected RadioButton mLinearLayoutRadioButton;

    protected RadioButton mGridLayoutRadioButton;

    protected MyRecycleView mRecyclerView;

    private Button btn;

    int index = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        mRecyclerView = (MyRecycleView) rootView.findViewById(R.id.recyclerView);
        btn = (Button)rootView.findViewById(R.id.btn);
        
        final MyLinearLayoutManager mLayoutManager = new MyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mLinearLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.linear_layout_rb);
        mLinearLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

        mGridLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.grid_layout_rb);
        mGridLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(getActivity(),SPAN_COUNT);
                mRecyclerView.setLayoutManager(myGridLayoutManager);
            }
        });

        final MyPresenterSelector presenterSelector = new MyPresenterSelector();

        presenterSelector.configSupportType(Header.class,new HeaderPresenter());

        presenterSelector.configSupportType(Item.class,new NormalPresenter());

        presenterSelector.configSupportType(Footer.class,new LoadingPresenter());

        final ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(presenterSelector);
        arrayObjectAdapter.add(new Header());

        for(int i=0;i<10;i++){
            index++;
            Item item1 = new Item();
            item1.setI(index);
            arrayObjectAdapter.add(item1);
        }

        final ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(arrayObjectAdapter);
        mRecyclerView.setAdapter(itemBridgeAdapter);

        final Footer footer = new Footer();
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                arrayObjectAdapter.add(footer);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10;i++){
                            index++;
                            Item item1 = new Item();
                            item1.setI(index);
                            arrayObjectAdapter.add(item1);
                        }
                        itemBridgeAdapter.notifyItemInserted(itemBridgeAdapter.getItemCount()-2);
                        arrayObjectAdapter.remove(footer);
                        mRecyclerView.loadComplete();
                    }
                },2000);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

}
