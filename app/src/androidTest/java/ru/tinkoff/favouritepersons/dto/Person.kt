package ru.tinkoff.favouritepersons.dto

import ru.tinkoff.favouritepersons.domain.Gender

data class Person (
    val name: String,
    val surname: String,
    val gender: Gender,
    val dob: DOB,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val photoUrl: String,
    val rating: Int
)

data class DOB (
    val year: Int,
    val month: Int,
    val day: Int
) {
    override fun toString(): String {
        return String.format("%04d-%02d-%02d", year, month, day)
    }
}

