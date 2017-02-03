package com.android.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-02-03.
 */

public class ListAdapter extends RecyclerView.Adapter {
    interface mListener {
        void ClickListener(View view);
    }

    private mListener mClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }


    public void setClickListener(mListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_item_text)
        TextView listItemText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
