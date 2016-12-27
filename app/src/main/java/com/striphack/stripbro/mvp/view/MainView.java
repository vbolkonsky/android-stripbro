package com.striphack.stripbro.mvp.view;

import android.support.annotation.DrawableRes;

import com.striphack.stripbro.mvp.view.base.MvpView;
import com.striphack.stripbro.repository.bro.BarneyReaction;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface MainView extends MvpView {

    void showFirstBarneyReaction(BarneyReaction reaction);

    void showBarneyReaction(BarneyReaction reaction);

    void afterUserAnswer(String message, int gravity);

    void changeBarnyImage(@DrawableRes int id);
}
