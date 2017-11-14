package com.sample.weather.test;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.weather.R;
import com.sample.weather.activity.WeatherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by VimalRaj on 11/13/2017.
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class WeatherActivityTest {

    @Rule
    public ActivityTestRule<WeatherActivity> weatherActivityTestRule = new ActivityTestRule<>(WeatherActivity.class);

    /**
     * Test case for page load
     */
    @Test
    public void verifyPageLoad() {
        onView(withId(R.id.txtAutoInput))
                .perform(typeText(""), closeSoftKeyboard());
    }

    /**
     * Test case for search input
     */
    @Test
    public void verifySearchInput() {
        onView(withId(R.id.txtAutoInput))
                .perform(typeText("San Francisco"), closeSoftKeyboard());
    }

    /**
     * Test case for search input and verify display result
     */
    @Test
    public void verifySearchButtonWithValidCity() {
        onView(withId(R.id.txtAutoInput))
                .perform(typeText("San Francisco"), closeSoftKeyboard());
        ViewInteraction searchButton = onView(allOf(withId(R.id.btnSearch)));
        searchButton.perform(click());
        onView(withId(R.id.txtTemperature)).check(matches(isDisplayed()));
    }

    /**
     * Test case for search input and verify invalid result
     */
    @Test
    public void verifySearchButtonWithInValidCity() {
        onView(withId(R.id.txtAutoInput))
                .perform(typeText("abcdef"), closeSoftKeyboard());
        ViewInteraction searchButton = onView(allOf(withId(R.id.btnSearch)));
        searchButton.perform(click());
        onView(withId(R.id.txtNoResult)).check(matches(isDisplayed()));
    }

    /**
     * Test case for search input with auto suggest input.
     */
    @Test
    public void verifyAutoSuggestClick() {
        onView(withId(R.id.txtAutoInput))
                .perform(typeText("San Franscisco"), closeSoftKeyboard());
        onData(withText("San Franscisco"))
                .inRoot(withDecorView(not(is(weatherActivityTestRule.getActivity().getWindow().getDecorView()))));
        /*onData(anything())
                .inAdapterView(withId(R.id.txtAutoInput))
                .atPosition(0)
                .onChildView(withId(R.id.txtItem)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(click());*/
    }

}
