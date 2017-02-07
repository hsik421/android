package com.android.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;


public class PlayService extends Service {
    private static final String TAG = PlayService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        setNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void setNotification(){

        Intent intent = new Intent(this,ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);

        remoteViews.setOnClickPendingIntent(R.id.noti_play,pendingIntent);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setOngoing(true);
        builder.setCustomContentView(remoteViews);

        Notification notification = builder.build();
        manager.notify(1,notification);


    }
}
