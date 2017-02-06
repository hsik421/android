package com.android.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-02-04.
 */

public class PagerActivity extends AppCompatActivity {
    @BindView(R.id.pager_toolbar)
    Toolbar pagerToolbar;
    @BindView(R.id.pager_tablayout)
    TabLayout pagerTablayout;
    @BindView(R.id.pager_viewpager)
    ViewPager pagerViewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pager_activity);
        ButterKnife.bind(this);

        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        pagerTablayout.addTab(pagerTablayout.newTab().setText("Tab"));
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pagerViewpager.setAdapter(adapter);
        pagerViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(pagerTablayout));

        pagerTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
