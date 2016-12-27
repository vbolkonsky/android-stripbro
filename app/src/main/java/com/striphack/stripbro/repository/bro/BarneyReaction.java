package com.striphack.stripbro.repository.bro;

import java.util.List;

/**
 * Created by frank on 10.12.2016.
 */
public class BarneyReaction {

    public static final int SIMPLE = 0;
    public static final int HARD = 1;

    public static final String OK = "ok";
    public static final String CANCEL = "cancel";

    public static final String PACK_1 = "pack.welcome";
    public static final String PACK_2 = "pack.extended";
    public static final String PACK_3 = "pack.superextended";


    private String barneySpeech;
    private int type;

    private List<Variant> variants;

    public String getBarneySpeech() {
        return barneySpeech;
    }

    public void setBarneySpeech(String barneySpeech) {
        this.barneySpeech = barneySpeech;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class Variant {

        private String variantText;

        private String variantId;

        public Variant(String variantText, String variantId) {
            this.variantText = variantText;
            this.variantId = variantId;
        }

        public String getVariantText() {
            return variantText;
        }

        public void setVariantText(String variantText) {
            this.variantText = variantText;
        }

        public String getVariantId() {
            return variantId;
        }

        public void setVariantId(String variantId) {
            this.variantId = variantId;
        }
    }


}
