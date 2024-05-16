package ru.tinkoff.favouritepersons.wiremock

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import wiremock.org.apache.commons.io.output.ByteArrayOutputStream

object WireMockHelper {
    fun imageToByteArray(path: String, context: Context = InstrumentationRegistry.getInstrumentation().context): ByteArray {
        val inputStream = context.assets.open(path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}