package com.striphack.stripbro.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.striphack.stripbro.R;
import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.LaunchPresenter;
import com.striphack.stripbro.mvp.router.LaunchRouter;
import com.striphack.stripbro.mvp.view.LaunchView;
import com.striphack.stripbro.ui.activity.base.ActivityNavigator;
import com.striphack.stripbro.ui.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Karpenko on 10.12.2016.
 */

public class LaunchActivity extends BaseActivity implements LaunchView, LaunchRouter{

    @Inject
    protected LaunchPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        StripbroApplication.getApplication(this).getComponent()
                .inject(this);
        ButterKnife.bind(this);
        LogoActivity.COUNTER = 0;
        presenter.attachRouter(this);
        presenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachRouter();
    }

    @Override
    public void onGoToAuthorization() {
        ActivityNavigator.startGenericActivity(this, EnterActivity.class, null, true, 0);
    }

    @Override
    public void onGoToMain() {
        ActivityNavigator.startGenericActivity(this, MainActivity.class, null, true, 0);
    }
}
