package com.android.rxrecyclerview.main;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by admin on 2017-02-14.
 */

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View view;
    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void initData() {
        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            datas.add(String.valueOf(i));
        }
        view.addData(datas);
    }

    @Override
    public void onItemClick(int position) {
        view.toast(position);
    }

    @Override
    public void refresh() {
        view.refresh();
    }

    @Override
    public void swiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            view.removeItem(position);
        }
    }
    @Override
    public void start() {

    }

}
