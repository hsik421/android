package com.android.rxrecyclerview;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by admin on 2017-02-14.
 */

public class MainPresenterImpl implements MainPresenter {
    private View view;
    public MainPresenterImpl(View view) {
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


}
