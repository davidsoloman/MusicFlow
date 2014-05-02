package com.musicflow.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.musicflow.app.data.User;
import com.musicflow.app.mappers.UserMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

import java.util.HashMap;

public class ProfileActivity extends ActionBarActivity {

    protected TextView username;
    protected UserProfileNetworkAdapter networkRequest;
    protected User user;
    protected HashMap<String, String> authHeaders = new HashMap<String, String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = (TextView) findViewById(R.id.activity_profile_username);
        user = new User();

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
        String accessToken = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("access_token", "");
        authHeaders.put("Authorization", "Bearer " + accessToken);
        String userId = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("user_id", "");

        networkRequest = new UserProfileNetworkAdapter();
        networkRequest.execute(UrlFactory.profile(userId));
    }

    private void loadView() {
        username.setText(user.getUsername());
    }

    protected class UserProfileNetworkAdapter extends NetworkAdapter {
        public UserProfileNetworkAdapter() {
            super(new UserMapper(), RequestType.GET, authHeaders, user);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadView();
        }
    }
}
