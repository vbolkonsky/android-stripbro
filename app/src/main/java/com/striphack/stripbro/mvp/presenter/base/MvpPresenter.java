package com.striphack.stripbro.mvp.presenter.base;


import com.striphack.stripbro.mvp.router.base.MvpRouter;
import com.striphack.stripbro.mvp.view.base.MvpView;

/**
 * Created at Magora Systems (http://magora-systems.com) on 13.09.16
 *
 * @author Stanislav S. Borzenko
 */
public interface MvpPresenter<V extends MvpView, R extends MvpRouter> {
    void attachView(V view);
    void detachView();
    void attachRouter(R router);
    void detachRouter();
}
