package com.example.jetmentor;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsTest3 {

    private IdlingResource mActivityResource;

    @Rule
    public ActivityTestRule<loginActivity> mActivityTestRule =
            new ActivityTestRule<>(loginActivity.class);

    @Before
    public void setUp() {
        if (mActivityResource != null) {
            Espresso.unregisterIdlingResources(mActivityResource);
        }

        // Register Activity as idling resource
        mActivityResource = new BaseActivityIdlingResource(mActivityTestRule.getActivity());
        Espresso.registerIdlingResources(mActivityResource);
    }

    @After
    public void tearDown() {
        if (mActivityResource != null) {
            Espresso.unregisterIdlingResources(mActivityResource);
        }
    }
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void settingsTest3() {
        // Verify profile settings are accurate

        // Make sure we're signed out
        signOutIfPossible();
        // Sign in
        String email = "jpboone@crimson.ua.edu";
        String password = "password";
        enterEmail(email);
        enterPassword(password);

        // Click settings navigation button
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // Click profile button
        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.view_profile), withText("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());



        // Validate name
        ViewInteraction textView = onView(
                allOf(withId(R.id.name_val), withText("Jesse Boone"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
//        textView.check(matches(withText("Jesse Boone")));
        onView(withText("Jesse Boone"))
                .check(matches(isDisplayed()));

        pressBack();
    }

    @Test
    public void SettingsTest2() {
        // Verify edit profile settings works appropriately

        // Make sure we're signed out
        signOutIfPossible();
        // Sign in
        String email = "jpboone@crimson.ua.edu";
        String password = "password";
        enterEmail(email);
        enterPassword(password);

        // Click settings navigation button
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.edit_profile), withText("edit profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Jesse"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.company_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("LM"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.position_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("me"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.years_of_experience),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.submit_changes), withText("Submit Changes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.view_profile), withText("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.name_val), withText("Jesse"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                2),
//                        isDisplayed()));
//        textView2.check(matches(withText("Jesse")));
//
//        ViewInteraction textView3 = onView(
//                allOf(withId(R.id.company_val), withText("LM"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        textView3.check(matches(withText("LM")));
//
//        ViewInteraction textView4 = onView(
//                allOf(withId(R.id.position_val), withText("me"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                6),
//                        isDisplayed()));
//        textView4.check(matches(withText("me")));
//
//        ViewInteraction textView5 = onView(
//                allOf(withId(R.id.availability_val), withText("false"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                10),
//                        isDisplayed()));
//        textView5.check(matches(withText("false")));

//        ViewInteraction textView6 = onView(
//                allOf(withId(R.id.message_marker), withText("Message :"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                11),
//                        isDisplayed()));
//        textView6.check(matches(isDisplayed()));
//
        onView(withText("Jesse"))
                .check(matches(isDisplayed()));
        onView(withText("LM"))
                .check(matches(isDisplayed()));
        onView(withText("me"))
                .check(matches(isDisplayed()));
        onView(withText("1.0"))
                .check(matches(isDisplayed()));
        onView(withText("Message :"))
                .check(matches(isDisplayed()));

        pressBack();
    }

    @Test
    public void SettingsTest1() {
        // Verify about and support are accurate

        // Make sure we're signed out
        signOutIfPossible();
        // Sign in
        String email = "jpboone@crimson.ua.edu";
        String password = "password";
        enterEmail(email);
        enterPassword(password);

        // Click settings navigation button
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.about), withText("about"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton6.perform(click());

        onView(withText("This app was created to connect people who are seeking a job at a perticular company with current employees from that company. These employees would act as mentors for the candidates and help prepare them to both understand what the application and interviewing processes are and what its like to work there. The goal is to help candidates and and companies make better decisions in the interviewing proces."))
                .check(matches(isDisplayed()));

//        ViewInteraction textView7 = onView(
//                allOf(withId(R.id.about_info_text), withText("This app was created to connect people who are seeking a job at a perticular company with current employees from that company. These employees would act as mentors for the candidates and help prepare them to both understand what the application and interviewing processes are and what its like to work there. The goal is to help candidates and and companies make better decisions in the interviewing proces."),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textView7.check(matches(withText("This app was created to connect people who are seeking a job at a perticular company with current employees from that company. These employees would act as mentors for the candidates and help prepare them to both understand what the application and interviewing processes are and what its like to work there. The goal is to help candidates and and companies make better decisions in the interviewing proces.")));

        pressBack();

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.support), withText("support"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton7.perform(click());

        onView(withText("If you are experiencing issues with this app please make sure you are using the latest version. If no updates are available and your android operating system is up to date then feel free to reach out to us at jetmentor2020@gmail.com ."))
                .check(matches(isDisplayed()));

//        ViewInteraction textView8 = onView(
//                allOf(withId(R.id.support_info_text), withText("If you are experiencing issues with this app please make sure you are using the latest version. If no updates are available and your android operating system is up to date then feel free to reach out to us at jetmentor2020@gmail.com ."),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textView8.check(matches(withText("If you are experiencing issues with this app please make sure you are using the latest version. If no updates are available and your android operating system is up to date then feel free to reach out to us at jetmentor2020@gmail.com .")));


        pressBack();

    }

    @Test
    public void SettingsTest0() {
        // Use edit profile to revert back and test listing as available mentor

        // Make sure we're signed out
        signOutIfPossible();
        // Sign in
        String email = "jpboone@crimson.ua.edu";
        String password = "password";
        enterEmail(email);
        enterPassword(password);

        // Click settings navigation button
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.edit_profile), withText("edit profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.name_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("Jesse Boone"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.company_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("Lockheed Martin"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.position_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("Intern"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.years_of_experience),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("4"), closeSoftKeyboard());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.availability_input), withText("List as available mentor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.mentor_message),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("Cannot wait to hear from you!"));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.submit_changes), withText("Submit Changes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton9.perform(click());

        pressBack();
    }

    @Test
    public void SettingsTest() {

        // Verify scout allows

        // Make sure we're signed out
        signOutIfPossible();
        // Sign in
        String email = "jpboone@crimson.ua.edu";
        String password = "password";
        enterEmail(email);
        enterPassword(password);

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_scout), withContentDescription("Scout"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.scoutMentorsRV),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.request_mentor_message),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText("Would you be my mentor?"), closeSoftKeyboard());

        // pressBack();

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.request_mentorship_button), withText("REQUEST MENTORSHIP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton10.perform(click());

        // Would add a check in mentors here but mine keeps crashing
        // (need to fix firebase dependencies or google play permissions
        // not sure which one is causing the error)
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    private void enterEmail(String email) {
        ViewInteraction emailField = onView(
                CoreMatchers.allOf(withId(R.id.fieldEmail),
                        withParent(withId(R.id.emailPasswordFields)),
                        isDisplayed()));
        emailField.perform(replaceText(email));
    }
    private void enterPassword(String password) {
        ViewInteraction passwordField = onView(
                CoreMatchers.allOf(withId(R.id.fieldPassword),
                        withParent(withId(R.id.emailPasswordFields)),
                        isDisplayed()));
        passwordField.perform(replaceText(password));
        // Click sign in
        ViewInteraction appCompatButton = onView(
                CoreMatchers.allOf(withId(R.id.emailSignInButton), withText(R.string.sign_in),
                        withParent(withId(R.id.emailPasswordButtons)),
                        isDisplayed()));
        appCompatButton.perform(click());
    }
    private void signOutIfPossible() {
        try {
            onView(CoreMatchers.allOf(withId(R.id.signOutButton), withText(R.string.sign_out), isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Ignore
        }
    }


}








