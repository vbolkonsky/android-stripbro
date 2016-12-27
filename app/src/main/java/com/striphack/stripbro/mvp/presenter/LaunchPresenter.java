package com.striphack.stripbro.mvp.presenter;

import com.striphack.stripbro.mvp.presenter.base.MvpPresenter;
import com.striphack.stripbro.mvp.router.LaunchRouter;
import com.striphack.stripbro.mvp.view.LaunchView;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface LaunchPresenter extends MvpPresenter<LaunchView, LaunchRouter> {

    void onCreate();
}
