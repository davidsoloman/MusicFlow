package com.musicflow.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.annotation.JsonRootName;

public class MainActivity extends ActionBarActivity {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
     * fragment in memory. If this becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter sectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager viewPager;
    private String[] navTiles;
    private DrawerLayout drawerLayout;
    private CharSequence actionBarTitle;
    private CharSequence drawerTitle;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBarTitle = drawerTitle = getTitle();
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

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

    // /** Swaps fragments in the main content view */
    // private void selectItem(int position) {
    // // Create a new fragment and specify the planet to show based on position
    // Fragment fragment = new SearchFragment();
    // Bundle args = new Bundle();
    // args.putInt(SearchFragment.ARG_PLANET_NUMBER, position);
    // fragment.setArguments(args);
    //
    // // Insert the fragment by replacing any existing fragment
    // FragmentManager fragmentManager = getFragmentManager();
    // fragmentManager.beginTransaction()
    // .replace(R.id.content_frame, fragment)
    // .commit();
    //
    // // Highlight the selected item, update the title, and close the drawer
    // drawerList.setItemChecked(position, true);
    // setTitle(navTiles[position]);
    // drawerLayout.closeDrawer(drawerList);
    // }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            // selectItem(position);
        }
    }
}
