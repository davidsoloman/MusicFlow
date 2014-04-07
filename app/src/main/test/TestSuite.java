package com.musicflow.app.tests.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Takes all the classes under test and adds them to test suite to be ran. This file should not need
 * to be edited going forward.
 */

public class TestSuite extends junit.framework.TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(TestSuite.class).includeAllPackagesUnderHere().build();
    }
}
