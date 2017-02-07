package com.android.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;


public class LocalService extends Service {
    private static final String TAG = LocalService.class.getSimpleName();
    private final IBinder mBinder = new LocalBinder();
    private boolean isService;
    private Intent mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return mBinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        isService = true;
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isService = false;
    }
    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i(TAG,"unbindService");
        super.unbindService(conn);
    }
    public void setNotification(){
        if(isService){
            mIntent = new Intent(this,MyBroadcastReceiver.class);

            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);

            remoteViews.setOnClickPendingIntent(R.id.noti_play,getPendingIntent(0));
            remoteViews.setOnClickPendingIntent(R.id.noti_right,getPendingIntent(1));
            remoteViews.setOnClickPendingIntent(R.id.noti_left,getPendingIntent(2));
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setOngoing(true);
            builder.setCustomBigContentView(remoteViews);

            Notification notification = builder.build();
            manager.notify(1,notification);
        }
    }
    private PendingIntent getPendingIntent(int num){
        switch (num){
            case 0:
                mIntent.setAction("play");
                break;
            case 1:
                mIntent.setAction("right");
                break;
            case 2:
                mIntent.setAction("left");
                break;
        }
        return PendingIntent.getBroadcast(this,num,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public boolean isService() {
        return isService;
    }



    public class LocalBinder extends Binder{
        LocalService getService(){
            return LocalService.this;
        }
    }
}
