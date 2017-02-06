package com.android.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_list_btn)
    Button mainListBtn;
    @BindView(R.id.main_json_btn)
    Button mainJsonBtn;
    @BindView(R.id.main_pager_btn)
    Button mainPagerBtn;
    @BindView(R.id.main_slide_btn)
    Button mainSlideBtn;
    @BindView(R.id.main_navi_btn)
    Button mainNaviBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    private void moveActivity(Intent pIntent) {
        startActivity(pIntent);
    }

    @OnClick({R.id.main_list_btn, R.id.main_json_btn, R.id.main_pager_btn, R.id.main_slide_btn,R.id.main_navi_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_list_btn:
                moveActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.main_json_btn:
                moveActivity(new Intent(this, JsonActivity.class));
                break;
            case R.id.main_pager_btn:
                moveActivity(new Intent(this, PagerActivity.class));
                break;
            case R.id.main_slide_btn:
                moveActivity(new Intent(this, SlideActivity.class));
                break;
            case R.id.main_navi_btn:
                moveActivity(new Intent(this, NaviActivity.class));
                break;
        }
    }
}
