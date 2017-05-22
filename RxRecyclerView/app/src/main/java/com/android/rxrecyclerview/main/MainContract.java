package com.android.rxrecyclerview.main;


import android.support.v7.widget.RecyclerView;

import com.android.rxrecyclerview.BasePresenter;
import com.android.rxrecyclerview.BaseView;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 2017-02-14.
 */

public interface MainContract {
    interface Presenter extends BasePresenter {
        void initData();
        void onItemClick(int position);
        void swiped(RecyclerView.ViewHolder viewHolder,int direction);
        void refresh();
    }

    interface View extends BaseView<Presenter> {
        void addData(ArrayList<String> data);
        void removeItem(int position);
        void refresh();
        void toast(int position);
    }
}
