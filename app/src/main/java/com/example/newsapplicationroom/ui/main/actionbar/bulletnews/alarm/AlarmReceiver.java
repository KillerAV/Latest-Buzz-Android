package com.example.newsapplicationroom.ui.main.actionbar.bulletnews.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;

import androidx.annotation.RequiresApi;

import com.example.newsapplicationroom.utils.Constants;

public class AlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        PersistableBundle bundle = new PersistableBundle();

        ComponentName service = new ComponentName(context.getPackageName(), LatestNewsJobScheduler.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(Constants.LATEST_NEWS_JOB_ID, service)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setExtras(bundle);

        PendingIntent pendingIntent
                = PendingIntent.getBroadcast(context,
                Constants.ALARM_PENDING_INTENT_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Constants.ALARM_REPEAT_INTERVAL, pendingIntent);
        jobScheduler.schedule(builder.build());
    }
}
