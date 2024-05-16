package ru.tinkoff.favouritepersons.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import io.qameta.allure.kotlin.Allure.step
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

    fun checkNameEditTextDisplayed() {
        step("Проверяем, что текстовое поле имени отображается на экране") {
            nameTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkSurnameEditTextDisplayed()  {
        step("Проверяем, что текстовое поле фамилии отображается на экране") {
            surnameTextInputLayout.edit.isDisplayed()
        }

    }
    fun checkGenderEditTextDisplayed()  {
        step("Проверяем, что текстовое поле пола отображается на экране") {
            genderTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkBirthDateEditTextDisplayed() {
        step("Проверяем, что текстовое поле даты рождения отображается на экране") {
            birthDateTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkEmailEditTextDisplayed() {
        step("Проверяем, что текстовое поле email отображается на экране") {
            emailTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkPhoneNumberEditTextDisplayed() {
        step("Проверяем, что текстовое поле номера телефона отображается на экране") {
            phoneTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkAddressEditTextDisplayed() {
        step("Проверяем, что текстовое поле адреса отображается на экране") {
            addressTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkPhotoEditTextDisplayed() {
        step("Проверяем, что текстовое поле фото url отображается на экране") {
            photoTextInputLayout.edit.isDisplayed()
        }
    }
    fun checkRatingEditTextDisplayed() {
        step("Проверяем, что текстовое поле рейтинга отображается на экране") {
            ratingTextInputLayout.edit.isDisplayed()
        }
    }
    fun clickSaveButton() {
        step("Нажимаем кнопку сохранить") {
            saveButton.click()
        }
    }
    fun enterName(name: String) {
        step("Вводим имя: $name") {
            nameTextInputLayout.edit.replaceText(name)
        }
    }
    fun enterSurname(surname: String) {
        step("Вводим фамилию: $surname") {
            surnameTextInputLayout.edit.replaceText(surname)
        }
    }
    fun enterGender(gender: String) {
        step("Вводим пол: $gender") {
            genderTextInputLayout.edit.replaceText(gender)
        }
    }
    fun enterDOB(dob: String) {
        step("Вводим дату рождения: $dob") {
            birthDateTextInputLayout.edit.replaceText(dob)
        }
    }
    fun enterEmail(email: String) {
        step("Вводим email: $email") {
            emailTextInputLayout.edit.replaceText(email)
        }
    }
    fun enterPhoneNumber(phoneNumber: String) {
        step("Вводим телефон: $phoneNumber") {
            phoneTextInputLayout.edit.replaceText(phoneNumber)
        }
    }
    fun enterAddress(address: String) {
        step("Вводим адрес: $address") {
            addressTextInputLayout.edit.replaceText(address)
        }
    }
    fun enterPhotoURL(photoURL: String) {
        step("Вводим url фото: $photoURL") {
            photoTextInputLayout.edit.replaceText(photoURL)
        }
    }
    fun enterRating(rating: String) {
        step("Вводим рейтинг: $rating") {
            ratingTextInputLayout.edit.replaceText(rating)
        }
    }
    fun checkNameEditTextFocusedOnClick() {
        step("Проверяем, что текстовое поле имени фокусируется при нажатии") {
            nameTextInputLayout.edit {
                click()
                isFocused()
            }
        }
    }

    fun checkSurnameEditTextFocusedOnClick() {
        step("Проверяем, что текстовое поле фамилии фокусируется при нажатии") {
            surnameTextInputLayout.edit {
                click()
                isFocused()
            }
        }
    }

    fun checkGenderEditTextFocusedOnClick() {
        step("Проверяем, что текстовое поле пола фокусируется при нажатии") {
            genderTextInputLayout.edit {
                click()
                isFocused()
            }
        }
    }

    fun checkDOBEditTextFocusedOnClick() {
        step("Проверяем, что текстовое поле даты рождения фокусируется при нажатии") {
            birthDateTextInputLayout.edit {
                click()
                isFocused()
            }
        }
    }

    fun checkRatingEditTextFocusedOnClick() {
        step("Проверяем, что текстовое поле рейтинга фокусируется при нажатии") {
            ratingTextInputLayout.edit {
                click()
                isFocused()
            }
        }
    }


    // Валидация
    // В тесткейсах описано изменение цвета, но ошибка предполагает изменение цвета
    // и делать кастомный матчер на эту проверку звучит не очень)
    fun checkNameErrorDisplayed() {
        val error = PersonErrorMessages.NAME.errorMessage
        step("Проверяем, что высветилась ошибка валидации имени: $error") {
            nameTextInputLayout.hasError(error)
        }

    }

    fun checkSurnameErrorDisplayed() {
        val error = PersonErrorMessages.SURNAME.errorMessage
        step("Проверяем, что высветилась ошибка валидации фамилии: $error") {
            surnameTextInputLayout.hasError(error)
        }

    }

    fun checkGenderErrorDisplayed() {
        val error = PersonErrorMessages.GENDER.errorMessage
        step("Проверяем, что высветилась ошибка валидации пола: $error") {
            genderTextInputLayout.hasError(error)
        }
    }

    fun checkDOBErrorDisplayed() {
        val error = PersonErrorMessages.BIRTHDATE.errorMessage
        step("Проверяем, что высветилась ошибка валидации даты рождения: $error") {
            birthDateTextInputLayout.hasError(error)
        }
    }

    fun checkRatingErrorDisplayed() {
        val error = PersonErrorMessages.RATING.errorMessage
        step("Проверяем, что высветилась ошибка валидации рейтинга: $error") {
            ratingTextInputLayout.hasError(error)
        }
    }


}