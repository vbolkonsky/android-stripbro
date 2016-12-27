package com.striphack.stripbro.storage;

import com.striphack.stripbro.repository.bro.ClientKey;

import rx.Observable;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface PromoCodeStorage  extends Storage{

    Observable<Void> put(String code);

    Observable<String> restore();

    Observable<ClientKey> getKey();

    void setKey(ClientKey key);

    boolean isFirstLaunch();

    void setFirstLaunch(boolean isFirstLaunch);

    Observable<Void> clear();

}
