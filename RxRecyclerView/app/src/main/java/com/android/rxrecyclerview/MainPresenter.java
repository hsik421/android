package com.android.rxrecyclerview;


import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by admin on 2017-02-14.
 */

public interface MainPresenter {
    void initData();
    void onItemClick(int position);
    void swiped(RecyclerView.ViewHolder viewHolder,int direction);
    void refresh();
    interface View{
        void addData(ArrayList<String> data);
        void removeItem(int position);
        void refresh();
        void toast(int position);
    }
}
