package ru.tinkoff.favouritepersons.customMatcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import ru.tinkoff.favouritepersons.R
import ru.tinkoff.favouritepersons.presentation.PersonFields

class PersonsSortMatcher(private val field: PersonFields) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("persons is not sorted by ${field.field}}")
    }

    override fun matchesSafely(recyclerView: RecyclerView): Boolean {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager == null ||
            recyclerView.adapter == null ||
            field == PersonFields.NO_METHOD)
        { return false }

        val itemCount = recyclerView.adapter?.itemCount ?: 0
        if (itemCount <= 1) {
            return true
        }

        return isRecyclerViewSorted(layoutManager, itemCount)
    }

    private fun isRecyclerViewSorted(
        layoutManager: RecyclerView.LayoutManager,
        itemCount: Int
    ): Boolean {
        return when (field) {
            PersonFields.BY_SURNAME -> compareStrings(itemCount, layoutManager, ::getSurnameValueAtPosition)
            PersonFields.BY_RATING -> compareNumbers(itemCount, layoutManager, ::getRatingAtPosition)
            PersonFields.BY_AGE -> compareNumbers(itemCount, layoutManager, ::getAgeAtPosition)
            PersonFields.NO_METHOD -> return true
        }
    }


    private fun compareNumbers(
        itemCount: Int,
        layoutManager: RecyclerView.LayoutManager,
        compareFunction: (RecyclerView.LayoutManager, Int) -> Int
    ): Boolean {
        for (i in 0 until itemCount) {
            val currentRating = compareFunction(layoutManager, i)
            val nextRating = compareFunction(layoutManager, i + 1)
            if (currentRating < nextRating) {
                return false
            }
        }
        return true
    }

    private fun compareStrings(
        itemCount: Int,
        layoutManager: RecyclerView.LayoutManager,
        compareFunction: (RecyclerView.LayoutManager, Int) -> String
    ): Boolean {
        for (i in 0 until itemCount) {
            val currentRating = compareFunction(layoutManager, i)
            val nextRating = compareFunction(layoutManager, i + 1)
            if (currentRating < nextRating) {
                return false
            }
        }
        return true
    }


    private fun getRatingAtPosition(layoutManager: RecyclerView.LayoutManager, position: Int): Int {
        val view = layoutManager.findViewByPosition(position)
        return view?.findViewById<TextView>(R.id.person_rating)?.text?.toString()?.toIntOrNull() ?: 0
    }

    private fun getAgeAtPosition(layoutManager: RecyclerView.LayoutManager, position: Int): Int {
        val view = layoutManager.findViewByPosition(position)
        val privateInfo = view?.findViewById<TextView>(R.id.person_private_info)?.text?.toString()
        return privateInfo?.filter { it.isDigit() }?.toIntOrNull() ?: 0
    }

    private fun getSurnameValueAtPosition(
        layoutManager: RecyclerView.LayoutManager,
        position: Int
    ): String {
        val view = layoutManager.findViewByPosition(position)
        val fullName = view?.findViewById<TextView>(R.id.person_name)?.text?.toString()?.split(" ")

        if (fullName == null || fullName.size < 2) return ""
        return fullName[1]
    }


}
