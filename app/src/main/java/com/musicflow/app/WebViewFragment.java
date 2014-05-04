package com.musicflow.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public abstract class WebViewFragment extends BeatsMusicFragment {
    private WebView webView;
    private TextView header;
    
    protected abstract String getUrl();
    protected abstract String getHeaderText();
    
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_webview, container, false);

        initializeHeaderText(rootView);
        initializeWebView(rootView);
        
        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void initializeWebView(View rootView) {
        webView = (WebView) rootView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {

            }
        });
        webView.loadUrl(getUrl());
    }

    private void initializeHeaderText(View rootView) {
//        header = (TextView) rootView.findViewById(R.id.headerText);
//        header.setText(getHeaderText());
    }
}
