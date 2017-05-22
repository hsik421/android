package com.android.rxrecyclerview.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.rxrecyclerview.OnRecyclerItemClickListener;
import com.android.rxrecyclerview.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-02-14.
 */

public class MainMainMainRecyclerAdapter extends RecyclerView.Adapter implements MainRecyclerDataModel,MainRecyclerAdapterView {
    private ArrayList<String> mDataList = new ArrayList<>();
    private OnRecyclerItemClickListener mListener;
    @Inject
    public MainMainMainRecyclerAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder pHolder, int position) {
        ViewHolder holder = (ViewHolder)pHolder;
        TextView textView = holder.recyclerItemText;
        textView.setText(mDataList.get(position));
        textView.setOnClickListener(v->mListener.onItemClick(MainMainMainRecyclerAdapter.this, position));
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public void add(String text) {
        mDataList.add(text);
    }

    @Override
    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getSize() {
        return mDataList.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnRecyclerItemClickListener listener){
        mListener = listener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @BindView(R.id.recycler_item_text)
        TextView recyclerItemText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //butterknife null 값 출력??
            recyclerItemText = (TextView)view.findViewById(R.id.recycler_item_text);
        }

    }
}
