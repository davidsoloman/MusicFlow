package com.musicflow.app.network;

import android.net.Uri;

import java.util.HashMap;

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
        return BASE_URL + "/api/artists" + "?client_id=" + clientID();
    }

    public static String artistList(CollectionQueryParams params) {

        return BASE_URL + "/api/artists" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String artist(String id) {
        return BASE_URL + "/api/artists/" + id + "?client_id=" + clientID();
    }

    public static String artist(String id, LookupQueryParams params) {

        return BASE_URL + "/api/artists/" + id + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String artistAlbums(String id) {
        return BASE_URL + "/api/artists/" + id + "/albums" + "?client_id=" + clientID();
    }

    public static String artistAlbums(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/artists/" + id + "/albums" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String artistEssentialAlbums(String id) {
        return BASE_URL + "/api/artists/" + id + "/essential_albums" + "?client_id=" + clientID();
    }

    public static String artistEssentialAlbums(String id, CollectionQueryParams params) {
        return BASE_URL + "/api/artists/" + id + "/essential_albums" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String artistTracks(String id) {
        return BASE_URL + "/api/artists/" + id + "/tracks" + "?client_id=" + clientID();
    }

    public static String artistTracks(String id, CollectionQueryParams params) {
        return BASE_URL + "/api/artists/" + id + "/tracks" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String artistPlaylists(String id) {
        return BASE_URL + "/api/artists/" + id + "/playlists" + "?client_id=" + clientID();
    }

    public static String artistPlaylists(String id, CollectionQueryParams params) {
        return BASE_URL + "/api/artists/" + id + "/playlists" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String activitiesList() {
        return BASE_URL + "/api/activities" + "?client_id=" + clientID();
    }

    public static String activitiesList(CollectionQueryParams params) {
        return BASE_URL + "/api/activities" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String activity(String id) {
        return BASE_URL + "/api/activities/" + id + "?client_id=" + clientID();
    }

    public static String activity(String id, LookupQueryParams params) {
        return BASE_URL + "/api/activities/" + id + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String activityEditorialPlaylists(String id) {
        return BASE_URL + "/api/activities/" + id + "/editorial_playlists" + "?client_id=" + clientID();
    }

    public static String activityEditorialPlaylists(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/activities/" + id + "/editorial_playlists" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String albumList() {
        return BASE_URL + "/api/albums" + "?client_id=" + clientID();
    }

    public static String albumList(CollectionQueryParams params) {

        return BASE_URL + "/api/albums" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String album(String id) {
        return BASE_URL + "/api/albums/" + id + "?client_id=" + clientID();
    }

    public static String album(String id, LookupQueryParams params) {

        return BASE_URL + "/api/albums/" + id + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String albumArtists(String id) {
        return BASE_URL + "/api/albums/" + id + "/artists" + "?client_id=" + clientID();
    }

    public static String albumArtists(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/albums/" + id + "/artists" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String albumTracks(String id) {
        return BASE_URL + "/api/albums/" + id + "/tracks" + "?client_id=" + clientID();
    }

    public static String albumTracks(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/albums/" + id + "/tracks" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String albumReviews(String id) {
        return BASE_URL + "/api/albums/" + id + "/reviews" + "?client_id=" + clientID();
    }

    public static String albumReviews(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/albums/" + id + "/reviews" + "?client_id=" + clientID() + '&' + params.toString();
    }

    public static String albumCompanionAlbums(String id) {
        return BASE_URL + "/api/albums/" + id + "/companion_albums" + "?client_id=" + clientID();
    }

    public static String albumCompanionAlbums(String id, CollectionQueryParams params) {

        return BASE_URL + "/api/albums/" + id + "/companion_albums" + "?client_id=" + clientID() + '&' + params.toString();
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
