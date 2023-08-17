package org.nekojess.nutriease.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private val robot = LoginActivityRobot()

    @Test
    fun `when activity is launched, title is displayed`() {
        with(robot) {
            arrange {
                launch()
            }
            assert {
                assertTitle("Entre na sua conta")
            }
        }
    }

}
