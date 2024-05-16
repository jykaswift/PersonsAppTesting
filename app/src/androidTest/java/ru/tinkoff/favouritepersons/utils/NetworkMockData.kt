package ru.tinkoff.favouritepersons.utils

import com.google.gson.GsonBuilder
import ru.tinkoff.favouritepersons.data.network.Coordinates
import ru.tinkoff.favouritepersons.data.network.Dob
import ru.tinkoff.favouritepersons.data.network.ID
import ru.tinkoff.favouritepersons.data.network.Info
import ru.tinkoff.favouritepersons.data.network.Location
import ru.tinkoff.favouritepersons.data.network.Login
import ru.tinkoff.favouritepersons.data.network.Name
import ru.tinkoff.favouritepersons.data.network.PersonDto
import ru.tinkoff.favouritepersons.data.network.Persons
import ru.tinkoff.favouritepersons.data.network.Picture
import ru.tinkoff.favouritepersons.data.network.Street
import ru.tinkoff.favouritepersons.data.network.Timezone

class NetworkMockData {

    private val info = Info(
        seed = "74ba64f4c75cc64c",
        results = 1,
        page = 1,
        version = "1.4"
    )

    private val location = Location(
        street = Street(number = 7631, name = "Rue Bony"),
        city = "Nantes",
        state = "Aude",
        country = "France",
        postcode = "42673",
        coordinates = Coordinates(latitude = "-19.1773", longitude = "34.4093"),
        timezone = Timezone(offset = "-8:00", description = "Pacific Time (US & Canada)")
    )

    private val name = Name(
        title = "Miss",
        first = "Мария",
        last = "Круг"
    )

    private val loginData = Login(
        uuid = "8f22c691-59bc-4c55-8cb8-ab84eae12384",
        username = "orangeostrich767",
        password = "punisher",
        salt = "LQVXvtaV",
        md5 = "fb935026c0f8cb9dcf368598f93cf8b1",
        sha1 = "4249b5a6bed5fb981eabe0fce158b78ae1d13fe3",
        sha256 = "20f606422e29aeeec707c41420f7aedb69929598ec646d2e7ded6ba5a7dc6a3c"
    )

    private val dob = Dob(
        date = "1976-03-29T23:16:31.780Z",
        age = 48
    )

    private val picture = Picture(
        large = "http://localhost:5000/api/portraits/women/89.jpg",
        medium = "http://localhost:5000/api/portraits/med/women/89.jpg",
        thumbnail = "http://localhost:5000/api/portraits/thumb/women/89.jpg"
    )

    private val person = PersonDto(
        gender = "female",
        name = name,
        location = location,
        email = "maria.krug@example.com",
        login = loginData,
        dob = dob,
        registered = dob,
        phone = "89999999999",
        cell = "06-55-52-64-17",
        id = ID(name = "INSEE", value = "2760233437744 87"),
        picture = picture,
        nat = "RU"
    )

    private val persons = Persons(
        results = listOf(person),
        info = info
    )

    companion object {
        fun getJsonMockData(): String {
            val gson = GsonBuilder().serializeNulls().create()
            return gson.toJson(NetworkMockData().persons)
        }
    }
}