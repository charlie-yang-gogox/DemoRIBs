package com.example.demoribs.root.login.logout

import com.example.demoribs.root.login.LoginStream
import com.example.demoribs.root.login.MutableLoginStream
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

    @field:[Inject Named("account")]
    lateinit var account: String

    @Inject
    lateinit var listener: Listener

    @field:[Inject Named("pw")]
    lateinit var password: String

    @Inject
    lateinit var loginStream: MutableLoginStream

    private val disposables = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        loginStream.showLoginInfo().subscribe {
            presenter.showInfoOnTextView(account, password, it)
        }.addTo(disposables)
        presenter.onLogoutClicked().subscribe {
            router.detachLogout()
            listener.attachLogin()
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
        fun showInfoOnTextView(accountText: String, pwText: String, times: Int)
    }

    interface Listener {
        fun attachLogin()
    }
}
