package com.musicflow.app.network;

import android.net.Uri;

import java.util.HashMap;

/**
 * Created by Dan on 4/27/2014.
 */
public class UrlFactory {
    public static final String BASE_URL = "https://partner.api.beatsmusic.com/v1";

    public final int OFFSET_DEFAULT = 0;
    public final int LIMIT_DEFAULT = 20;
    public final String[] FIELDS_DEFAULT = new String[0];
    public final String[] REFS_DEFAULT = new String[0];
    public final String ORDER_BY_DEFAULT = "popularity desc";
    public final HashMap<String, Boolean> STREAMABILITY_FILTERS_DEFAULT = new HashMap<String, Boolean>();
    public final HashMap<String, String> FILTERS_DEFAULT = new HashMap<String, String>();
    public final String[] IDS_DEFAULT = new String[0];

    @SuppressWarnings("SpellCheckingInspection")
    private static String clientID() {
        return "frksnm8edw2t8ddebhkqkjwk";
    }

    public static String artistList() {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artistList(CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String artist(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artist(String id, LookupQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String artistAlbums(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("albums").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artistAlbums(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("albums").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String artistEssentialAlbums(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("essential_albums").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artistEssentialAlbums(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("essential_albums").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String artistTracks(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("tracks").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artistTracks(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("tracks").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String artistPlaylists(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("playlists").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String artistPlaylists(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/artists").append(id).append("playlists").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String activitiesList() {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String activitiesList(CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String activity(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append(id).append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String activity(String id, LookupQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append(id).append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String activityEditorialPlaylists(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append(id).append("editorial_playlists").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String activityEditorialPlaylists(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/activities").append(id).append("editorial_playlists").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String albumList() {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String albumList(CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String album(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String album(String id, LookupQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String albumArtists(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("artists").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String albumArtists(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("artists").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String albumTracks(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("tracks").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String albumTracks(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("tracks").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String albumReviews(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("reviews").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String albumReviews(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("reviews").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }

    public static String albumCompanionAlbums(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("companion_albums").append("?client_id=").append(clientID());
        return sb.toString();
    }

    public static String albumCompanionAlbums(String id, CollectionQueryParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append("/api/albums").append(id).append("companion_albums").append("?client_id=").append(clientID()).append('&');
        sb.append(params.toString());

        return sb.toString();
    }


    public class LookupQueryParams {
        protected String[] fields;
        protected String[] refs;

        public LookupQueryParams() {
            this(new String[0], new String[0]);
        }

        public LookupQueryParams(String[] fields, String[] refs) {
            this.fields = fields;
            this.refs = refs;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (fields.length > 0) {
                for (String field : fields) {
                    sb.append("fields=").append(field).append('&');
                }
            }

            if (refs.length > 0) {
                for (String ref : refs) {
                    sb.append("refs=").append(ref).append('&');
                }
            }

            return sb.toString();
        }
    }

    public class CollectionQueryParams {

        protected int offset;
        protected int limit;
        protected String[] fields;
        protected String[] refs;
        protected String orderBy;
        protected HashMap<String, Boolean> streamabilityFilters;
        protected HashMap<String, String> filters;
        protected String[] ids;

        public CollectionQueryParams() {
            this(OFFSET_DEFAULT, LIMIT_DEFAULT, FIELDS_DEFAULT, REFS_DEFAULT, ORDER_BY_DEFAULT, STREAMABILITY_FILTERS_DEFAULT, FILTERS_DEFAULT, IDS_DEFAULT);
        }

        public CollectionQueryParams(int offset, int limit, String[] fields, String[] refs, String orderBy, HashMap<String, Boolean> streamabilityFilters, HashMap<String, String> filters, String[] ids) {
            this.offset = offset;
            this.limit = limit;
            this.fields = fields;
            this.refs = refs;
            this.orderBy = orderBy;
            this.streamabilityFilters = streamabilityFilters;
            this.filters = filters;
            this.ids = ids;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (ids.length == 0) {
                sb.append("offset=").append(offset).append('&');
                sb.append("limit=").append(limit).append('&');

                if (fields.length > 0) {
                    for (String field : fields) {
                        sb.append("fields=").append(field).append('&');
                    }
                }

                if (refs.length > 0) {
                    for (String ref : refs) {
                        sb.append("refs=").append(ref).append('&');
                    }
                }

                sb.append("order_by=").append(Uri.encode(orderBy)).append('&');

                if (!streamabilityFilters.isEmpty()) {
                    for (String key : streamabilityFilters.keySet()) {
                        sb.append("filters=").append(key).append('=').append(streamabilityFilters.get(key)).append('&');
                    }
                }

                if (!filters.isEmpty()) {
                    for (String key : filters.keySet()) {
                        sb.append("filters=").append(key).append('=').append(filters.get(key)).append('&');
                    }
                }
            } else {
                for (String id : ids) {
                    sb.append("ids=").append(id).append('&');
                }
            }

            return sb.toString();
        }

//        private String arrayToCommaDelimitString(String[] array) {
//            StringBuilder sb = new StringBuilder();
//
//            for(int i = 0; i < array.length; ++i) {
//                sb.append(array[i]);
//                if (i != array.length -1) {
//                    sb.append(",");
//                }
//            }
//
//            return sb.toString();
//        }
    }
}
