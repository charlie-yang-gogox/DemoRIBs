package com.example.demoribs.root.login

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class MutableLoginStream(times: Int) : LoginStream {
    private val loginTimesRelay: BehaviorRelay<Int> = BehaviorRelay.create()

    init {
        loginTimesRelay.accept(times)
    }

    fun addTimes() {
        loginTimesRelay.value?.let {
            Log.d("MutableLoginStream", "it=$it")
            loginTimesRelay.accept(it + 1)
        }
    }

    override fun showLoginInfo(): Observable<Int> = loginTimesRelay.hide()
}