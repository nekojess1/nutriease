package org.nekojess.nutriease.ui.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.mockk.mockk
import io.mockk.unmockkAll
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.nekojess.nutriease.R
import org.nekojess.nutriease.data.repository.user.UserRepository
import org.koin.core.context.loadKoinModules

class LoginActivityRobot {
    private lateinit var scenario: ActivityScenario<LoginActivity>

    private val repository: UserRepository = mockk()
    private val viewModel = LoginViewModel(repository)
    private val modules = module {
        factory { repository }
        factory { viewModel }
    }

    fun tearDown() {
        unmockkAll()
        stopKoin()
        scenario.close()
    }

    init {
        loadKoinModules(modules)
    }

    inner class Arrange {
        fun launch() {
            scenario = ActivityScenario.launch(LoginActivity::class.java)
        }
    }

    inner class Action {

    }

    inner class Assert {
        fun assertTitle(expectedTitle: String) {
            onView(withId(R.id.login_activity_title)).check(matches(withText(expectedTitle)))
        }
    }


    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun action(func: Action.() -> Unit) = Action().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}
