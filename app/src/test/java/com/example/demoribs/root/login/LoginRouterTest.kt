package com.example.demoribs.root.login

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: LoginBuilder.Component
  @Mock internal lateinit var interactor: LoginInteractor
  @Mock internal lateinit var view: LoginView

  private var router: LoginRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = LoginRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

