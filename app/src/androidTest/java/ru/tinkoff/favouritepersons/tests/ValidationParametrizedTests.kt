package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
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


/*
!!!!!!!!!!!! НУЖНО ЗАПУСКАТЬ ТЕСТОВЫЙ КЛАСС, А НЕ ТЕСТЫ ПО ОТДЕЛЬНОСТИ !!!!!!!!!!!!!!!!!!!!

Вероятнее всего тесты не будут запускаться по отдельности, какой-то баг библиотеки или IDE я не
смог понять в чем причина, только нашел пост на редите, что надо запускать все сразу

Я испробовал все библиотеки: JUnit params, junit.Parameterized, но только эта адекватно работает с kaspresso
Вероятно я ошибаюсь, но тогда в любом случае интересно будет узнать, что нужно было использовать
*/
@RunWith(TestParameterInjector::class)
class ValidationParametrizedTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    ).apply {
        stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
    }
) {

    private val databaseMockData = DatabaseMockData()
    private val setupDatabaseRule = SetupDatabaseRule(listOf(databaseMockData.femalePerson))
    private val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityScenarioRule)
        .around(setupDatabaseRule)

    // Тесткейс №10
    @Test
    fun dateBirthValidationTest(
        @TestParameter(value = ["2001-1-1", " ", "1990-13-20", "0-0-0"])
        dob: String
    ) {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterDOB(dob)
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkDOBErrorDisplayed()
    }

    // Тесткейс №11
    @Test
    fun genderValidationTest(
        @TestParameter(value = [" ", "0", "*"])
        gender: String
    ) {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterGender(gender)
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkGenderErrorDisplayed()
    }


    // Тесткейс №12
    @Test
    fun ratingValidationTest(
        @TestParameter(value = ["-1", "101", "1.1", " ", "К"])
        rating: String
    ) {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterRating(rating)
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkRatingErrorDisplayed()
    }

}