package com.example.demoribs.root.login.logout

import com.example.demoribs.root.login.LoginBuilder
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LogoutBuilder.LogoutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LogoutRouter(
    view: LogoutView,
    interactor: LogoutInteractor,
    val component: LogoutBuilder.Component
) : ViewRouter<LogoutView, LogoutInteractor>(view, interactor, component)
