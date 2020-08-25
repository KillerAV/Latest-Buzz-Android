package com.example.newsapplicationroom.ui.bulletnews;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.R;

import javax.inject.Inject;
import javax.inject.Named;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmNotificationLauncher {

    private NotificationManager notificationManager;
    private Context context;
    private String fromDate, toDate;

    @Inject
    public AlarmNotificationLauncher(Context context, @Named("fromDate") String fromDate, @Named("toDate") String toDate) {
        this.context = context;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private void getNotificationChannel() {
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription(context.getString(R.string.notification_channel_description));

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        return new NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                .setContentTitle(context.getString(R.string.alarm_notification_title))
                .setContentInfo(context.getString(R.string.alarm_notification_content))
                .setSmallIcon(R.drawable.ic_latest_news_notification)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
    }

    public void displayNotification() {
        getNotificationChannel();

        Intent intent = new Intent(context, LatestNewsActivity.class);
        intent.putExtra(Constants.EXTRA_IS_ALARM_LAUNCHED, true);
        intent.putExtra(Constants.EXTRA_FROM_DATE, fromDate);
        intent.putExtra(Constants.EXTRA_TO_DATE, toDate);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, Constants.ALARM_NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = getNotificationBuilder();

        builder.setContentIntent(pendingIntent);

        notificationManager.notify(Constants.ALARM_NOTIFICATION_ID, builder.build());
    }
}
