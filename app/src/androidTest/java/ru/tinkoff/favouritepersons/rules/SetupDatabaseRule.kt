package ru.tinkoff.favouritepersons.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import ru.tinkoff.favouritepersons.domain.PersonItem
class SetupDatabaseRule(private val persons: List<PersonItem>) : TestRule {

    private val databaseMocker = DatabaseMocker()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                databaseMocker.setupDataBaseWith(persons)
                base.evaluate()
                databaseMocker.removePersonsFromRepository()
            }
        }
    }
}