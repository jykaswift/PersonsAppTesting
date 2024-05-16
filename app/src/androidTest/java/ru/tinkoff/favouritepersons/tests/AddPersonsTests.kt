package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.favouritepersons.domain.Gender
import ru.tinkoff.favouritepersons.dto.DOB
import ru.tinkoff.favouritepersons.dto.Person
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.EditPersonScreen
import ru.tinkoff.favouritepersons.screens.PersonsScreen


class AddPersonsTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    )
) {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    // Тесткейс №1
    @Test
    fun editTextsDisplayedInPortraitOrientationTest() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()

        editPersonScreen.apply {
            checkNameEditTextDisplayed()
            checkSurnameEditTextDisplayed()
            checkGenderEditTextDisplayed()
            checkBirthDateEditTextDisplayed()
            checkEmailEditTextDisplayed()
            checkPhoneNumberEditTextDisplayed()
            checkAddressEditTextDisplayed()
            checkPhotoEditTextDisplayed()
            checkRatingEditTextDisplayed()
        }
    }


    // Тесткейс №2
    @Test
    fun editTextsDisplayedInLandscapeOrientationTest() = before {
        device.uiDevice.setOrientationLeft()
    } .after {  } .run {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()

        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()

        editPersonScreen.apply {
            checkNameEditTextDisplayed()
            checkSurnameEditTextDisplayed()
            checkGenderEditTextDisplayed()
            checkBirthDateEditTextDisplayed()
            checkEmailEditTextDisplayed()
            checkPhoneNumberEditTextDisplayed()
            checkAddressEditTextDisplayed()
            checkPhotoEditTextDisplayed()
            checkRatingEditTextDisplayed()
        }
    }

    // Тесткейс №3
    @Test
    fun addPersonManuallyTest() = run {

        val person = Person(
            name = "Константин",
            surname = "Константинов",
            gender = Gender.MALE,
            dob = DOB(1990,1,1),
            email = "test@gmail.com",
            phoneNumber = "89999999999",
            address = "Иваново",
            photoUrl = "www.url.net",
            rating = 100
        )

        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()

        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()

        editPersonScreen.apply {
            person.apply {
                enterName(name)
                enterSurname(surname)
                enterGender(gender.inputString())
                enterDOB(dob.toString())
                enterEmail(email)
                enterPhoneNumber(phoneNumber)
                enterAddress(address)
                enterPhotoURL(photoUrl)
                enterRating(rating.toString())
            }

            clickSaveButton()
        }

        personsScreen.apply {
            person.apply {
                checkPersonNameContains(name, 0)
                checkPersonAgeBy(dob, 0)
                checkPersonGenderWith(gender, 0)
                checkPersonRatingWith(rating, 0)
            }
        }
    }

    // Тесткейс №7
    // Можно было бы использовать параметризацию, но мне показалось, что это
    // будет выглядеть только менее читабельно и в целом смысла мало
    @Test
    fun checkNameEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkNameEditTextFocusedOnClick()
    }

    @Test
    fun checkSurnameEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkSurnameEditTextFocusedOnClick()
    }

    @Test
    fun checkGenderEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkGenderEditTextFocusedOnClick()
    }

    @Test
    fun checkDOBEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkDOBEditTextFocusedOnClick()
    }

    @Test
    fun checkRatingEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkRatingEditTextFocusedOnClick()
    }
}