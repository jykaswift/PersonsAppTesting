package ru.tinkoff.favouritepersons.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import ru.tinkoff.favouritepersons.R

class StudentsScreen: KScreen<StudentsScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val addPersonButton = KButton { withId(R.id.fab_add_person) }
    private val addPersonNetworkButton = KButton { withId(R.id.fab_add_person_by_network) }

    fun clickAddButton() {
        addPersonButton.click()
    }

    fun clickAddPersonFromNetworkButton() {
        addPersonNetworkButton.click()
    }
}