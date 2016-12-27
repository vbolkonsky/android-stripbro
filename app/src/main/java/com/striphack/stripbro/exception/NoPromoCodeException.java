package com.striphack.stripbro.exception;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public class NoPromoCodeException extends RuntimeException {

    public NoPromoCodeException() {
        super();
    }

    public NoPromoCodeException(String message) {
        super(message);
    }

    public NoPromoCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
