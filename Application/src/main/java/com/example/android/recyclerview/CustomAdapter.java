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

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.User;
import com.example.android.callback.OnLoadMoreListener;
import com.example.android.common.logger.Log;
import com.example.android.viewholder.LoadingViewHolder;
import com.example.android.viewholder.UserViewHolder;

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
    private int lastVisibleItem, totalItemCount;

    private SparseArray<User> mSparseArray = null;

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    private String[] mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(String[] dataSet) {
        mDataSet = dataSet;
//        this.mRecyclerView = recyclerView;
//        this.mManager = (GridLayoutManager) recyclerView.getLayoutManager();
//
//        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return getItemViewType(position) == VIEW_LOADING ? mManager.getSpanCount() : 1;
//            }
//        });
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType){

            case VIEW_TYPE_HEADER:
                break;

            case VIEW_TYPE_ITEM:
                break;

            case VIEW_TYPE_LOADING:
                break;

            case VIEW_TYPE_FOOTER:
                break;

            default:
                break;
        }
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        if(holder instanceof UserViewHolder){
            User user = null;
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.tvName.setText(user.getName());
        }else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

//    @Override public int getItemCount() {
//        return mUsers == null ? 0 : mUsers.size();
//    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

//    @Override public int getItemViewType(int position) {
//        return mUsers.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
//    }
}
