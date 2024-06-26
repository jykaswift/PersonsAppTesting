package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.MockServerPreferencesRule
import ru.tinkoff.favouritepersons.screens.PersonsScreen
import ru.tinkoff.favouritepersons.utils.NetworkMockData
import ru.tinkoff.favouritepersons.wiremock.WireMockHelper.imageToByteArray

@Epic("Приложение студентов")
@Feature("Сетевые функции")
@RunWith(AllureAndroidJUnit4::class)
class NetworkTests: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 3_000, intervalMs = 250)
        }
    ).apply {
        stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
    }
)  {

    private val activityScenarioRule = activityScenarioRule<MainActivity>()
    private val mockServerPreferencesRule = MockServerPreferencesRule()
    private val wireMockRule = WireMockRule(5000)

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(wireMockRule)
        .around(mockServerPreferencesRule)
        .around(activityScenarioRule)

    // Тесткейс №6
    // По идее этот тест лучше не мокать, но я нигде больше не использую мок
    // поэтому, чтобы показать что мок работает, замокал тут ответ)
    @Test
    @Story("Получение студента из сети")
    @DisplayName("Проверка получения студента из сети")
    fun getPersonFromNetwork() {
        stubFor(get(urlPathMatching(".*/api/"))
            .willReturn(ok(NetworkMockData.getJsonMockData()))
        )

        stubFor(get(urlPathMatching(".*/med/.*"))
            .willReturn(
                aResponse()
                .withStatus(200)
                    .withBody(imageToByteArray("image.jpg"))
            )
        )

        val personsScreen = PersonsScreen()
        personsScreen.clickAddButton()
        personsScreen.clickAddPersonFromNetworkButton()
        personsScreen.checkNumberOfPersons(1)
    }
}