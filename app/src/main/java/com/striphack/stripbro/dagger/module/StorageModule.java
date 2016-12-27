package com.striphack.stripbro.dagger.module;

import android.content.Context;

import com.striphack.stripbro.storage.PreferencesPromoCodeStorage;
import com.striphack.stripbro.storage.PromoCodeStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Module
public class StorageModule  {

    private Context context;

    public StorageModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    PromoCodeStorage providePromoCodeStorage(){
        return new PreferencesPromoCodeStorage(context);
    }
}
