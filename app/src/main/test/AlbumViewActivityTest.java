package com.musicflow.app.tests.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.musicflow.app.AlbumViewActivity;

/**
 * Created by whitney on 4/6/14.
 */
public class AlbumViewActivityTest extends ActivityUnitTestCase<AlbumViewActivity> {
    protected Intent launchIntent;

    public AlbumViewActivityTest() {
        super(AlbumViewActivity.class);
    }

    @Override
    protected void setUp() {
        launchIntent = new Intent(getInstrumentation().getTargetContext(), AlbumViewActivity.class);
        startActivity(launchIntent, null, null);
    }

    public void testActivityNotNUll() {
        startActivity(launchIntent, null, null);
        assertNotNull(getActivity());
    }
}
