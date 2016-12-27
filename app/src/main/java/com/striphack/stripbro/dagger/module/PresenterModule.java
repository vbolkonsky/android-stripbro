package com.striphack.stripbro.dagger.module;

import android.content.Context;

import com.striphack.stripbro.mvp.presenter.EnterPresenter;
import com.striphack.stripbro.mvp.presenter.LaunchPresenter;
import com.striphack.stripbro.mvp.presenter.MainPresenter;
import com.striphack.stripbro.mvp.presenter.impl.EnterPresenterImpl;
import com.striphack.stripbro.mvp.presenter.impl.LaunchPresenterImpl;
import com.striphack.stripbro.mvp.presenter.impl.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Module
public class PresenterModule {

    private Context context;

    public PresenterModule(Context context){
        this.context = context;
    }

    @Provides
    LaunchPresenter provideLaunchPresneter(){
        return new LaunchPresenterImpl(context);
    }

    @Provides
    EnterPresenter provideEnterPresenter(){
        return new EnterPresenterImpl(context);
    }

    @Provides
    MainPresenter provideMainPresenter(){
        return new MainPresenterImpl(context);
    }
}
