package com.android.rxrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by admin on 2017-02-14.
 */

public interface OnRecyclerItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter,int position);
}
