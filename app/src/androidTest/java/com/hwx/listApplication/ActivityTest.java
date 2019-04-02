package com.hwx.listApplication;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.hwx.listApplication.view.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void checkListLoadsInActivity() {
        onView(withId(R.id.fab)).perform(click());
        SystemClock.sleep(3000);
        //проверяем на наличие фильмов в списке
        assertTrue("Has films in lits", getRVcount() > 0);

        //clicking to first object
        onView(withId(R.id.list_films)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(3000);
        //проверяем на наличие названия фильма
        onView(withId(R.id.tvFilmTitle)).check(matches(isDisplayed()));
    }

    //получаем список фильмов в RecyclerView
    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.list_films);
        return recyclerView.getAdapter().getItemCount();
    }
}
