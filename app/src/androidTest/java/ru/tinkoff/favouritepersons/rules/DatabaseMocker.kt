package ru.tinkoff.favouritepersons.rules

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import ru.tinkoff.favouritepersons.data.PersonListRepositoryImpl
import ru.tinkoff.favouritepersons.data.network.RetrofitService
import ru.tinkoff.favouritepersons.domain.PersonItem
import ru.tinkoff.favouritepersons.room.PersonDataBase


// Создал этот класс, если понадобится мокать БД не в правиле, например, в before {} after {}
class DatabaseMocker(private var persons: List<PersonItem> = listOf()) {
    private var repository : PersonListRepositoryImpl? = null

    fun setupDataBaseWith(persons: List<PersonItem>) {
        val context: Context = ApplicationProvider.getApplicationContext()
        val randomUserApiService = RetrofitService.getInstance("")
        val dao = Room.inMemoryDatabaseBuilder(context, PersonDataBase::class.java)
            .build()
            .personsDao()
        repository = PersonListRepositoryImpl.getInstance(dao, randomUserApiService)
        this.persons = persons
        savePersonsToRepository()
    }

    fun savePersonsToRepository() {
        runBlocking {
            persons.forEach {
                repository?.addPersonItem(it)
            }
        }
    }
    fun removePersonsFromRepository() {
        runBlocking {
            persons.forEach {
                println(repository)
                repository?.deletePersonItem(it)
            }
        }
        persons = listOf()
    }

}