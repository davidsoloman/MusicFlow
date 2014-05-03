package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.musicflow.app.data.Authorization;
import com.musicflow.app.data.AuthorizationRequest;
import com.musicflow.app.data.User;
import com.musicflow.app.mappers.AuthorizationMapper;
import com.musicflow.app.mappers.UserMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

/**
 * Responsible for showing the user information about themselves.  Represents the Beats "me" 
 * API endpoint.
 */
public class ProfileActivity extends ActionBarActivity {

    protected TextView username;
    protected UserProfileNetworkAdapter networkRequest;
    protected User user;
    protected HashMap<String, String> authHeaders = new HashMap<String, String>();
    protected Authorization authorization;
    protected RefreshAccessToken refreshAccessToken;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = (TextView) findViewById(R.id.activity_profile_username);
        user = new User();
        authorization = new Authorization();

        fireOffNetwork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds data to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void fireOffNetwork()
    {
        String preferencesKey = getString(R.string.user_preferences_key);
        Long accessExpires = getSharedPreferences(preferencesKey, MODE_PRIVATE).getLong("access_expires_at", System.currentTimeMillis());

        if (accessExpires < System.currentTimeMillis()) {
            String code = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("refresh_token", "");
            AuthorizationRequest body = new AuthorizationRequest(UrlFactory.clientSecret(), UrlFactory.clientID(), "http://www.musicflow.com", code, "refresh_token", true);

            refreshAccessToken = new RefreshAccessToken(this, body);
            refreshAccessToken.execute(UrlFactory.obtainToken());
        } else {
            String accessCode = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("access_token", "");
            authHeaders.put("Authorization", "Bearer " + accessCode);
            String userId = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("user_id", "");

            networkRequest = new UserProfileNetworkAdapter(this);
            networkRequest.execute(UrlFactory.profile(userId));
        }
    }

    private void loadView() {
        username.setText(user.getUsername());
    }

    protected class UserProfileNetworkAdapter extends NetworkAdapter {
        public UserProfileNetworkAdapter(Context context) {
            super(context, new UserMapper(), RequestType.GET, authHeaders, user);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadView();
        }
    }

    protected class RefreshAccessToken extends NetworkAdapter {

        public RefreshAccessToken(Context context, AuthorizationRequest body) {
            super(context, new AuthorizationMapper(), NetworkAdapter.RequestType.POST, new HashMap<String, String>(), body, authorization);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            String preferencesKey = getString(R.string.user_preferences_key);
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("access_token", authorization.getResult().getAccessToken()).commit();
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putString("refresh_token", authorization.getResult().getRefreshToken()).commit();
            getSharedPreferences(preferencesKey, MODE_PRIVATE).edit().putLong("access_expires_at", System.currentTimeMillis() + (1000 * authorization.getResult().getExpiresIn())).commit();

            authHeaders.put("Authorization", "Bearer " + authorization.getResult().getAccessToken());

            String accessCode = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("access_token", "");
            authHeaders.put("Authorization", "Bearer " + accessCode);
            String userId = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("user_id", "");

            networkRequest = new UserProfileNetworkAdapter(context);
            networkRequest.execute(UrlFactory.profile(userId));
        }
    }
}
