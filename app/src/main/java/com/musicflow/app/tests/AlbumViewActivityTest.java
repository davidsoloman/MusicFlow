package com.musicflow.app.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

import com.musicflow.app.AlbumViewActivity;
import com.musicflow.app.R;

public class AlbumViewActivityTest extends ActivityInstrumentationTestCase2<AlbumViewActivity> {
    public AlbumViewActivityTest() {
        super(AlbumViewActivity.class);
    }

    public void testArtistHeroImageVisible() {
        AlbumViewActivity activity;
        activity = (AlbumViewActivity) getActivity();

        ImageView artistHeroImage;
        artistHeroImage = (ImageView) getActivity().findViewById(R.id.artist_hero_image);
        assertNotNull(artistHeroImage);
    }

}
