package com.musicflow.app;

import android.os.Bundle;

public class DocumentationFragment extends WebViewFragment {
    
    public static DocumentationFragment newInstance(int position) {
        DocumentationFragment f = new DocumentationFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        f.position = position;
        return f;
    }
    
    @Override
    protected String getUrl() {
        return getResources().getString(R.string.documentation_home);
    }

    @Override
    protected String getHeaderText() {
        return getResources().getString(R.string.getting_started);
    }

    public static CharSequence getTitle() {
        return "Getting Started";
    }

}
