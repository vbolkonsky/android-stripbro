package com.striphack.stripbro.mvp.presenter.impl;

import android.content.Context;

import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.EnterPresenter;
import com.striphack.stripbro.mvp.presenter.base.BaseMvpPresenter;
import com.striphack.stripbro.mvp.router.EnterRouter;
import com.striphack.stripbro.mvp.view.EnterView;
import com.striphack.stripbro.repository.bro.BarneyService;
import com.striphack.stripbro.storage.PromoCodeStorage;
import com.striphack.stripbro.utils.RxUtils;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Slf4j
public class EnterPresenterImpl extends BaseMvpPresenter<EnterView, EnterRouter> implements EnterPresenter {

    @Inject
    protected PromoCodeStorage storage;
    @Inject
    protected BarneyService barneyService;

    public EnterPresenterImpl(Context context) {
        StripbroApplication.getApplication(context).getComponent().inject(this);
    }

    @Override
    public void onEnterPromoCode(String code) {
        log.debug("need to save promo code");
        RxUtils.wrapObservable(() -> barneyService.authorizeCard(code))
                .flatMap(key -> storage.put(code)
                        .doOnNext(v -> storage.setKey(key)))
                .subscribe(v -> goToMain(),
                        throwable -> log.error("cant save code", throwable));


    }

    private void goToMain() {
        EnterRouter router = getRouter();
        if (router != null) {
            router.onGoToMain();
        }
    }
}
