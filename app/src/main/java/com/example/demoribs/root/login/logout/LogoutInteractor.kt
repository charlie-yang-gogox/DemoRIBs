package com.example.demoribs.root.login.logout

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import javax.inject.Named

/**
 * Coordinates Business Logic for [LogoutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LogoutInteractor : Interactor<LogoutInteractor.LogoutPresenter, LogoutRouter>() {

    @Inject
    lateinit var presenter: LogoutPresenter

    private val disposables = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.onLogoutClicked().subscribe {
            // TODO
        }.addTo(disposables)
    }

    override fun willResignActive() {
        super.willResignActive()
        disposables.clear()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface LogoutPresenter {
        fun onLogoutClicked(): Observable<Unit>
    }
}
