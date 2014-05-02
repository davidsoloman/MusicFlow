package com.musicflow.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.musicflow.app.adapters.TracksAdapter;
import com.musicflow.app.data.Review;
import com.musicflow.app.data.Tracks;
import com.musicflow.app.mappers.ReviewMapper;
import com.musicflow.app.mappers.TracksMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Displays a the review of album.
 */
public class AlbumReviewFragment extends BeatsMusicFragment {
    protected WebView albumReview;
    protected Review review;
    protected ReviewNetworkAdapter networkRequest;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AlbumReviewFragment newInstance(int sectionNumber) {
        AlbumReviewFragment fragment = new AlbumReviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        String albumId = getActivity().getIntent().getStringExtra("AlbumId");

        review = new Review();

        View rootView = inflater.inflate(R.layout.fragment_album_review, container, false);
        albumReview = (WebView) rootView.findViewById(R.id.album_review);

        networkRequest = new ReviewNetworkAdapter();
        networkRequest.execute(UrlFactory.albumReview(albumId));

        innerFrame.addView(rootView);
        return innerFrame;

    }

    private void loadReviewData() {
        albumReview.loadData(review.getContent(), "text/html", "utf-8");
    }

    private class ReviewNetworkAdapter extends NetworkAdapter {

        public ReviewNetworkAdapter() {
            super(new ReviewMapper(), RequestType.GET, new HashMap<String, String>(), review);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadReviewData();
        }
    }

}
