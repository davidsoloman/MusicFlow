package com.musicflow.app.data;

import java.util.ArrayList;
import java.util.List;

public class AlbumList extends BaseJson {

    protected String code;
    protected PagingInfo info;
    protected List<Album> data;

    public AlbumList() {
        this.data = new ArrayList<Album>();
        this.info = new PagingInfo();
    }

    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof AlbumList) {
            List<Album> temp = ((AlbumList) parseJson).data;
            this.data.addAll(temp);
            this.info = ((AlbumList) parseJson).info;
            this.code = ((AlbumList) parseJson).code;
        } else {
            throw new Exception();
        }
    }

    public String getCode() {
        return code;
    }

    public PagingInfo getInfo() {
        return info;
    }

    public List<Album> getData() {
        return data;
    }

    public List<Album> getAlbums() {
        return data;
    }

    // /api/albums/:album_id/images/default
    public List<String> getCoverArt() {
        List<String> coverArtUrls = new ArrayList<String>();

        for (Album album : data) {
            coverArtUrls.add("https://partner.api.beatsmusic.com/v1/api/albums/" + album.id + "/images/default?client_id=frksnm8edw2t8ddebhkqkjwk&size=medium");
        }

        return coverArtUrls;
    }
}
