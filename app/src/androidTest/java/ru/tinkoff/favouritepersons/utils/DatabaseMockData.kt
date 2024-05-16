package ru.tinkoff.favouritepersons.utils

import ru.tinkoff.favouritepersons.domain.Gender
import ru.tinkoff.favouritepersons.domain.PersonItem

class DatabaseMockData {

    val malePerson = PersonItem(
        id = 2,
        name = "Михаил",
        surname ="Круг",
        birthDate= "1990-01-01",
        gender = Gender.MALE,
        age = 33,
        email = "test@gmail.com",
        phone = "89999999999",
        address = "Иваново",
        imageLink = "www.test.ru",
        rating = 20
    )

    val femalePerson = PersonItem(
        id = 1,
        name = "Даша",
        surname ="Квадрат",
        birthDate= "2001-01-01",
        gender = Gender.FEMALE,
        age = 18,
        email = "test@gmail.com",
        phone = "89999999999",
        address = "Иваново",
        imageLink = "www.test.ru",
        rating = 30
    )

    val secondMalePerson = PersonItem(
        id = 3,
        name = "Алексей",
        surname ="Ромб",
        birthDate= "2001-01-01",
        gender = Gender.MALE,
        age = 22,
        email = "test@gmail.com",
        phone = "89999999999",
        address = "Иваново",
        imageLink = "www.test.ru",
        rating = 10
    )

    val sortMockData = listOf(malePerson, femalePerson, secondMalePerson)
}