package ru.tinkoff.favouritepersons.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import ru.tinkoff.favouritepersons.R
import ru.tinkoff.favouritepersons.presentation.PersonErrorMessages

class EditPersonScreen: KScreen<PersonsScreen>() {
    override val layoutId: Int = R.layout.person_item_activity
    override val viewClass: Class<*>? = null

    private val nameTextInputLayout = KTextInputLayout { withId(R.id.til_name) }
    private val surnameTextInputLayout = KTextInputLayout { withId(R.id.til_surname) }
    private val genderTextInputLayout = KTextInputLayout { withId(R.id.til_gender) }
    private val birthDateTextInputLayout = KTextInputLayout{ withId(R.id.til_birthdate) }
    private val emailTextInputLayout = KTextInputLayout { withId(R.id.til_email) }
    private val phoneTextInputLayout= KTextInputLayout { withId(R.id.til_phone) }
    private val addressTextInputLayout = KTextInputLayout { withId(R.id.til_address) }
    private val photoTextInputLayout = KTextInputLayout { withId(R.id.til_image_link) }
    private val ratingTextInputLayout = KTextInputLayout { withId(R.id.til_score) }
    private val saveButton = KButton { withId(R.id.submit_button) }

    fun checkNameEditTextDisplayed() = nameTextInputLayout.edit.isDisplayed()
    fun checkSurnameEditTextDisplayed() = surnameTextInputLayout.edit.isDisplayed()
    fun checkGenderEditTextDisplayed() = genderTextInputLayout.edit.isDisplayed()
    fun checkBirthDateEditTextDisplayed() = birthDateTextInputLayout.edit.isDisplayed()
    fun checkEmailEditTextDisplayed() = emailTextInputLayout.edit.isDisplayed()
    fun checkPhoneNumberEditTextDisplayed() = phoneTextInputLayout.edit.isDisplayed()
    fun checkAddressEditTextDisplayed() = addressTextInputLayout.edit.isDisplayed()
    fun checkPhotoEditTextDisplayed() = photoTextInputLayout.edit.isDisplayed()
    fun checkRatingEditTextDisplayed() = ratingTextInputLayout.edit.isDisplayed()
    fun clickSaveButton() = saveButton.click()
    fun enterName(name: String) = nameTextInputLayout.edit.replaceText(name)
    fun enterSurname(surname: String) = surnameTextInputLayout.edit.replaceText(surname)
    fun enterGender(gender: String) = genderTextInputLayout.edit.replaceText(gender)
    fun enterDOB(dob: String) = birthDateTextInputLayout.edit.replaceText(dob)
    fun enterEmail(email: String) = emailTextInputLayout.edit.replaceText(email)
    fun enterPhoneNumber(phoneNumber: String) = phoneTextInputLayout.edit.replaceText(phoneNumber)
    fun enterAddress(address: String) = addressTextInputLayout.edit.replaceText(address)
    fun enterPhotoURL(photoURL: String) = photoTextInputLayout.edit.replaceText(photoURL)
    fun enterRating(rating: String) = ratingTextInputLayout.edit.replaceText(rating.toString())
    fun checkNameEditTextFocusedOnClick() {
        nameTextInputLayout.edit {
            click()
            isFocused()
        }
    }

    fun checkSurnameEditTextFocusedOnClick() {
        surnameTextInputLayout.edit {
            click()
            isFocused()
        }
    }

    fun checkGenderEditTextFocusedOnClick() {
        genderTextInputLayout.edit {
            click()
            isFocused()
        }
    }

    fun checkDOBEditTextFocusedOnClick() {
        birthDateTextInputLayout.edit {
            click()
            isFocused()
        }
    }

    fun checkRatingEditTextFocusedOnClick() {
        ratingTextInputLayout.edit {
            click()
            isFocused()
        }
    }


    // Валидация
    // В тесткейсах описано изменение цвета, но ошибка предполагает изменение цвета
    // и делать кастомный матчер на эту проверку звучит не очень)
    fun checkNameErrorDisplayed() {
        nameTextInputLayout.hasError(PersonErrorMessages.NAME.errorMessage)
    }

    fun checkSurnameErrorDisplayed() {
        surnameTextInputLayout.hasError(PersonErrorMessages.SURNAME.errorMessage)
    }

    fun checkGenderErrorDisplayed() {
        genderTextInputLayout.hasError(PersonErrorMessages.GENDER.errorMessage)
    }

    fun checkDOBErrorDisplayed() {
        birthDateTextInputLayout.hasError(PersonErrorMessages.BIRTHDATE.errorMessage)
    }

    fun checkRatingErrorDisplayed() {
        ratingTextInputLayout.hasError(PersonErrorMessages.RATING.errorMessage)
    }


}