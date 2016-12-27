package com.striphack.stripbro.dagger.component;

import com.striphack.stripbro.dagger.module.NetworkModule;
import com.striphack.stripbro.dagger.module.PresenterModule;
import com.striphack.stripbro.dagger.module.ServiceModule;
import com.striphack.stripbro.dagger.module.StorageModule;
import com.striphack.stripbro.dagger.module.SystemModules;
import com.striphack.stripbro.mvp.presenter.impl.EnterPresenterImpl;
import com.striphack.stripbro.mvp.presenter.impl.LaunchPresenterImpl;
import com.striphack.stripbro.mvp.presenter.impl.MainPresenterImpl;
import com.striphack.stripbro.ui.activity.EnterActivity;
import com.striphack.stripbro.ui.activity.LaunchActivity;
import com.striphack.stripbro.ui.activity.LogoActivity;
import com.striphack.stripbro.ui.activity.MainActivity;
import com.striphack.stripbro.ui.notification.NotificationIntentService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Karpenko on 10.12.2016.
 */
@Component(modules = {NetworkModule.class,
        PresenterModule.class,
        StorageModule.class,
        SystemModules.class,
        ServiceModule.class})
@Singleton
public interface StripbroComponent {

    void inject(LaunchActivity activity);

    void inject(EnterActivity activity);

    void inject(MainActivity activity);

    void inject(LogoActivity activity);

    void inject(EnterPresenterImpl presenter);

    void inject(LaunchPresenterImpl presenter);

    void inject(MainPresenterImpl presenter);

    void inject(NotificationIntentService service);
}
