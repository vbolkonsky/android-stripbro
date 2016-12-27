package com.striphack.stripbro.ui.notification;

import android.content.Context;
import android.content.Intent;

import com.striphack.stripbro.R;
import com.striphack.stripbro.ui.activity.LaunchActivity;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class NotificationService {

    public static final int FINISH_MESSAGE = 0;

    private Context context;

    @Inject
    public NotificationService(Context context) {
        this.context = context;
    }

    public void finishNotification(String message) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        String appName = context.getString(R.string.app_name);
        NotificationUtils.showNotification(context, FINISH_MESSAGE, appName, message, null, intent);
    }
}
