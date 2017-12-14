package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moetaz on 12/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest {

    @Test
    public void testDoInBackground() throws Exception {
        MainActivityFragment fragment = new  MainActivityFragment();
        fragment.inTestMode = true;
        new EndpointAsyncTask(fragment).execute();
        Thread.sleep(10000);
        assertTrue( fragment.jokeContent != null);
    }
}
