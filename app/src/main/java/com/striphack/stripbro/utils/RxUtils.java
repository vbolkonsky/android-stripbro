package com.striphack.stripbro.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rx.Observable;
import rx.functions.Action0;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RxUtils {
    public static Observable<Void> wrapObservable(Action0 action) {
        return Observable.create(subscriber -> {
            action.call();

            subscriber.onNext(null);
            subscriber.onCompleted();
        });
    }

    public static <T> Observable<T> wrapObservable(ActionRes<T> action) {
        return Observable.create(subscriber -> {
            subscriber.onNext(action.call());
            subscriber.onCompleted();
        });
    }

    public interface ActionRes<T> {
        T call();
    }
}
