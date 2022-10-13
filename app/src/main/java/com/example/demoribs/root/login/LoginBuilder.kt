package com.example.demoribs.root.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.demoribs.R
import com.example.demoribs.root.login.logout.LogoutBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link loginScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class LoginBuilder(dependency: ParentComponent) :
    ViewBuilder<LoginView, LoginRouter, LoginBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [LoginRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [LoginRouter].
     */
    fun build(parentViewGroup: ViewGroup): LoginRouter {
        val view = createView(parentViewGroup)
        val interactor = LoginInteractor()
        val component = DaggerLoginBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.loginRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoginView {
        return inflater.inflate(R.layout.login_rib, parentViewGroup, false) as LoginView
    }

    interface ParentComponent {
        // TODO: Define dependencies required from your parent interactor here.
    }

    @dagger.Module
    abstract class Module {

        @LoginScope
        @Binds
        internal abstract fun presenter(view: LoginView): LoginInteractor.LoginPresenter

        @dagger.Module
        companion object {

            @LoginScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: LoginView,
                interactor: LoginInteractor
            ): LoginRouter {
                return LoginRouter(view, interactor, component, LogoutBuilder(component))
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @LoginScope
    @dagger.Component(
        modules = arrayOf(Module::class),
        dependencies = arrayOf(ParentComponent::class)
    )
    interface Component : InteractorBaseComponent<LoginInteractor>, BuilderComponent, LogoutBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoginInteractor): Builder

            @BindsInstance
            fun view(view: LoginView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun loginRouter(): LoginRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class LoginScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class LoginInternal
}
