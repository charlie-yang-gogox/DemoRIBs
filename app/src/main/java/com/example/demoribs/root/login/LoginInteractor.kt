package com.example.demoribs.root.login

import android.util.Log
import com.example.demoribs.root.api.DummyApiService
import com.example.demoribs.root.api.LoginState
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoginScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoginInteractor : Interactor<LoginInteractor.LoginPresenter, LoginRouter>() {

    @Inject
    lateinit var presenter: LoginPresenter

    private val disposables = CompositeDisposable()

    var api = DummyApiService()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.loginClicked().subscribe { info ->
            Log.d(
                javaClass.simpleName,
                "call api.getRemoteLoginResult(${info.first}, ${info.second})"
            )
            api.getRemoteLoginResult(info.first, info.second)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it) {
                        LoginState.Success -> {
                            presenter.processLogin(false, "success, jump to another screen")
                            router.detachLogin()
                            router.attachLogout(info.first)
                        }
                        LoginState.AccNotExisted -> presenter.processLogin(false, "account isn't existed")
                        LoginState.WrongPassword -> presenter.processLogin(false, "please try another password")
                        LoginState.Verifying -> presenter.processLogin(true)
                    }
                }.addTo(disposables)
        }.addTo(disposables)
    }

    fun getPw(): String {
        return presenter.getPwFromEditText()
    }

    override fun willResignActive() {
        super.willResignActive()
        disposables.clear()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface LoginPresenter {
        fun loginClicked(): Observable<Pair<String, String>>
        fun processLogin(isVerifying : Boolean, msg: String = "")
        fun getPwFromEditText(): String
    }
}
