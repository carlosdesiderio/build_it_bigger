package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask.EndpointTaskListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Functional test for {@link EndpointsAsyncTask}
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest{

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    private CountDownLatch latch;
    private EndpointsAsyncTask task;
    private String response;

    @Before
    public void setUp() {
        latch = new CountDownLatch(1);
        task = new EndpointsAsyncTask(new EndpointTaskListener() {
            @Override
            public void onJokeApiResponse(String joke) {
                response = joke;
                latch.countDown();
                Log.d(TAG, "greencat: inisde RESPONSE");

            }
        });
    }

    @Test
    public void whenTaskIsCalled_thenANotNullStringIsReturn() {
        // test that is null initially
        assertNull(response);
        task.execute();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // test that response has been set after calling task
        assertNotNull(response);
    }

    @Test
    public void whenTaskIsCalled_thenAValidStringIsReturn() {
        // test that is null initially
        assertNull(response);
        task.execute();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // test that response has been set after calling task
        assertFalse("String 'Connect timed out' has been returned", response.equals("connect timed out"));
    }
}
