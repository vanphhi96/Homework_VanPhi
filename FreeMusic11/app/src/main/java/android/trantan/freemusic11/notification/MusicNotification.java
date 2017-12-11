package android.trantan.freemusic11.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.trantan.freemusic11.R;
import android.trantan.freemusic11.activites.MainActivity;
import android.trantan.freemusic11.database.TopSongModel;
import android.trantan.freemusic11.utils.MusicHandler;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by vanph on 09/12/2017.
 */

public class MusicNotification {
    public static RemoteViews remoteViews;
    private static final int NOTIFICATION_ID=1;
    public static NotificationCompat.Builder builder;
    public static NotificationManager notificationManager;
    public static void setupNotification(Context context, TopSongModel topSongModel){

        remoteViews = new RemoteViews(context.getPackageName(),R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_songnoti,topSongModel.song);
        remoteViews.setTextViewText(R.id.tv_singernoti,topSongModel.singer);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        remoteViews.setImageViewResource(R.id.iv_pausenoti,R.drawable.ic_pause_black_24dp);
       builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent)
                .setOngoing(true);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(topSongModel.smallImage!=null)
        {
            Picasso.with(context).load(topSongModel.smallImage).transform(new CropCircleTransformation())
                    .into(remoteViews,R.id.iv_songnoti,NOTIFICATION_ID,builder.build());
        }
        else{
            Picasso.with(context).load(R.drawable.mp3_file_icon).transform(new CropCircleTransformation())
                    .into(remoteViews,R.id.iv_songnoti,NOTIFICATION_ID,builder.build());
        }

        setOnClickPlayPause(context);

        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }

    public static void updateNotification() {
        if(MusicHandler.hybridMediaPlayer.isPlaying())
        {
            remoteViews.setImageViewResource(R.id.iv_pausenoti,R.drawable.ic_pause_black_24dp);
            builder.setOngoing(true);
        }
        else {
            remoteViews.setImageViewResource(R.id.iv_pausenoti,R.drawable.ic_play_arrow_black_24dp);
            builder.setOngoing(false);
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }

    private static void setOnClickPlayPause(Context context) {
        Intent inten = new Intent(context,MusicService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,inten,0);
        remoteViews.setOnClickPendingIntent(R.id.iv_pausenoti,pendingIntent);

    }
}
