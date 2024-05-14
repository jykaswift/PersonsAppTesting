package ru.tinkoff.favouritepersons.rules

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockServerPreferencesRule: TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                putUrl()
                base.evaluate()
            }
        }
    }

    private fun putUrl() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .getSharedPreferences("demo_url", Context.MODE_PRIVATE)
            .edit()
            .putString("url", "http://localhost:5000")
            .commit()
    }

}