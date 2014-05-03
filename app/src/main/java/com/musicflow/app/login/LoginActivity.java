package com.musicflow.app.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.musicflow.app.R;
import com.musicflow.app.data.Authorization;
import com.musicflow.app.data.AuthorizationRequest;
import com.musicflow.app.data.Me;
import com.musicflow.app.mappers.AuthorizationMapper;
import com.musicflow.app.mappers.MeMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class LoginActivity extends Activity {
    protected WebView webView;

    protected Me me;
    protected Authorization authorization;
    protected MeNetworkRequest networkRequest;
    protected AuthNetworkRequest authNetworkRequest;
    HashMap<String, String> authHeaders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        me = new Me();
        authorization = new Authorization();

        authHeaders = new HashMap<String, String>();

        webView = (WebView) findViewById(R.id.activity_login_web_view);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {

            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("musicflow") && url.contains("code")) {
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("code");
                    String state = uri.getQueryParameter("state");
                    String scope = uri.getQueryParameter("scope");

                    String preferencesKey = getString(R.string.user_preferences_key);
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_code", code).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("user_state",state).commit();
                    getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_code_scope",scope).commit();

                    AuthorizationRequest body = new AuthorizationRequest(UrlFactory.clientSecret(), UrlFactory.clientID(), "http://www.musicflow.com", code, "authorization_code", false);

                    authNetworkRequest = new AuthNetworkRequest(body);
                    authNetworkRequest.execute(UrlFactory.obtainToken());
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.loadUrl("https://partner.api.beatsmusic.com/v1/oauth2/authorize?response_type=code&redirect_uri=http%3A%2F%2Fwww.musicflow.com&client_id=" + UrlFactory.clientID());
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

    protected class AuthNetworkRequest extends NetworkAdapter {

        public AuthNetworkRequest(AuthorizationRequest body) {
            super(new AuthorizationMapper(), NetworkAdapter.RequestType.POST, new HashMap<String, String>(), body, authorization);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            String preferencesKey = getString(R.string.user_preferences_key);
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_token", authorization.getResult().getAccessToken()).commit();
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("refresh_token", authorization.getResult().getRefreshToken()).commit();
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putLong("access_expires_at", System.currentTimeMillis() + (1000 * authorization.getResult().getExpiresIn())).commit();

            authHeaders.put("Authorization", "Bearer " + authorization.getResult().getAccessToken());

            networkRequest = new MeNetworkRequest();
            networkRequest.execute(UrlFactory.me());
        }
    }
}
