package com.example.demoribs.root.login.logout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.logout_rib.view.*

/**
 * Top level view for {@link LogoutBuilder.LogoutScope}.
 */
class LogoutView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle),
    LogoutInteractor.LogoutPresenter {
    private val didClickBehaviorRelay = BehaviorRelay.create<Unit>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        logout_btn.setOnClickListener { didClickBehaviorRelay.accept(Unit) }
    }

    override fun onLogoutClicked(): Observable<Unit> = didClickBehaviorRelay

    override fun showInfoOnTextView(accountText: String, pwText: String, times: Int) {
        logout_tv.text = "$accountText login success \nyour password is: $pwText\nWrong times: $times"
    }
}
