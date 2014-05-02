package com.musicflow.app.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.musicflow.app.R;
import com.musicflow.app.network.UrlFactory;

public class LoginActivity extends Activity {
    protected WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = (WebView) findViewById(R.id.activity_login_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {

            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("musicflow") && url.contains("access_token")) {
                    Uri uri = Uri.parse(url);
                    String token = uri.getQueryParameter("access_token");
                    getPreferences(MODE_PRIVATE).edit().putString("access_token",token).commit();
                    displaySuccess();
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.loadUrl("https://partner.api.beatsmusic.com/v1/oauth2/authorize?response_type=token&redirect_uri=http%3A%2F%2Fwww.musicflow.com&client_id=" + UrlFactory.clientID());
    }

    public void displaySuccess() {
        Toast.makeText(this, getString(R.string.login_welcome), Toast.LENGTH_SHORT).show();
    }
}
