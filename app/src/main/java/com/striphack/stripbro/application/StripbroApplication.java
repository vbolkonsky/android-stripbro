package com.striphack.stripbro.application;

import android.app.Application;
import android.content.Context;

import com.striphack.stripbro.dagger.component.DaggerStripbroComponent;
import com.striphack.stripbro.dagger.component.StripbroComponent;
import com.striphack.stripbro.dagger.module.NetworkModule;
import com.striphack.stripbro.dagger.module.PresenterModule;
import com.striphack.stripbro.dagger.module.ServiceModule;
import com.striphack.stripbro.dagger.module.StorageModule;
import com.striphack.stripbro.dagger.module.SystemModules;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Karpenko on 10.12.2016.
 */
@Slf4j
public class StripbroApplication extends Application {

    private  StripbroComponent component;

    public static StripbroApplication getApplication(Context context){
        return (StripbroApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        log.debug("{} running...", StripbroApplication.class.getCanonicalName());
        super.onCreate();
        buildGraph();
    }

    private void  buildGraph(){
        component = DaggerStripbroComponent.builder()
                .networkModule(new NetworkModule(this))
                .presenterModule(new PresenterModule(this))
                .storageModule(new StorageModule(this))
                .systemModules(new SystemModules(this))
                .serviceModule(new ServiceModule(this))
                .build();
    }

    public StripbroComponent getComponent(){
        return component;
    }
}
