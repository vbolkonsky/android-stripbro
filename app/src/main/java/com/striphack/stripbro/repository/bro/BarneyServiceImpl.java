package com.striphack.stripbro.repository.bro;

import java.util.ArrayList;

/**
 * Created by frank on 10.12.2016.
 */
public class BarneyServiceImpl implements BarneyService {

    public static final String BARNEY_RETURN_DISCUSION = "Привет! Давно тебя не было, рад тебя видеть. Начнём как обычно?";



    public BarneyServiceImpl() {


    }

    @Override
    public ClientKey authorizeCard(String cardNumber) {
        ClientKeyImpl clientKey = new ClientKeyImpl(cardNumber);
        return clientKey;
    }

    @Override
    public BarneyReaction clientEnteredInClub(ClientKey key, boolean first) {
        BarneyReaction br = new BarneyReaction();
        if (first) {
            br.setType(BarneyReaction.HARD);
            br.setBarneySpeech("Привет. Сегодня тебе повезло! Я изменю твою жизнь и расскажу тебе," +
                    "что это за место и буду направляться тебя и помогать чем смогу, а могу я многое!" +
                    "Вот тебе твой приветственный Chivas, и могу предложить одно из следующих продолжений твоего вечера");

            ArrayList<BarneyReaction.Variant> variants = new ArrayList<>();

            variants.add(new BarneyReaction.Variant("Private & Chivas", BarneyReaction.PACK_1));
            variants.add(new BarneyReaction.Variant("Private 2 girls & Chivas", BarneyReaction.PACK_2));
            variants.add(new BarneyReaction.Variant("Private & Chivas & Кальян", BarneyReaction.PACK_3));
            variants.add(new BarneyReaction.Variant("Спасибо, я осмотрюсь", BarneyReaction.CANCEL));
//освобождение
            br.setVariants(variants);

        } else {
            br.setType(BarneyReaction.SIMPLE);
            br.setBarneySpeech(BARNEY_RETURN_DISCUSION);

            ArrayList<BarneyReaction.Variant> variants = new ArrayList<>();

            variants.add(new BarneyReaction.Variant("Конечно", BarneyReaction.OK));
            variants.add(new BarneyReaction.Variant("Потом", BarneyReaction.CANCEL));

            br.setVariants(variants);

        }

        return br;
    }


    @Override
    public BarneyReaction checkBarney(ClientKey key) {


        BarneyReaction br = new BarneyReaction();
        br.setBarneySpeech("Друг, похоже ты уже допил, повторить?");

        ArrayList<BarneyReaction.Variant> variants = new ArrayList<>();
        variants.add(new BarneyReaction.Variant("Давай", BarneyReaction.OK));
        variants.add(new BarneyReaction.Variant("Я пока пас", BarneyReaction.CANCEL));
        br.setVariants(variants);

        return br;
    }


    @Override
    public BarneyReaction pushToReturn(ClientKey key) {

        BarneyReaction br = new BarneyReaction();
        br.setBarneySpeech("Салют! Давно тебя не видел, заработался? Мне кажется, тебе нужно расслабиться, приходи, девчонки ждут!");

        ArrayList<BarneyReaction.Variant> variants = new ArrayList<>();
        variants.add(new BarneyReaction.Variant("Отличная идея", BarneyReaction.OK));
        variants.add(new BarneyReaction.Variant("Не сегодня", BarneyReaction.CANCEL));
        br.setVariants(variants);

        return br;
    }

    @Override
    public String userAnswer(String variant) {
        if(BarneyReaction.CANCEL.equals(variant)){
            return "Бро, Я тебя не узнаю!";
        }
        if(variant.startsWith("pack.")){
            return "Отличный выбор!\uD83D\uDC4D";
        }
        return "Узнаю тебя, Бро!";
    }
}
