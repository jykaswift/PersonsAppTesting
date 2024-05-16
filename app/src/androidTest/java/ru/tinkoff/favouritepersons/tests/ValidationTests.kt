package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.SetupDatabaseRule
import ru.tinkoff.favouritepersons.screens.EditPersonScreen
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.DatabaseMockData

@Epic("Приложение студентов")
@Feature("Валидация текстовых полей")
@RunWith(AllureAndroidJUnit4::class)
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
    @Story("Валидация имени")
    @DisplayName("Проверка валидации имени")
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
    @Story("Валидация фамилии")
    @DisplayName("Проверка валидации фамилии")
    fun surnameValidationTest() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickPersonAt(0)
        editPersonScreen.enterSurname(" ")
        editPersonScreen.clickSaveButton()
        editPersonScreen.checkSurnameErrorDisplayed()
    }
}