package com.app.weather

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.weather.presentation.di.AppFragmentFactory
import com.app.weather.presentation.ui.weather.WeatherFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class WeatherFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        val fragmentFactory = AppFragmentFactory(FakeLocationHelper())
        launchFragmentInHiltContainer<WeatherFragment>(fragmentFactory = fragmentFactory) { }
    }

    @Test
    fun should_display_top_views() {

        onView(withId(R.id.current_temperature_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.current_condition_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.current_location_text_view))
            .check(matches(withText("")))
    }

    @Test
    fun should_display_empty_text_in_today_section_text_views() {

        onView(withId(R.id.wind_speed_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.humidity_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.cloud_percentage_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.pressure_text_view))
            .check(matches(withText("")))
    }

    @Test
    fun should_display_views_in_forecast_section() {

        onView(withId(R.id.temp_1_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.temp_2_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.temp_3_text_view))
            .check(matches(withText("")))

        onView(withId(R.id.weath_ic_1_image_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.weath_ic_2_image_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.weath_ic_3_image_view))
            .check(matches(isDisplayed()))

        onView(withId(R.id.humidity_1_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.humidity_2_text_view))
            .check(matches(withText("")))
        onView(withId(R.id.humidity_3_text_view))
            .check(matches(withText("")))
    }

    @Test
    fun should_match_text_values_with_text_in_today_section() {

        onView(withId(R.id.wind_speed_title)).check(matches(withText("Wind Speed")))
        onView(withId(R.id.humidity_title)).check(matches(withText("Humidity")))
        onView(withId(R.id.cloud_percentage_title)).check(matches(withText("Cloud Percentage")))
        onView(withId(R.id.pressure_title)).check(matches(withText("Pressure")))
    }

    @Test
    fun should_match_text_values_with_text_in_forecast_section() {

        onView(withId(R.id.day_1_text_view)).check(matches(withText("Today")))
        onView(withId(R.id.day_2_text_view)).check(matches(withText("Tomorrow")))
        onView(withId(R.id.day_3_text_view)).check(matches(withText("")))
    }

}
