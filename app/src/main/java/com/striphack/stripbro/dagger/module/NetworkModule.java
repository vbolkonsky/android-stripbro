package com.striphack.stripbro.dagger.module;

import android.content.Context;

import dagger.Module;

/**
 * Created by Karpenko on 10.12.2016.
 */
@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(Context context){
        this.context = context;
    }

}
