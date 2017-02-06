package com.android.test;

import android.app.Application;
import android.util.Log;

/**
 * Created by admin on 2017-02-04.
 */

public class GlobalApplication extends Application {
    private static final String TAG = GlobalApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"Application");
    }
}
