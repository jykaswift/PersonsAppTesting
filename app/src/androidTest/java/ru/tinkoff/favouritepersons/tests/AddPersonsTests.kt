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
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.domain.Gender
import ru.tinkoff.favouritepersons.dto.DOB
import ru.tinkoff.favouritepersons.dto.Person
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.EditPersonScreen
import ru.tinkoff.favouritepersons.screens.PersonsScreen


@Epic("Приложение студентов")
@Feature("Добавление студентов")
@RunWith(AllureAndroidJUnit4::class)
class AddPersonsTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    ).apply {
        stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
    }
) {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    // Тесткейс №1
    @Test
    @Story("Видимость текстовых полей")
    @DisplayName("Проверка видимости полей добавления пользователя в портретном режиме")
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
    @Story("Видимость текстовых полей")
    @DisplayName("Проверка видимости полей добавления пользователя в альбомном режиме")
    fun editTextsDisplayedInLandscapeOrientationTest() = before {
        device.uiDevice.setOrientationLeft()
    } .after {
        device.uiDevice.setOrientationNatural()
    } .run {
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
    @Story("Добавление пользователя")
    @DisplayName("Проверка добавления пользователя с корректными данными")
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
    @Story("Фокусировка текстовых полей")
    @DisplayName("Проверка фокусировки текстового поля имени при нажатии")
    fun checkNameEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkNameEditTextFocusedOnClick()
    }

    @Test
    @Story("Фокусировка текстовых полей")
    @DisplayName("Проверка фокусировки текстового поля фамилии при нажатии")
    fun checkSurnameEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkSurnameEditTextFocusedOnClick()
    }

    @Test
    @Story("Фокусировка текстовых полей")
    @DisplayName("Проверка фокусировки текстового поля пола при нажатии")
    fun checkGenderEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkGenderEditTextFocusedOnClick()
    }

    @Test
    @Story("Фокусировка текстовых полей")
    @DisplayName("Проверка фокусировки текстового поля даты рождения при нажатии")
    fun checkDOBEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkDOBEditTextFocusedOnClick()
    }

    @Test
    @Story("Фокусировка текстовых полей")
    @DisplayName("Проверка фокусировки текстового поля рейтинга при нажатии")
    fun checkRatingEditTextIsFocusWhileEditing() {
        val personsScreen = PersonsScreen()
        val editPersonScreen = EditPersonScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonManuallyButton()
        editPersonScreen.checkRatingEditTextFocusedOnClick()
    }
}