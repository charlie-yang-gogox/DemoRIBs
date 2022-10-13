package com.example.demoribs.root.api

sealed class LoginState {
    object Success : LoginState()
    object AccNotExisted : LoginState()
    object WrongPassword : LoginState()
    object Verifying: LoginState()
}