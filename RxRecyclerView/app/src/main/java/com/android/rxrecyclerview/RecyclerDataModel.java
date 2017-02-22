package com.android.rxrecyclerview;


/**
 * Created by admin on 2017-02-14.
 */

public interface RecyclerDataModel {
    void add(String text);
    void remove(int position);

    int getSize();
}
