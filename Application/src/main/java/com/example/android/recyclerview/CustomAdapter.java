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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.User;
import com.example.android.callback.OnLoadMoreListener;
import com.example.android.common.logger.Log;
import com.example.android.viewholder.FooterViewHolder;
import com.example.android.viewholder.HeaderViewHolder;
import com.example.android.viewholder.ItemViewHolder;
import com.example.android.viewholder.LoadingViewHolder;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CustomAdapter";

    private static final int VIEW_TYPE_ITEM = 0;

    private static final int VIEW_TYPE_LOADING = 1;

    private static final int VIEW_TYPE_HEADER = 2;

    private static final int VIEW_TYPE_FOOTER = 3;

    private boolean isLoading = false;

    private int visibleThreshold = 5;

    private int lastVisibleItem;

    private int totalItemCount;

    private SparseArray<User> mSparseArray = null;

    private OnLoadMoreListener mOnLoadMoreListener;

    private ArrayList<User> users = null;

    private RecyclerView.ViewHolder viewHolder = null;

    private View headerView,footerView,loadingView;

    private RecyclerView mRecyclerView = null;

    private LinearLayoutManager mLayoutManager = null;

    public CustomAdapter(ArrayList<User> users, RecyclerView recyclerView, LinearLayoutManager layoutManager) {
        this.users = users;
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = layoutManager;
//        this.mManager = (GridLayoutManager) recyclerView.getLayoutManager();
//        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return getItemViewType(position) == VIEW_LOADING ? mManager.getSpanCount() : 1;
//            }
//        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setCustomView(View headerView,View footerView,View loadingView){
        this.headerView = headerView;
        this.footerView = footerView;
        this.loadingView = loadingView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType){

            case VIEW_TYPE_HEADER:
                viewHolder = new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_header, viewGroup, false));
                break;

            case VIEW_TYPE_ITEM:
                viewHolder = new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false));
                break;

            case VIEW_TYPE_LOADING:
                viewHolder = new LoadingViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_item, viewGroup, false));
                break;

            case VIEW_TYPE_FOOTER:
                viewHolder = new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_footer, viewGroup, false));
                break;

            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");
        User user = users.get(position);
        if (holder instanceof HeaderViewHolder){

        }else if (holder instanceof ItemViewHolder){
            ItemViewHolder userViewHolder = (ItemViewHolder) holder;
            userViewHolder.getTextView().setText(user.getName());
        }else if (holder instanceof FooterViewHolder){

        }else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }
    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size() + convertExtItemCount();
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemViewType(int position) {

        if (users.get(position) == null){
            return VIEW_TYPE_HEADER;
        }

        if (users.get(position).getItemType() == 2){
            return VIEW_TYPE_LOADING;
        }

        return VIEW_TYPE_ITEM;
//        return super.getItemViewType(position);
    }

    public void showLoading(){
        User loading = new User();
        loading.setItemType(2);
        users.add(loading);
        notifyItemInserted(users.size() - 1);
    }

    public void hideLoading(){
        users.remove(users.size() - 1);
        notifyItemRemoved(users.size());
    }

    public void loadMoreComplete(){
        notifyDataSetChanged();
        setLoaded();
    }

    /**
     * 计算多余的item数
     * @return
     */
    private final int convertExtItemCount(){
        int extItem = 0;
        if (headerView != null){
            extItem++;
        }
        if (loadingView != null){
            extItem++;
        }
        if (footerView != null){
            extItem++;
        }
        return extItem;
    }
}
