package com.striphack.stripbro.mvp.presenter;

import com.striphack.stripbro.mvp.presenter.base.MvpPresenter;
import com.striphack.stripbro.mvp.router.EnterRouter;
import com.striphack.stripbro.mvp.view.EnterView;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface EnterPresenter extends MvpPresenter<EnterView, EnterRouter> {

    void onEnterPromoCode(String code);
}
