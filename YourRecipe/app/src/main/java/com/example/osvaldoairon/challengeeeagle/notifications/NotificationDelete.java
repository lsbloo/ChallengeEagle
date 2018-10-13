package com.example.osvaldoairon.challengeeeagle.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.osvaldoairon.challengeeeagle.DetailsAct;
import com.example.osvaldoairon.challengeeeagle.R;
import com.example.osvaldoairon.challengeeeagle.Recipes;

public class NotificationDelete {

    public static final String ACTION_DELETE="om.example.osvaldoairon.challengeeeagle.notifications.DELETE_NOTIFCATION";


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static PendingIntent CpendingIntent(
            Context ctx, String text , int id
    ){
        Intent resultIntent = new Intent(ctx, DetailsAct.class);
        resultIntent.putExtra("text", text);

        TaskStackBuilder stack = TaskStackBuilder.create(ctx);
        stack.addParentStack(Recipes.class);
        stack.addNextIntent(resultIntent);
        return stack.getPendingIntent(id,PendingIntent.FLAG_UPDATE_CURRENT);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void createNotification(
            Context ctx , String text , int id
    ){
        PendingIntent pendingIntent = CpendingIntent(ctx,text,id);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ctx).setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_account_circle_black_24dp).setContentTitle("Delete" +id)
                .setContentText(text)
                .setContentIntent(pendingIntent);


        NotificationManagerCompat m = NotificationManagerCompat.from(ctx);
        m.notify(id,builder.build());

    }
}
