package com.cs442.vgodhamg.servicenotificationcounter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by vedant on 16-03-2016.
 */
public class ServiceNotificationSetCounter extends Service {
    String TAG = "Assignment6-->Log Display-->";
    final Handler handler = new Handler();
    int counter,j=0;
    public static final int NOTIFICATION_ID = 5453;
    Timer counting_timer;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
         return null;
    }


@Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Has Been Started", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Service Has Been Started");
        //If user press the ServiceNotificationCounterStart button, this service will be started with the counter start number from EditView.
        try {
           counter = Integer.parseInt(intent.getExtras().getString("counter_value", "1"));
            Toast.makeText(this, "Counter Starting From : " + counter,Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Counter Starting From : " + counter);
        } catch (NumberFormatException ex) {  // If EditView entered text is not a number it will start from 1
           counter = 1;
          Toast.makeText(this,"Not a valid input, Counter will start from : " + counter,Toast.LENGTH_SHORT).show();
           Log.d(TAG, "Not a valid input, Counter will start from : " + counter);
        }
        //Initiate the timer
        counting_timer = new Timer();

          //schedule(TimerTask task, long delay, long period)
         //  Schedule a task for repeated fixed-delay execution after a specific delay.
    counting_timer.schedule(new TimerTask() {
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    Log.i(TAG, "Counter Value : " + counter);
                    if (counter != 1) {
                        j = j + 1;
                        counter = counter + 1;
                        if (j % 10 == 0) {            // When counter reaches values of multiples of 10 it will generate a notification
                            triggerNotification(counter);
                            // triggerNotification() triggers the notification after every 10 seconds
                        }// Increments the counter value by 1 in every one second
                    }
                    else{
                        j=1;
                        counter = counter + 1;
                        j = j + 1;
                        if (j % 10 == 0) {            // When counter reaches values of multiples of 10 it will generate a notification
                            triggerNotification(counter); // triggerNotification() triggers the notification after every 10 seconds
                        }

                    }

                    }

            });
        }


    }, 0, 1000);

   // Android will restart the service AND redeliver the same intent to onStartCommand() of the service
    return START_REDELIVER_INTENT;
}

    @Override
    public void onDestroy() {
        //Called by the system to notify a Service that it is no longer used and is being removed.
        // The service should clean up any resources it holds (threads, registered receivers, etc) at this point.
        super.onDestroy(); // cleaning up any resources it holds (threads, registered receivers, etc) at this point

        counting_timer.cancel();   // Stopping the timer
        Toast.makeText(this, "Service Stopped with Counter Value : " + (counter - 1), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Counter Stopped At : " + (counter - 1));
        Log.d(TAG, "Service Has Been Stopped");

    }

    private void triggerNotification(final int text) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.images_icon);
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);   // Creates a virtual stack so that when back key is pressed main activity will be displayed
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
                );
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        CharSequence tickerText = "Current Counter Value :" + text;
        Notification notification = new Notification.Builder(this)
                .setLargeIcon(bm)
                .setSmallIcon(R.drawable.images_icon)
                .setContentTitle(getString(R.string.notification))  // Setting Title of Notification
                .setAutoCancel(true)                               // Makes this notification automatically dismissed when the user touches it
                .setPriority(Notification.PRIORITY_MAX)           // Setting priority to Max
                .setContentIntent(pendingIntent)
                .setContentText(tickerText)
                .setLights(0xFF0000FF, 100, 3000)
                .setSound(soundUri)
                .build();

        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
        Log.d(TAG, "Notification Has Been Sent To The User..!!");

    }

}
