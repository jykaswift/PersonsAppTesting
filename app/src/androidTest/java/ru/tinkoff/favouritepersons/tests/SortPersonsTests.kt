package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.SetupDatabaseRule
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.DatabaseMockData

class SortPersonsTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    )
) {
    private val databaseMockData = DatabaseMockData()
    private val setupDatabaseRule = SetupDatabaseRule(databaseMockData.sortMockData)
    private val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityScenarioRule)
        .around(setupDatabaseRule)

    @Test
    fun sortPersonByRatingTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickRatingButton()
        personsScreen.checkPersonsSortedByRating()
    }

    @Test
    fun sortPersonByAgeTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickAgeButton()
        personsScreen.checkPersonsSortedByAge()
    }

    @Test
    fun sortPersonByNameTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.sortMenu.click()
        personsScreen.sortMenu.clickNameButton()
        personsScreen.checkPersonsSortedByName()
    }

}