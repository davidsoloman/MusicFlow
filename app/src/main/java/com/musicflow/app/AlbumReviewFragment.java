package com.musicflow.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.freethinking.beats.sdk.data.Review;
import com.freethinking.beats.sdk.mappers.ReviewMapper;
import com.freethinking.beats.sdk.network.NetworkAdapter;
import com.freethinking.beats.sdk.network.NetworkParts;
import com.freethinking.beats.sdk.network.UrlFactory;

import java.util.HashMap;

/**
 * Displays a the review of album.
 */
public class AlbumReviewFragment extends BeatsMusicFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    protected WebView albumReview;
    protected FrameLayout noResultsFrame;
    protected FrameLayout resultsFrame;
    protected TextView albumTitle;
    protected Review review;
    protected TextView reviewBy;
    protected String albumName;
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
        albumName = getActivity().getIntent().getStringExtra("AlbumTitle");

        review = new Review();

        View rootView = inflater.inflate(R.layout.fragment_album_review, container, false);

        albumReview = (WebView) rootView.findViewById(R.id.album_review);
        albumTitle = (TextView) rootView.findViewById(R.id.review_title);
        reviewBy = (TextView) rootView.findViewById(R.id.review_by);
        resultsFrame = (FrameLayout) rootView.findViewById(R.id.web_view_result_frame);
        noResultsFrame = (FrameLayout) rootView.findViewById(R.id.no_reviews_frame);

        networkRequest = new ReviewNetworkAdapter(getActivity());
        networkRequest.execute(UrlFactory.albumReview(getActivity(), albumId));

        innerFrame.addView(rootView);
        return innerFrame;
    }

    private void loadReviewData() {
        if (review.getContent() != null) {
            albumReview.loadData(review.getContent(), "text/html", "utf-8");
            albumTitle.setText(albumName);
            if (review.getAuthor() != null) {
                reviewBy.setText(review.getAuthor());
            }
        } else {
            noResultsFrame.setVisibility(View.VISIBLE);
            resultsFrame.setVisibility(View.GONE);

        }
    }

    private class ReviewNetworkAdapter extends NetworkAdapter {

        public ReviewNetworkAdapter(Context context) {
            super(context, new ReviewMapper(), NetworkParts.RequestType.GET, new HashMap<String, String>(), review);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadReviewData();
        }
    }
}
