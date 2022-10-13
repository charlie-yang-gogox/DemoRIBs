package com.example.demoribs.root

import com.example.demoribs.root.login.LoginBuilder
import com.example.demoribs.root.login.LoginRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    v: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    val loginBuilder: LoginBuilder
) : ViewRouter<RootView, RootInteractor>(v, interactor, component) {
    fun attachLogin() {
        val loginRouter = loginBuilder.build(view)
        attachChild(loginRouter)
        view.addView(loginRouter.view)
    }
}
