package ru.tinkoff.favouritepersons

import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.rules.CleanPreferencesRule
import ru.tinkoff.favouritepersons.rules.MockServerPreferencesRule
import ru.tinkoff.favouritepersons.screens.StudentsScreen
import ru.tinkoff.favouritepersons.wiremock.WireMockHelper.fileToString

class Test: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 10_000, intervalMs = 250)
        }
    )
) {

    private val activityScenarioRule = activityScenarioRule<MainActivity>()
    private val mockRule = WireMockRule(5000)
    private val mockServerPreferencesRule = MockServerPreferencesRule()
    private val cleanPreferencesRule = CleanPreferencesRule()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(mockRule)
        .around(mockServerPreferencesRule)
        .around(activityScenarioRule)
        .around(cleanPreferencesRule)

    @Test
    fun useAppContext() {

        stubFor(get("/api/")
            .willReturn(ok(fileToString("student_response.json")))
        )

        val studentsScreen = StudentsScreen()
        studentsScreen.clickAddButton()
        studentsScreen.clickAddPersonFromNetworkButton()
        Thread.sleep(5000)

    }
}