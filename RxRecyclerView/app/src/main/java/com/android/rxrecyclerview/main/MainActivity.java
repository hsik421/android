package com.android.rxrecyclerview.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.android.rxrecyclerview.PerActivity;
import com.android.rxrecyclerview.R;
import com.android.rxrecyclerview.adapter.MainMainMainRecyclerAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

@PerActivity
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerview;
    @BindView(R.id.main_refreshlayout)
    SwipeRefreshLayout mainRefreshlayout;

    MainMainMainRecyclerAdapter adapter;
    @Inject MainContract.Presenter mainPresenter;

    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        paint = new Paint();
        adapter = new MainMainMainRecyclerAdapter();

        DaggerMainComponent.builder().mainPresenterModule(new MainPresenterModule(this)).build();
        mainPresenter.initData();
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerview.setAdapter(adapter);

        mainRefreshlayout.setOnRefreshListener(() -> {
            mainRefreshlayout.setRefreshing(false);
            mainPresenter.refresh();
        });

        adapter.setOnItemClickListener((recyclerview, position) -> mainPresenter.onItemClick(position));

        initSwipe();

    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mainPresenter.swiped(viewHolder, direction);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX <= 0) {
                        paint.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, paint);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, paint);
                    }
                }
                clearView(recyclerView, viewHolder);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mainRecyclerview);
    }

    @Override
    public void removeItem(int position) {
        adapter.remove(position);
    }

    @Override
    public void refresh() {
        adapter.refresh();
    }

    @Override
    public void toast(int position) {
        Toast.makeText(this, "position = " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addData(ArrayList<String> data) {
        for (String text : data) {
            adapter.add(text);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mainPresenter = checkNotNull(presenter);
    }
}
