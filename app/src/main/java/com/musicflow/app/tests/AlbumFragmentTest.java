package com.musicflow.app.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.GridView;

import com.musicflow.app.AlbumFragment;
import com.musicflow.app.MainActivity;
import com.musicflow.app.R;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;

public class AlbumFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    protected GridView gridView;
    protected PullToRefreshLayout pullToRefreshLayout;

    public AlbumFragmentTest() {
        super(MainActivity.class);
    }

    public void loadFragmentUiElements() {
        gridView = (GridView) getActivity().findViewById(R.id.gridview);
        pullToRefreshLayout = (PullToRefreshLayout) getActivity().findViewById(R.id.ptr_layout);
    }

}
