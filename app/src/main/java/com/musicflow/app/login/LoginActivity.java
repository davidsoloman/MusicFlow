package com.musicflow.app.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.musicflow.app.R;
import com.musicflow.app.data.Me;
import com.musicflow.app.mappers.MeMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class LoginActivity extends Activity {
    protected WebView webView;

    protected Me me;
    protected MeNetworkRequest networkRequest;
    HashMap<String, String> authHeaders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        me = new Me();
        authHeaders = new HashMap<String, String>();

        webView = (WebView) findViewById(R.id.activity_login_web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {

            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("musicflow") && url.contains("access_token")) {
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("access_token");
                    String tokenType = uri.getQueryParameter("token_type");
                    Long expiresIn = Long.parseLong(uri.getQueryParameter("expires_in"));
                    String state = uri.getQueryParameter("state");
                    String scope = uri.getQueryParameter("scope");

                    String preferencesKey = getString(R.string.user_preferences_key);
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_token",code).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_token_token_type",tokenType).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putLong("access_token_expiration", System.currentTimeMillis() + (expiresIn * 1000)).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("user_state",state).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_token_scope",scope).commit();

                    authHeaders.put("Authorization", "Bearer " + code);

                    networkRequest = new MeNetworkRequest();
                    networkRequest.execute(UrlFactory.me());
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.loadUrl("https://partner.api.beatsmusic.com/v1/oauth2/authorize?response_type=token&redirect_uri=http%3A%2F%2Fwww.musicflow.com&client_id=" + UrlFactory.clientID());
    }

    public void completeSignIn() {
        Toast.makeText(this, getString(R.string.login_welcome), Toast.LENGTH_SHORT).show();
        finish();
    }

    protected class MeNetworkRequest extends NetworkAdapter {

        public MeNetworkRequest() {
            super(new MeMapper(), NetworkAdapter.RequestType.GET, authHeaders, me);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String preferencesKey = getString(R.string.user_preferences_key);
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("user_id", me.getResult().getUserContext()).commit();
            completeSignIn();
        }
    }
}
