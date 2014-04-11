package com.musicflow.app.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.musicflow.app.ArtistViewActivity;
import com.musicflow.app.R;

public class AlbumViewActivityTest extends ActivityInstrumentationTestCase2<ArtistViewActivity> {
    protected ImageView artistHeroImage;
    protected TextView artistName;
    protected TextView bio;

    public AlbumViewActivityTest() {
        super(ArtistViewActivity.class);
    }

    public void loadActivityUiElements() {
        artistHeroImage = (ImageView) getActivity().findViewById(R.id.artist_hero_image);
        artistName = (TextView) getActivity().findViewById(R.id.artist_name);
        bio = (TextView) getActivity().findViewById(R.id.artist_description);
    }

    public void testArtistHeroImageVisible() {
        loadActivityUiElements();
        assertEquals(View.VISIBLE, artistHeroImage.getVisibility());
    }

    public void testArtistHeroImageNotNull() {
        loadActivityUiElements();
        assertNotNull(artistHeroImage);
    }

    public void testArtistNameVisible() {
        loadActivityUiElements();
        assertEquals(View.VISIBLE, artistName.getVisibility());
    }

    public void testArtistBioVisible() {
        loadActivityUiElements();
        assertEquals(View.VISIBLE, bio.getVisibility());
    }
}
