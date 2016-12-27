package com.striphack.stripbro.ui.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.NotificationCompat;

import com.striphack.stripbro.R;
import com.striphack.stripbro.utils.ColorGenerator;
import com.striphack.stripbro.utils.ParserUtils;


/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class NotificationUtils {

    public static void showNotification(final Context context, final int notificationId,
                                        final String title, final String text, final Bitmap bitmap, Intent intent) {
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);


        builder.setSmallIcon(R.drawable.ic_stat_512_play_store)
                .setPriority(Notification.PRIORITY_MAX)
                .setColor(ColorGenerator.MATERIAL.getRandomColor())
                .setContentTitle(title)
                .setContentIntent(notifyPendingIntent)
                .setSound(ParserUtils.parseToUri("android.resource://com.punch.client/" + R.raw.orgasm))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setContentText(text)
                .setAutoCancel(true);

        if(bitmap != null) {
            builder.setLargeIcon(bitmap);
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }

    public static void clearNotification(final Context context, final int notificationId) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }

    public static void clearAllNotification(final Context context) {
        clearNotification(context, NotificationService.FINISH_MESSAGE);
    }
}
