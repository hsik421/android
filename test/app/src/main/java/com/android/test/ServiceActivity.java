package com.android.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-02-06.
 */

public class ServiceActivity extends AppCompatActivity {
    @BindView(R.id.service_start_btn)
    Button serviceStartBtn;
    @BindView(R.id.service_stop_btn)
    Button serviceStopBtn;

    private Intent mIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        ButterKnife.bind(this);
        mIntent = new Intent(this,PlayService.class);
    }

    @OnClick({R.id.service_start_btn, R.id.service_stop_btn})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.service_start_btn:
                stopService(mIntent);
                startService(mIntent);
                break;
            case R.id.service_stop_btn:
                stopService(mIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        stopService(mIntent);
        super.onBackPressed();
    }
}
