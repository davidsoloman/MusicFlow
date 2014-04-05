package com.musicflow.app.data;

import java.util.ArrayList;
import java.util.List;

public class AlbumList extends BaseJson {

    protected String code;
    protected PagingInfo info;
    protected List<Album> data;

    public AlbumList() {
        data = new ArrayList<Album>();
        info = new PagingInfo();
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

    public List<Album> getAlbums() {
        return data;
    }

}
