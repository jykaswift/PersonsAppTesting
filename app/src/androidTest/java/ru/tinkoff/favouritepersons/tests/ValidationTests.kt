package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.google.testing.junit.testparameterinjector.TestParameters
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.SetupDatabaseRule
import ru.tinkoff.favouritepersons.screens.EditPersonScreen
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.DatabaseMockData


class ValidationTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    )
) {

    private val databaseMockData = DatabaseMockData()
    private val setupDatabaseRule = SetupDatabaseRule(listOf(databaseMockData.femalePerson))
    private val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityScenarioRule)
        .around(setupDatabaseRule)


    // Тесткейс №8
    @Test
    fun nameValidationTest() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterName(" ")
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkNameErrorDisplayed()
    }


    // Тесткейс №9
    @Test
    fun surnameValidationTest() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterSurname(" ")
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkSurnameErrorDisplayed()
    }
}