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
    protected Authorization authorization;

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void fireOffNetwork()
    {
        String preferencesKey = getString(R.string.user_preferences_key);
        String userId = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("user_id", "");
        networkRequest = new UserProfileNetworkAdapter(this);
        networkRequest.execute(UrlFactory.profile(userId));
    }

    private void loadView() {
        username.setText(user.getUsername());
    }

    protected class UserProfileNetworkAdapter extends NetworkAdapter {
        @Override
        protected Boolean authRequired() {
            return true;
        }

        public UserProfileNetworkAdapter(Context context) {
            super(context, new UserMapper(), RequestType.GET, new HashMap<String, String>(), user);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadView();
        }
    }
}
