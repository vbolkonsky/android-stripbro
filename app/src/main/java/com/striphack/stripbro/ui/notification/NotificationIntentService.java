package com.striphack.stripbro.ui.notification;

import android.app.IntentService;
import android.content.Intent;

import com.striphack.stripbro.application.StripbroApplication;

import javax.inject.Inject;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public class NotificationIntentService extends IntentService {

    public static final String KEY_MESSAGE = "key_message";

    @Inject
    protected NotificationService notificationService;

    public NotificationIntentService() {
        super("Finish Service");
        StripbroApplication.getApplication(getApplicationContext()).getComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationService.finishNotification(intent.getStringExtra(KEY_MESSAGE));
    }
}
