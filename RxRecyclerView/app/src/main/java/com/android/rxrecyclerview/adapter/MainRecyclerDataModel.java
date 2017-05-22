package com.android.rxrecyclerview.adapter;


/**
 * Created by admin on 2017-02-14.
 */

public interface MainRecyclerDataModel {
    void add(String text);
    void remove(int position);

    int getSize();
}
