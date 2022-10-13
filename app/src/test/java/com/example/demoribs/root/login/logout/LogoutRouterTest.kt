package com.example.demoribs.root.login.logout

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LogoutRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: LogoutBuilder.Component
  @Mock internal lateinit var interactor: LogoutInteractor
  @Mock internal lateinit var view: LogoutView

  private var router: LogoutRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = LogoutRouter(view, interactor, component)
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

