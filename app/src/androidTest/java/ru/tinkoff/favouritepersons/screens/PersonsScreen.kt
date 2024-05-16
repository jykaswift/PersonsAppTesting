package ru.tinkoff.favouritepersons.screens

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.qameta.allure.kotlin.Allure.step
import org.hamcrest.Matcher
import ru.tinkoff.favouritepersons.R
import ru.tinkoff.favouritepersons.customMatcher.PersonsSortMatcher
import ru.tinkoff.favouritepersons.domain.Gender
import ru.tinkoff.favouritepersons.dto.DOB
import ru.tinkoff.favouritepersons.presentation.PersonFields
import ru.tinkoff.favouritepersons.presentation.viewmodel.MainViewModel
import ru.tinkoff.favouritepersons.screens.elements.SortMenu

class PersonsScreen: KScreen<PersonsScreen>() {
    override val layoutId = R.layout.activity_main
    override val viewClass = MainViewModel::class.java

    private val addPersonButton = KButton { withId(R.id.fab_add_person) }
    private val addPersonNetworkButton = KButton { withId(R.id.fab_add_person_by_network) }
    private val addPersonManuallyButton = KButton { withId(R.id.fab_add_person_manually) }
    private val personsList = KRecyclerView(
        builder = { withId(R.id.rv_person_list) },
        itemTypeBuilder = { itemType(::PersonItem) }
    )
    val sortMenu = SortMenu { withId(R.id.action_item_sort) }

    fun clickAddButton() {
        step("Нажимаем кнопку добавить студента") {
            addPersonButton.click()
        }
    }
    fun clickAddPersonFromNetworkButton() {
        step("Нажимаем кнопку получить студента из сети") {
            addPersonNetworkButton.click()
        }
    }

    fun clickAddPersonManuallyButton() {
        step("Нажимаем кнопку создать студента") {
            addPersonManuallyButton.click()
        }
    }

    fun checkPersonNameContains(string: String, atPosition: Int) {
        step("Проверяем имя $atPosition студента в списке") {
            personsList.childAt<PersonItem>(atPosition) {
                name.containsText(string)
            }
        }
    }

    fun checkPersonAgeBy(dateOfBirth: DOB, atPosition: Int) {
        val currentYear = java.time.Year.now().value
        val age = currentYear - dateOfBirth.year
        step("Проверяем возраст $atPosition студента в списке") {
            personsList.childAt<PersonItem>(atPosition) {
                privateInfo.containsText(age.toString())
            }
        }
    }

    fun checkPersonGenderWith(gender: Gender, atPosition: Int) {
        step("Проверяем пол $atPosition студента в списке") {
            personsList.childAt<PersonItem>(atPosition) {
                privateInfo.containsText(gender.toString())
            }
        }
    }

    fun checkPersonRatingWith(rating: Int, atPosition: Int) {
        step("Проверяем рейтинг $atPosition студента в списке") {
            personsList.childAt<PersonItem>(atPosition) {
                this.rating.containsText(rating.toString())
            }
        }
    }

    fun deletePersonAt(position: Int) {
        step("Удаляем $position студента в списке") {
            personsList.swipeLeft(position)
        }
    }

    fun checkNumberOfPersons(count: Int) {
        step("Проверяем количество студентов в списке") {
            personsList.hasSize(count)
        }
    }

    fun clickPersonAt(position: Int) {
        step("Нажимаем на первого студента в списке") {
            personsList.childAt<PersonItem>(position) {
                click()
            }
        }
    }

    fun checkPersonsSortedByRating() {
        step("Проверяем что студенты отсортированы по рейтингу") {
            personsList.assert { matches(PersonsSortMatcher(PersonFields.BY_RATING)) }
        }

    }

    fun checkPersonsSortedByAge() {
        step("Проверяем что студенты отсортированы по возрасту") {
            personsList.assert { matches(PersonsSortMatcher(PersonFields.BY_AGE)) }
        }
    }

    fun checkPersonsSortedByName() {
        step("Проверяем что студенты отсортированы по имени") {
            personsList.assert { matches(PersonsSortMatcher(PersonFields.BY_SURNAME)) }
        }
    }
}

private class PersonItem(matcher: Matcher<View>) : KRecyclerItem<PersonItem>(matcher) {
    val name = KTextView(matcher) { withId(R.id.person_name) }
    val privateInfo = KTextView(matcher) { withId(R.id.person_private_info) }
    val rating = KTextView(matcher) { withId(R.id.person_rating) }
}

fun KRecyclerView.swipeLeft(itemIndex: Int) {
    val swipeAction = GeneralSwipeAction(
        Swipe.FAST,
        GeneralLocation.BOTTOM_RIGHT,
        GeneralLocation.BOTTOM_LEFT, Press.
        PINPOINT
    )
    val viewAction = RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemIndex, swipeAction)
    view.perform(viewAction)
}