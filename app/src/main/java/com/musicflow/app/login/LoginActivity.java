package com.musicflow.app.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.musicflow.app.R;
import com.musicflow.app.data.Authorization;
import com.musicflow.app.network.UrlFactory;

public class LoginActivity extends Activity {
    protected WebView webView;

    protected Authorization authResponse;
//    protected TokenNetworkRequest networkRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authResponse = new Authorization();

        webView = (WebView) findViewById(R.id.activity_login_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
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

                    getPreferences(MODE_PRIVATE).edit().putString("access_token",code).commit();
                    getPreferences(MODE_PRIVATE).edit().putString("access_token_token_type",tokenType).commit();
                    getPreferences(MODE_PRIVATE).edit().putLong("access_token_expiration", System.currentTimeMillis() + (expiresIn * 1000)).commit();
                    getPreferences(MODE_PRIVATE).edit().putString("user_state",state).commit();
                    getPreferences(MODE_PRIVATE).edit().putString("access_token_scope",scope).commit();

                    completeSignIn();

//                    String clientSecret = UrlFactory.clientSecret();
//                    String clientId = UrlFactory.clientID();
//                    AuthorizationRequest authorizationRequest = new AuthorizationRequest(clientSecret, clientId, code, getString(R.string.authorization_code_param));
//                    networkRequest = new TokenNetworkRequest(authorizationRequest);
//                    networkRequest.execute(UrlFactory.obtainToken());

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

//    protected class TokenNetworkRequest extends NetworkAdapter {
//
//        public TokenNetworkRequest(AuthorizationRequest body) {
//            super(new AuthorizationMapper(), RequestType.POST, new HashMap<String, String>(), body, authResponse);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            getPreferences(MODE_PRIVATE).edit().putString("access_token",authResponse.getResult().getAccessToken()).commit();
//            getPreferences(MODE_PRIVATE).edit().putString("refresh_token",authResponse.getResult().getRefreshToken()).commit();
//            getPreferences(MODE_PRIVATE).edit().putString("access_token_token_type",authResponse.getResult().getTokenType()).commit();
//            getPreferences(MODE_PRIVATE).edit().putLong("access_token_expiration", System.currentTimeMillis() + (authResponse.getResult().getExpiresIn() * 1000)).commit();
//            getPreferences(MODE_PRIVATE).edit().putString("user_state",authResponse.getResult().getState()).commit();
//            getPreferences(MODE_PRIVATE).edit().putString("access_token_scope",authResponse.getResult().getScope()).commit();
//            completeSignIn();
//        }
//    }
}
