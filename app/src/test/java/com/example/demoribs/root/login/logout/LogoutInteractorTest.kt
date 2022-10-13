package com.example.demoribs.root.login.logout

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LogoutInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: LogoutInteractor.LogoutPresenter
  @Mock internal lateinit var router: LogoutRouter

  private var interactor: LogoutInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestLogoutInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<LogoutInteractor.LogoutPresenter, LogoutRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}