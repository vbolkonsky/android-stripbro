package com.striphack.stripbro.repository.bro;

/**
 * Created by frank on 10.12.2016.
 */
public interface BarneyService {

    ClientKey authorizeCard(String cardNumber);

    BarneyReaction clientEnteredInClub(ClientKey key, boolean first);

    BarneyReaction checkBarney(ClientKey key);

    BarneyReaction pushToReturn(ClientKey key);

    String userAnswer(String variant);

}
