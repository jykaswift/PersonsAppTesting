package ru.tinkoff.favouritepersons.screens.elements

import io.github.kakaocup.kakao.common.builders.ViewBuilder
import io.github.kakaocup.kakao.common.views.KBaseView
import io.github.kakaocup.kakao.text.KButton
import io.qameta.allure.kotlin.Allure.step
import ru.tinkoff.favouritepersons.R

class SortMenu(function: ViewBuilder.() -> Unit) : KBaseView<SortMenu>(function) {

    private val defaultButton = KButton { withId(R.id.bsd_rb_default) }
    private val ageButton = KButton { withId(R.id.bsd_rb_age) }
    private val ratingButton = KButton { withId(R.id.bsd_rb_rating) }
    private val nameButton = KButton { withId(R.id.bsd_rb_name) }

    fun clickDefaultButton() {
        step("Нажимает кнопку сортировки по умолчанию") {
            defaultButton.click()
        }

    }
    fun clickAgeButton() {
        step("Нажимает кнопку сортировки по возрасту") {
            ageButton.click()
        }
    }
    fun clickRatingButton() {
        step("Нажимает кнопку сортировки по рейтингу") {
            ratingButton.click()
        }
    }
    fun clickNameButton() {
        step("Нажимает кнопку сортировки по имени") {
            nameButton.click()
        }
    }

}