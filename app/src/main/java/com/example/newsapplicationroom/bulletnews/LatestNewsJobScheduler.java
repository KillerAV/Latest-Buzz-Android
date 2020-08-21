package com.example.newsapplicationroom.bulletnews;

import android.app.job.JobParameters;
import android.app.job.JobService;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.newsapplicationroom.components.AlarmNotificationComponent;
import com.example.newsapplicationroom.components.DaggerAlarmNotificationComponent;
import com.example.newsapplicationroom.utils.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LatestNewsJobScheduler extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        new PopulateDatabaseAsync(this).execute(params);
        return true;
    }

    private static class PopulateDatabaseAsync extends AsyncTask<JobParameters, Void, JobParameters> {

        private final JobService jobService;

        public PopulateDatabaseAsync(JobService jobService) {
            this.jobService = jobService;
        }

        @Override
        protected JobParameters doInBackground(JobParameters... jobParameters) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date currDate = new Date();
            String toDate = df.format(currDate);
            Date prevDate = new Date(currDate.getTime() - Constants.MILLISECONDS_IN_A_DAY);
            String fromDate = df.format(prevDate);
            LatestNews.populateLatestNewsDatabase(fromDate, toDate);
            return jobParameters[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            jobService.jobFinished(jobParameters, false);
            AlarmNotificationComponent alarmNotificationComponent = DaggerAlarmNotificationComponent.builder().setContext(jobService).build();
            alarmNotificationComponent.getAlarmNotificationLauncher().displayNotification();
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
