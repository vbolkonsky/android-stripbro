package com.striphack.stripbro.mvp.presenter;

import com.striphack.stripbro.mvp.presenter.base.MvpPresenter;
import com.striphack.stripbro.mvp.router.MainRouter;
import com.striphack.stripbro.mvp.view.MainView;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface MainPresenter extends MvpPresenter<MainView, MainRouter> {

    void onShowNotification();

    void onCreate(boolean isCheck);

    void userAnswer(String answer);
}
