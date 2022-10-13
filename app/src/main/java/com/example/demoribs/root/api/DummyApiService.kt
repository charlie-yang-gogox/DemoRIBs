package com.example.demoribs.root.api

import android.util.Log
import io.reactivex.Observable

class DummyApiService {
    private val info = mapOf("1111" to "11", "2222" to "22")

    fun getRemoteLoginResult(account: String, pw: String): Observable<LoginState> =
        Observable.create { emitter ->
            Log.d("DummyApiService", "getRemoteLoginResult, acc=$account, pw=$pw")
            emitter.onNext(LoginState.Verifying) // emit is verifying
            Thread.sleep(2000) // wait for back end
            emitter.onNext( //emit result
                if (info.containsKey(account)) {
                    if (info[account] == pw) {
                        LoginState.Success
                    } else {
                        LoginState.WrongPassword
                    }
                } else {
                    LoginState.AccNotExisted
                }
            )
        }
}
