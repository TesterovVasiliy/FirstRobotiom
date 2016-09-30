package com.warg.firstrobotiom;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Display;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo = null;
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.warg.firstrobotiom", appContext.getPackageName());
    }

    @Test
    public void ClicOnButton() throws Exception {
        solo.clickOnText(solo.getString(R.string.btnText));
        solo.waitForLogMessage("Click", 1000);
    }

    @Test
    public void clickActivityStarted() throws Exception {
        solo.clickOnText(solo.getString(R.string.btnText));
        solo.waitForActivity("Activity not visible", 1000);
    }

    @Test
    public void login() throws Exception {
        solo.clickOnText(solo.getString(R.string.btnText));
        Solo soloLogin = new Solo(InstrumentationRegistry.getInstrumentation(), solo.getCurrentActivity());
        soloLogin.enterText((EditText) soloLogin.getView(R.id.email), "foo@example.com");
        soloLogin.enterText((EditText) soloLogin.getView(R.id.password), "hello");
        soloLogin.clickOnText(soloLogin.getString(R.string.action_sign_in));
        Thread.sleep(2000);
        soloLogin.waitForLogMessage("Succes", 2000);
    }

    @Test
    public void openMenu() throws Exception {
        solo.clickOnText(solo.getString(R.string.btnText));
        Solo soloNavigationDrawer = new Solo(InstrumentationRegistry.getInstrumentation(), solo.getCurrentActivity());
        Display display = soloNavigationDrawer.getCurrentActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        soloNavigationDrawer.drag(0, width / 2, height / 2, height / 2, 1);
        Thread.sleep(2000);
        assertTrue(((NavigationView) soloNavigationDrawer.getView(R.id.nav_view)).isActivated());
    }
}


