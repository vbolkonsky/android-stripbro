package com.striphack.stripbro.repository.bro;

/**
 * Created by frank on 10.12.2016.
 */
public class ClientKeyImpl implements ClientKey {

    private String userId;

    public ClientKeyImpl(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
