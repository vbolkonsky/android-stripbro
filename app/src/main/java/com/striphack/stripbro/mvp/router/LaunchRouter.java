package com.striphack.stripbro.mvp.router;

import com.striphack.stripbro.mvp.router.base.MvpRouter;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public interface LaunchRouter extends MvpRouter {

    void onGoToAuthorization();

    void onGoToMain();
}
