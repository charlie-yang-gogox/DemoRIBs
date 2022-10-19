package com.example.demoribs.root.login.logout

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.demoribs.R
import com.example.demoribs.root.login.LoginBuilder
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
 * Builder for the {@link LogoutScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class LogoutBuilder(dependency: ParentComponent) : ViewBuilder<LogoutView, LogoutRouter, LogoutBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [LogoutRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [LogoutRouter].
   */
  fun build(parentViewGroup: ViewGroup): LogoutRouter {
    val view = createView(parentViewGroup)
    val interactor = LogoutInteractor()
    val component = DaggerLogoutBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.logoutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LogoutView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.logout_rib, parentViewGroup, false) as LogoutView
  }

  interface ParentComponent {
    // define args from parent with parent provide
    fun listener() : LogoutInteractor.Listener

  }

  @dagger.Module
  abstract class Module {

    @LogoutScope
    @Binds
    internal abstract fun presenter(view: LogoutView): LogoutInteractor.LogoutPresenter

    @dagger.Module
    companion object {

      @LogoutScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: LogoutView,
          interactor: LogoutInteractor
      ): LogoutRouter {
        return LogoutRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @LogoutScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<LogoutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LogoutInteractor): Builder

      @BindsInstance
      fun view(view: LogoutView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun logoutRouter(): LogoutRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LogoutScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LogoutInternal
}
