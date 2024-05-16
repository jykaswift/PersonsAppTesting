package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import ru.tinkoff.favouritepersons.domain.Gender
import ru.tinkoff.favouritepersons.dto.DOB
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.DatabaseMocker
import ru.tinkoff.favouritepersons.rules.SetupDatabaseRule
import ru.tinkoff.favouritepersons.screens.EditPersonScreen
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.DatabaseMockData

class EditPersonsTests: TestCase(
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
    val ruleChain = RuleChain
        .outerRule(activityScenarioRule)
        .around(setupDatabaseRule)


    // Тесткейс №4
    @Test
    fun deletePersonFromListTest() = run {
        val personsScreen = PersonsScreen()
        personsScreen.deletePersonAt(0)
        personsScreen.checkNumberOfPersons(0)
    }

    // Тесткейс №5
    @Test
    fun editPersonTest() = run {
        val name = "Иван"
        val surname = "Иванов"
        val dob = DOB(1990, 1, 1)
        val gender = Gender.MALE
        val rating = 1

        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()

        personsScreen.clickPersonAt(0)

        editPersonScreen.apply {
            enterName(name)
            enterSurname(surname)
            enterDOB(dob.toString())
            enterGender(gender.inputString())
            enterRating(rating.toString())
            clickSaveButton()
        }

        personsScreen.apply {
            checkPersonNameContains(name, 0)
            checkPersonNameContains(surname, 0)
            checkPersonAgeBy(dob, 0)
            checkPersonGenderWith(gender, 0)
            checkPersonRatingWith(rating, 0)
        }
    }
}