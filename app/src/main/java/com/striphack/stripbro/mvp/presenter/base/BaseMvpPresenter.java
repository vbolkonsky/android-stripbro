package com.striphack.stripbro.mvp.presenter.base;

import android.support.annotation.Nullable;

import com.striphack.stripbro.mvp.router.base.MvpRouter;
import com.striphack.stripbro.mvp.view.base.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created at Magora Systems (http://magora-systems.com) on 13.09.16
 *
 * @author Stanislav S. Borzenko
 */
public class BaseMvpPresenter<V extends MvpView, R extends MvpRouter>
        implements MvpPresenter<V, R> {
    private WeakReference<V> viewRef;
    private WeakReference<R> routerRef;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);

        onViewAttached();
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;

            onViewDetached();
        }
    }

    @Override
    public void attachRouter(R router) {
        routerRef = new WeakReference<>(router);
    }

    @Override
    public void detachRouter() {
        if (routerRef != null) {
            routerRef.clear();
            routerRef = null;
        }
    }

    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Nullable
    public R getRouter() {
        return routerRef == null ? null : routerRef.get();
    }

    public boolean isRouterAttached() {
        return routerRef != null && routerRef.get() != null;
    }

    public void onViewAttached() {
        // Empty
    }

    public void onViewDetached() {
        // Empty
    }
}
