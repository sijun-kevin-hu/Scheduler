package com.example.scheduler;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CourseTest {
    private Course calculus;
    @Before
    public void setUp() {
        calculus = new Course("calculus");
    }

    @Test
    public void addTest() {
        // Context of the app under test
        Assignment homework = new Assignment();
        Assignment exam = new Assignment();

        calculus.add(homework);
        calculus.add(exam);

        assertEquals(calculus.getArray()[0], homework);
        assertEquals(calculus.getArray()[1], exam);
    }
}