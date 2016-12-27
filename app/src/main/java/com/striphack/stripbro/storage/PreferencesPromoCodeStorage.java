package com.striphack.stripbro.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.striphack.stripbro.exception.NoPromoCodeException;
import com.striphack.stripbro.repository.bro.ClientKey;
import com.striphack.stripbro.repository.bro.ClientKeyImpl;
import com.striphack.stripbro.utils.RxUtils;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

/**
  *
 * @author Valentin S.Bolkonsky
 */
@Slf4j
public class PreferencesPromoCodeStorage implements PromoCodeStorage {


    private static final String KEY_CODE = "key_code";
    private static final String KEY_FIRST = "key_is_first";
    private static final String KEY_SERVER_ID = "key_client_id";

    private SharedPreferences sp;

    public PreferencesPromoCodeStorage(Context context) {
        this.sp = context.getSharedPreferences(KEY_CODE, Context.MODE_PRIVATE);
    }

    @Override
    public Observable<Void> put(String code) {
        return RxUtils.wrapObservable(() ->
        sp.edit().putString(KEY_CODE, code).apply());
    }

    @Override
    public Observable<String> restore() {
        return RxUtils.wrapObservable(() -> {
            final String code = sp.getString(KEY_CODE, null);
            if(null == code){
                throw new NoPromoCodeException("promo code not set yet");
            }
            return code;
        });
    }

    @Override
    public Observable<ClientKey> getKey() {
        return RxUtils.wrapObservable(() -> {
            final String code = sp.getString(KEY_SERVER_ID, null);
            if(null == code){
                throw new NoPromoCodeException("client key not set yet");
            }
            return new ClientKeyImpl(code);
        });
    }

    @Override
    public void setKey(ClientKey key) {
        sp.edit().putString(KEY_SERVER_ID, key.getUserId()).apply();
    }

    @Override
    public boolean isFirstLaunch() {
      return sp.getBoolean(KEY_FIRST, true);
    }

    @Override
    public void setFirstLaunch(boolean isFirstLaunch) {
        sp.edit().putBoolean(KEY_FIRST, isFirstLaunch).apply();
    }

    @Override
    public Observable<Void> clear() {
        sp.edit().putBoolean(KEY_FIRST, true).apply();
        return put(null);
    }
}
