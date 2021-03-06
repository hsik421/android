package com.android.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by admin on 2017-02-07.
 */

public class MediaPlayerService extends Service {
    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_PAUSE = "action_pause";
    public static final String ACTION_REWIND = "action_rewind";
    public static final String ACTION_FAST_FORWARD = "action_fast_foward";
    public static final String ACTION_NEXT = "action_next";
    public static final String ACTION_PREVIOUS = "action_previous";
    public static final String ACTION_STOP = "action_stop";

    private MediaPlayer mMediaPlayer;
    private MediaSessionManager mManager;
    private MediaSession mSession;
    private MediaController mController;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    private void init(){
        mMediaPlayer = new MediaPlayer();

        mSession = new MediaSession(getApplicationContext(), "simple player session");
        mController = new MediaController(getApplicationContext(), mSession.getSessionToken());

        mSession.setCallback(new MediaSession.Callback() {
            @Override
            public void onPlay() {
                super.onPlay();
                Log.e( "MediaPlayerService", "onPlay");
                buildNotification();
            }

            @Override
            public void onPause() {
                super.onPause();
                Log.e( "MediaPlayerService", "onPause");
                buildNotification();
            }

            @Override
            public void onSkipToNext() {
                super.onSkipToNext();
                Log.e( "MediaPlayerService", "onSkipToNext");
                //Change media here
                buildNotification();
            }

            @Override
            public void onSkipToPrevious() {
                super.onSkipToPrevious();
                Log.e( "MediaPlayerService", "onSkipToPrevious");
                //Change media here
                buildNotification();
            }

            @Override
            public void onFastForward() {
                super.onFastForward();
                Log.e( "MediaPlayerService", "onFastForward");
                //Manipulate current media here
            }

            @Override
            public void onRewind() {
                super.onRewind();
                Log.e( "MediaPlayerService", "onRewind");
                //Manipulate current media here
            }

            @Override
            public void onStop() {
                super.onStop();
                Log.e( "MediaPlayerService", "onStop");
                //Stop media player here
                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel( 1 );
                Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
                stopService( intent );
            }


        });
    }
    private void handleIntent( Intent intent ) {
        if( intent == null || intent.getAction() == null )
            return;

        String action = intent.getAction();

        if( action.equalsIgnoreCase( ACTION_PLAY ) ) {
            mController.getTransportControls().play();
        } else if( action.equalsIgnoreCase( ACTION_PAUSE ) ) {
            mController.getTransportControls().pause();
        } else if( action.equalsIgnoreCase( ACTION_FAST_FORWARD ) ) {
            mController.getTransportControls().fastForward();
        } else if( action.equalsIgnoreCase( ACTION_REWIND ) ) {
            mController.getTransportControls().rewind();
        } else if( action.equalsIgnoreCase( ACTION_PREVIOUS ) ) {
            mController.getTransportControls().skipToPrevious();
        } else if( action.equalsIgnoreCase( ACTION_NEXT ) ) {
            mController.getTransportControls().skipToNext();
        } else if( action.equalsIgnoreCase( ACTION_STOP ) ) {
            mController.getTransportControls().stop();
        }
    }



    private void buildNotification() {
        Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
        intent.setAction( ACTION_STOP );
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);
        remoteViews.setOnClickPendingIntent(R.id.noti_left,getPendingIntent(2,ACTION_NEXT));
        remoteViews.setOnClickPendingIntent(R.id.noti_play,getPendingIntent(0,ACTION_PLAY));
        remoteViews.setOnClickPendingIntent(R.id.noti_right,getPendingIntent(1,ACTION_PREVIOUS));
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "Media Title" )
                .setContentText( "Media Artist" )
                .setDeleteIntent( pendingIntent )
                .setCustomBigContentView(remoteViews);

        NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        notificationManager.notify( 1, builder.build() );
    }
    private PendingIntent getPendingIntent(int num,String pAction){
        Intent intent = new Intent(this,MediaPlayerService.class);
        switch (num){
            case 0:
                intent.setAction("play");
                intent.setAction(pAction);
                break;
            case 1:
                intent.setAction("right");
                intent.setAction(pAction);
                break;
            case 2:
                intent.setAction("left");
                intent.setAction(pAction);
                break;
        }
        return PendingIntent.getService(this,1,intent,0);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if( mManager == null ) {
            init();
        }

        handleIntent( intent );
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mSession.release();
        return super.onUnbind(intent);
    }
}
