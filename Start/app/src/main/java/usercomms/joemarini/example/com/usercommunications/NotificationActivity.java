package usercomms.joemarini.example.com.usercommunications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationActivity extends AppCompatActivity
    implements View.OnClickListener{

    private static final int NOTIFY_ID = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btnNotification).setOnClickListener(this);
    }

    private void createNotification() {
        // TODO: create the NotificationCompat Builder
        // Using the builder object will create the rest of the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // TODO: Create the intent that will start the ResultActivity when the user
        // taps the notification or chooses an action button
        Intent intent = new Intent(this, NotificationResultActivity.class);

        // Include the notificationID as an extra piece of data in the Intent
        intent.putExtra("notifyID", NOTIFY_ID);

        // Store the notification ID so we can cancel it later in the ResultActivity
        // Give Pending Intent to the system because the system will fire it off
        // on the app's behalf
        PendingIntent pendingIntent = PendingIntent.getActivity(
                // pass the context of the app
                this,
                // notification ID
                NOTIFY_ID,
                // Intent to fire off
                intent,
                // Cancels any pending intent that might have already been fired
                // when the user taps
                PendingIntent.FLAG_CANCEL_CURRENT);

        // TODO: Set the three required items all notifications must have
        builder.setSmallIcon(R.drawable.ic_stat_sample_notification);
        builder.setContentTitle("Sample Notification");
        builder.setContentText("This is a sample notification");


        // TODO: Set the notification to cancel when the user taps on it
        // When the user taps on the notification, we want it to auto cancel.
        // want the icon to automatically dissapear from the notification area in the
        // status bar.
        builder.setAutoCancel(true);

        // TODO: Set the large icon to be our app's launcher icon
        // Decode the mipmap image from the resources
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        // TODO: Set the small subtext message
        // Appears below the content message
        builder.setSubText("Tap to view");


        // TODO: Set the content intent to launch our result activity
        // Add pendingIntent that we created earlier to the builder
        builder.setContentIntent(pendingIntent);


        // TODO: Add an expanded layout to the notification
        // This is a notification style that can contain a large amount of text. It works really well
        // when you have a large amount of information to display in the notification area and want
        // the user to be able to expand the message to see all of the content.
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();

        // Set title that will appear in the expanded state
        bigTextStyle.setBigContentTitle("This is a big notification");

        // Set the large text string that will serve as the expanded content
        // when the notification is in the expanded state
        bigTextStyle.bigText(getResources().getString(R.string.LongMsg));

        // once the properties of the big text style have been set, we need to use
        // the builder to add this object to the builder using the setStyle() function.
        builder.setStyle(bigTextStyle);

        // TODO: Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action
        // To do this, we need to call the addAction() method on the builder.
        // The action is composed of an icon, a text string, and a pending intent to fire
        // when the action button is clicked.
        builder.addAction(R.mipmap.ic_launcher, "Action 1", pendingIntent);

        builder.addAction(R.mipmap.ic_launcher, "Action 2", pendingIntent);


        // TODO: Set the lock screen visibility of the notification
        // VISIBILITY_PUBLIC: All information visible
        // VISIBILITY_SECRET: No part of notification is shown
        // VISIBILITY_PRIVATE: Basic info, like icon and title
        builder.setVisibility(Notification.VISIBILITY_PRIVATE);


        // TODO: Build the finished notification and then display it to the user
        // Create the notification by calling the build() function on the builder object
        Notification notification = builder.build();

        // Trigger the notification by getting a reference to the notification manager
        // and then using the notify() function
        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        mgr.notify(NOTIFY_ID, notification);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNotification) {
            createNotification();
        }
    }
}
