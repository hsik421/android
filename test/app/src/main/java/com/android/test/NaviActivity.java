package com.android.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-02-06.
 */

public class NaviActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navi_view)
    NavigationView naviView;
    @BindView(R.id.navi_drawer)
    DrawerLayout naviDrawer;
    @BindView(R.id.nava_toolbar)
    Toolbar navaToolbar;
    @BindView(R.id.navi_recycler)
    RecyclerView naviRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_activity);
        ButterKnife.bind(this);
        setSupportActionBar(navaToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, naviDrawer, 0, 0);
        naviDrawer.addDrawerListener(toggle);
        toggle.syncState();

        naviView.setNavigationItemSelectedListener(this);
        naviRecycler.setLayoutManager(new LinearLayoutManager(this));
        naviRecycler.setAdapter(new NaviAdapter());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        naviDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (naviDrawer.isDrawerOpen(GravityCompat.START)) {
            naviDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
