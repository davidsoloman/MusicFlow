package com.musicflow.app;

import android.os.Bundle;

/**
 * Displays a web view that shows the API's home page for their documentation.
 */
public class DocumentationFragment extends WebViewFragment {

    public static DocumentationFragment newInstance(int position) {
        DocumentationFragment f = new DocumentationFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        f.position = position;
        return f;
    }

    public static CharSequence getTitle() {
        return "Getting Started";
    }

    @Override
    protected String getUrl() {
        return getResources().getString(R.string.documentation_home);
    }

    @Override
    protected String getHeaderText() {
        return getResources().getString(R.string.getting_started);
    }

}
