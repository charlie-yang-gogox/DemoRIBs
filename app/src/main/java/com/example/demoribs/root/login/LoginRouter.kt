package com.example.demoribs.root.login

import com.example.demoribs.root.login.logout.LogoutBuilder
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoginBuilder.LoginScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoginRouter(
    view: LoginView,
    interactor: LoginInteractor,
    component: LoginBuilder.Component,
    val loginBuilder: LogoutBuilder
) : ViewRouter<LoginView, LoginInteractor>(view, interactor, component) {
    fun attachLogout() {
        val logoutRouter = loginBuilder.build(view)
        attachChild(logoutRouter)
        view.addView(logoutRouter.view)
    }
    fun detachLogin() {
        view.removeAllViews()
        detachChild(this)
    }
}
