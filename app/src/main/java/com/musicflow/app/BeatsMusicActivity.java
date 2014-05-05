package com.musicflow.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.freethinking.beats.sdk.login.LoginActivity;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.musicflow.app.pagers.SectionsPagerAdapter;

/**
 * Base activity for all other activities to inherit from.
 */
public abstract class BeatsMusicActivity extends ActionBarActivity {
    protected String[] navTiles;
    protected DrawerLayout drawerLayout;
    protected CharSequence actionBarTitle;
    protected CharSequence drawerTitle;
    protected ListView drawerList;
    protected ActionBarDrawerToggle drawerToggle;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

    /**
     * Checks the phone's network connectivity status.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beats_music);

        actionBarTitle = drawerTitle = getTitle();
        navTiles = getResources().getStringArray(R.array.nav_tiles);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navTiles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.drawable.beatsmusic_full_color,
                        R.string.drawer_open, R.string.drawer_close) {
                    public void onDrawerClosed(View view) {
                        getActionBar().setTitle(drawerTitle);
                        invalidateOptionsMenu();
                    }

                    public void onDrawerOpened(View drawerView) {
                        getActionBar().setTitle(drawerTitle);
                        invalidateOptionsMenu();
                    }
                };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void setTitle(CharSequence title) {
        actionBarTitle = title;
        getActionBar().setTitle(actionBarTitle);
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        Intent i;
        switch (position) {
            case 0:
                i = new Intent(this, ArtistsActivity.class);
                this.startActivity(i);
                break;
            case 1:
                i = new Intent(this, AlbumsActivity.class);
                this.startActivity(i);
                break;
            case 2:
                i = new Intent(this, ActivitiesActivity.class);
                this.startActivity(i);
                break;
            case 3:
                i = new Intent(this, HighlightsActivity.class);
                this.startActivity(i);
                break;
            case 4:
                i = new Intent(this, TracksActivity.class);
                this.startActivity(i);
                break;
            case 5:
                i = new Intent(this, GenresActivity.class);
                this.startActivity(i);
                break;
            case 6:
                i = new Intent(this, PlaylistActivity.class);
                this.startActivity(i);
                break;
            case 7:
                if (NetworkAdapter.loggedIn(this)) {
                    i = new Intent(this, ProfileActivity.class);
                } else {
                    i = new Intent(this, LoginActivity.class);
                }
                this.startActivity(i);
                break;
        }


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
