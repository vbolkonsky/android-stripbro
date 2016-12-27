package com.striphack.stripbro.dagger.module;

import android.content.Context;

import com.striphack.stripbro.ui.notification.NotificationIntentService;
import com.striphack.stripbro.ui.notification.NotificationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Module
public class SystemModules {

    private Context context;

    public SystemModules(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    NotificationService provideNotificationService(){
        return new NotificationService(context);
    }

    @Provides
    NotificationIntentService provideNotificationIntentService(){
        return new NotificationIntentService();
    }
}
