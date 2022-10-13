package com.example.demoribs.root.login

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: LoginInteractor.LoginPresenter
  @Mock internal lateinit var router: LoginRouter

  private var interactor: LoginInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    interactor = TestLoginInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<LoginInteractor.LoginPresenter, LoginRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}