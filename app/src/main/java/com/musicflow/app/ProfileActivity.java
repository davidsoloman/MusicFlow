package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.freethinking.beats.sdk.data.User;
import com.freethinking.beats.sdk.mappers.UserMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.NetworkParts;
import com.freethinking.beats.sdk.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Responsible for showing the user information about themselves.  Represents the Beats "me"
 * API endpoint.
 */
public class ProfileActivity extends ActionBarActivity {

    protected TextView username;
    protected TextView fullName;
    protected ImageView coverImage;
    protected UserProfileNetworkAdapter networkRequest;
    protected User user;
    protected String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = (TextView) findViewById(R.id.activity_profile_username);
        fullName = (TextView) findViewById(R.id.activity_profile_user_full_name);
        coverImage = (ImageView) findViewById(R.id.activity_profile_user_cover_image);

        user = new User();

        String preferencesKey = getString(R.string.user_preferences_key);
        userId = getSharedPreferences(preferencesKey, MODE_PRIVATE).getString("user_id", "");

        fireOffNetwork();
    }

    private void fireOffNetwork() {
        networkRequest = new UserProfileNetworkAdapter(this);
        networkRequest.execute(UrlFactory.profile(userId));
    }

    private void loadView() {
        username.setText('@'+user.getUsername());
        fullName.setText(user.getFullName());
        Picasso.with(this).load(UrlFactory.imageUrl(userId, UrlFactory.EntityType.USER, UrlFactory.ImageType.COVER, UrlFactory.ImageSize.LARGE)).placeholder(R.drawable.placeholder).fit().centerCrop().into(coverImage);
    }

    protected class UserProfileNetworkAdapter extends NetworkAdapter {
        public UserProfileNetworkAdapter(Context context) {
            super(context, new UserMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), user);
        }

        @Override
        protected Boolean authRequired() {
            return true;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadView();
        }
    }
}
