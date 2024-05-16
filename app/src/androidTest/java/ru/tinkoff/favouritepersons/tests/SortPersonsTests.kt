package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
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
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.DatabaseMockData


@Epic("Приложение студентов")
@Feature("Сортировка студентов")
@RunWith(AllureAndroidJUnit4::class)
class SortPersonsTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    ).apply {
        stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
    }
) {
    private val databaseMockData = DatabaseMockData()
    private val setupDatabaseRule = SetupDatabaseRule(databaseMockData.sortMockData)
    private val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityScenarioRule)
        .around(setupDatabaseRule)

    @Test
    @Story("Сортировка по рейтингу")
    @DisplayName("Проверка правильности сортировки студентов по рейтингу")
    fun sortPersonByRatingTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickRatingButton()
        personsScreen.checkPersonsSortedByRating()
    }

    @Test
    @Story("Сортировка по возрасту")
    @DisplayName("Проверка правильности сортировки студентов по возрасту")
    fun sortPersonByAgeTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickAgeButton()
        personsScreen.checkPersonsSortedByAge()
    }

    @Test
    @Story("Сортировка по имени")
    @DisplayName("Проверка правильности сортировки студентов по имени")
    fun sortPersonByNameTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickNameButton()
        personsScreen.checkPersonsSortedByName()
    }

}