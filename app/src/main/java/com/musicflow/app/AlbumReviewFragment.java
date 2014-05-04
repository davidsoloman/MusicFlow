package com.musicflow.app;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.musicflow.app.data.Review;
import com.musicflow.app.mappers.ReviewMapper;
import com.musicflow.app.network.NetworkAdapter;
import com.musicflow.app.network.UrlFactory;

/**
 * Displays a the review of album.
 */
public class AlbumReviewFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected WebView albumReview;
    protected Review review;
    protected ReviewNetworkAdapter networkRequest;

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

        networkRequest = new ReviewNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.albumReview(albumId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadReviewData() {
        albumReview.loadData(review.getContent(), "text/html", "utf-8");
    }

    private class ReviewNetworkAdapter extends NetworkAdapter {

        public ReviewNetworkAdapter(Context context) {
            super(context, new ReviewMapper(), RequestType.GET, new HashMap<String, String>(),
                    review);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadReviewData();
        }
    }
}
