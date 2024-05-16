package ru.tinkoff.favouritepersons.wiremock

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import ru.tinkoff.favouritepersons.data.network.PersonDto
import wiremock.org.apache.commons.io.output.ByteArrayOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object WireMockHelper {

    fun fileToString(path: String, context: Context = InstrumentationRegistry.getInstrumentation().context): String {
        return BufferedReader(InputStreamReader(context.assets.open(path), StandardCharsets.UTF_8)).readText()
    }

    fun imageToByteArray(path: String, context: Context = InstrumentationRegistry.getInstrumentation().context): ByteArray {
        val inputStream = context.assets.open(path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }


}