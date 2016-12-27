package com.striphack.stripbro.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.striphack.stripbro.R;
import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.repository.bro.BarneyService;
import com.striphack.stripbro.storage.PromoCodeStorage;
import com.striphack.stripbro.ui.activity.base.ActivityNavigator;
import com.striphack.stripbro.ui.activity.base.BaseActivity;
import com.striphack.stripbro.ui.notification.NotificationService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Valentin S. Bolkonsky on 11.12.2016.
 */
@Slf4j
public class LogoActivity extends BaseActivity {

    public static int COUNTER = 0;

    @Inject
    protected PromoCodeStorage storage;

    @Inject
    protected NotificationService notificationService;

    @Inject
    protected BarneyService barneyService;

    @BindView(R.id.text_logo)
    protected View textLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        textLogo.setVisibility(View.VISIBLE);
        StripbroApplication.getApplication(this).getComponent().inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!storage.isFirstLaunch()){
            new Handler().postDelayed(this::onShowNotification, 4000L);
            return;
        }
        if(COUNTER < 1) {
            new Handler().postDelayed(() -> {
                final Bundle bundle = new Bundle();
                bundle.putBoolean(MainActivity.KEY_NEED_PUSH, true);
                ActivityNavigator.startGenericActivity(LogoActivity.this,
                        MainActivity.class, bundle, true, 0);
            }, getResources().getInteger(R.integer.main_timer));
            COUNTER++;
        }else{
            if(storage.isFirstLaunch()){
                storage.setFirstLaunch(false);
            }
        }
    }

    public void onShowNotification() {
        storage.getKey()
                .subscribe(clientKey -> {
                    LogoActivity.this.finish();
                    new Handler().postDelayed(() -> notificationService.finishNotification(
                            barneyService.pushToReturn(clientKey).getBarneySpeech()),
                            getResources().getInteger(R.integer.return_push_timer));
                }, throwable -> log.error("can't get key", throwable));
    }
}
