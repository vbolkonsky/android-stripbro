package com.striphack.stripbro.mvp.presenter.impl;

import android.content.Context;
import android.os.Handler;

import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.LaunchPresenter;
import com.striphack.stripbro.mvp.presenter.base.BaseMvpPresenter;
import com.striphack.stripbro.mvp.router.LaunchRouter;
import com.striphack.stripbro.mvp.view.LaunchView;
import com.striphack.stripbro.storage.PromoCodeStorage;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Slf4j
public class LaunchPresenterImpl extends BaseMvpPresenter<LaunchView, LaunchRouter>
        implements LaunchPresenter {

    @Inject
    protected PromoCodeStorage storage;

    public LaunchPresenterImpl(Context context){
        StripbroApplication.getApplication(context).getComponent().inject(this);
    }

    @Override
    public void onCreate() {
        storage.restore()
                .subscribe(s -> goToMain(),
                        throwable -> goToAuthorization());
    }

    private void goToAuthorization(){
        final LaunchRouter router = getRouter();
        if (router != null) {
            new Handler().postDelayed(router::onGoToAuthorization, 2000);

        }
    }

    private void goToMain(){
        final LaunchRouter router = getRouter();
        if (router != null) {
            new Handler().postDelayed(router::onGoToMain, 2000);
        }
    }
}
