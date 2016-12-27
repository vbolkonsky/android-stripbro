package com.striphack.stripbro.dagger.module;

import android.content.Context;

import com.striphack.stripbro.repository.bro.BarneyService;
import com.striphack.stripbro.repository.bro.BarneyServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Valentin S. Bolkonsky on 11.12.2016.
 */
@Module
public class ServiceModule {

    private Context context;

    public ServiceModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    BarneyService provideBarneyService(){
        return new BarneyServiceImpl();
    }
}
