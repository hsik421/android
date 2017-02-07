package com.android.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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

    private static final String TAG = ServiceActivity.class.getSimpleName();
    private Intent mIntent;
    private ServiceConnection mConnection;
    private LocalService mService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        ButterKnife.bind(this);
        mIntent = new Intent(this,LocalService.class);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LocalService.LocalBinder binder = (LocalService.LocalBinder)iBinder;
                mService = binder.getService();
                startService(mIntent);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    @OnClick({R.id.service_start_btn, R.id.service_stop_btn})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.service_start_btn:
                Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
                intent.setAction( MediaPlayerService.ACTION_PLAY );
                startService( intent );
                break;
            case R.id.service_stop_btn:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mService.isService()){
            unbindService(mConnection);
            stopService(mIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(mIntent,mConnection,Context.BIND_AUTO_CREATE);
    }


}
