package com.striphack.stripbro.mvp.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;

import com.striphack.stripbro.R;
import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.MainPresenter;
import com.striphack.stripbro.mvp.presenter.base.BaseMvpPresenter;
import com.striphack.stripbro.mvp.router.MainRouter;
import com.striphack.stripbro.mvp.view.MainView;
import com.striphack.stripbro.repository.bro.BarneyReaction;
import com.striphack.stripbro.repository.bro.BarneyService;
import com.striphack.stripbro.storage.PromoCodeStorage;
import com.striphack.stripbro.ui.notification.NotificationService;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Slf4j
public class MainPresenterImpl extends BaseMvpPresenter<MainView, MainRouter>
        implements MainPresenter {

    @Inject
    protected PromoCodeStorage storage;
    @Inject
    protected NotificationService notificationService;
    @Inject
    protected BarneyService barneyService;

    private Context context;


    public MainPresenterImpl(Context context) {
        StripbroApplication.getApplication(context)
                .getComponent().inject(this);
        this.context = context;
    }

    @Override
    public void onCreate(boolean isCheck) {
        final boolean isFirst = storage.isFirstLaunch();
        storage.getKey()
                .subscribe(clientKey -> {
                   showBarneyReaction(isCheck ?
                           barneyService.checkBarney(clientKey):
                           barneyService.clientEnteredInClub(clientKey, isFirst), isFirst);
                }, throwable -> log.error("can't get key", throwable));

    }

    @Override
    public void userAnswer(String answer) {
        final String message = barneyService.userAnswer(answer);
        afterUserAnswer(message);
        changeBarneyImage(answer);
        new Handler().postDelayed(() -> {
            MainRouter router = getRouter();
            if(router != null){
                router.onGoToLogo();
            }
        }, context.getResources().getInteger(R.integer.logo_timer));
    }

    @Override
    public void onShowNotification() {
        //final Intent intent = new Intent(context, NotificationIntentService.class);
        //intent.putExtra(NotificationIntentService.KEY_MESSAGE, message);
        //context.startService(intent);
        final boolean isFirst = storage.isFirstLaunch();
        if(!isFirst){
            storage.getKey()
                    .subscribe(clientKey -> {
                        new Handler().postDelayed(() -> notificationService.finishNotification(
                                barneyService.pushToReturn(clientKey).getBarneySpeech()),
                                context.getResources().getInteger(R.integer.return_push_timer));
                    }, throwable -> log.error("can't get key", throwable));

        }
    }

    private void showBarneyReaction(BarneyReaction reaction, boolean isFirst){
        final MainView view = getView();
        if(view != null){
            if(isFirst){
                view.showFirstBarneyReaction(reaction);
            }else{
                view.showBarneyReaction(reaction);
            }
        }
    }

    private void afterUserAnswer(String message){
        final MainView view = getView();
        if(view != null){
            view.afterUserAnswer(message, Gravity.CENTER);
        }
    }

    private void changeBarneyImage(String variantId){
        final MainView view = getView();
        if(view != null){
            if(!BarneyReaction.CANCEL.equals(variantId)){
                view.changeBarnyImage(R.drawable.barn_success);
            }
        }
    }
}
