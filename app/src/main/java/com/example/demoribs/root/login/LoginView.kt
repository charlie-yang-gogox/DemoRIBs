package com.example.demoribs.root.login

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.login_rib.view.*

/**
 * Top level view for {@link LoginBuilder.LoginScope}.
 */
class LoginView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context),
    LoginInteractor.LoginPresenter {
    private val didClickLoginButton = BehaviorRelay.create<Pair<String, String>>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        login_btn.setOnClickListener {
            didClickLoginButton.accept(
                Pair(
                    account_tv.text.toString(),
                    pw_tv.text.toString()
                )
            )
        }
    }

    override fun loginClicked(): Observable<Pair<String, String>> = didClickLoginButton

    override fun processLogin(isVerifying:Boolean, msg: String) {
        if (isVerifying) {
            progress_bar.visibility = View.VISIBLE
            login_btn.isEnabled = false
            account_tv.isEnabled = false
            pw_tv.isEnabled = false
        } else {
            progress_bar.visibility = View.GONE
            login_btn.isEnabled = true
            account_tv.isEnabled = true
            pw_tv.isEnabled = true
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
