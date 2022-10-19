package com.example.demoribs.root.login

import io.reactivex.Observable

interface LoginStream {
    fun showLoginInfo(): Observable<Int>
}
