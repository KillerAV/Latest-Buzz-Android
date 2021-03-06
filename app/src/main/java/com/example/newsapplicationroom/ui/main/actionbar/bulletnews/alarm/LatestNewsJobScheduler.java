package com.example.newsapplicationroom.ui.main.actionbar.bulletnews.alarm;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.newsapplicationroom.di.main.actionbar.bulletnews.alarm.LatestNewsJobSchedulerComponent;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.ui.main.MainActivity;
import com.example.newsapplicationroom.utils.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LatestNewsJobScheduler extends JobService {

    @Inject
    AlarmNotificationLauncher alarmNotificationLauncher;

    @Override
    public boolean onStartJob(JobParameters params) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date currDate = new Date();
        MainActivity.toDate = df.format(currDate);
        Date prevDate = new Date(currDate.getTime() - Constants.MILLISECONDS_IN_A_DAY);
        MainActivity.fromDate = df.format(prevDate);

        LatestNewsJobSchedulerComponent.Builder builder = NewsApplication.getLatestNewsJobSchedulerComponentBuilder();
        builder
                .context(this)
                .setFromDate(MainActivity.fromDate)
                .setToDate(MainActivity.toDate)
                .build()
                .inject(this);

        alarmNotificationLauncher.displayNotification();

        MainActivity.isAlarmLaunched = true;
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
