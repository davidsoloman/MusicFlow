package com.musicflow.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class BeatsMusicActivity extends ActionBarActivity {
    protected String[] navTiles;
    protected DrawerLayout drawerLayout;
    protected CharSequence actionBarTitle;
    protected CharSequence drawerTitle;
    protected ListView drawerList;
    protected ActionBarDrawerToggle drawerToggle;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_profile:
                this.startActivity(new Intent(this, ProfileActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /** Swaps fragments in the main content view */
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
                break;
            case 4: 
                break;
            case 5:
                break;
            case 6:
                break;
        }


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}