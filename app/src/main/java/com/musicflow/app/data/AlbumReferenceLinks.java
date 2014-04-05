package com.musicflow.app.data;

import java.util.ArrayList;
import java.util.List;

public class AlbumReferenceLinks extends BaseJson{
    protected List<ReferenceLink> artists;
    protected ReferenceLink label;
    protected List<ReferenceLink> tracks;

    public AlbumReferenceLinks() {
        artists = new ArrayList<ReferenceLink>();
        label = new ReferenceLink();
        tracks = new ArrayList<ReferenceLink>();
    }

    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof AlbumReferenceLinks) {
            List<ReferenceLink> temp = ((AlbumReferenceLinks) parseJson).artists;
            this.artists.addAll(temp);
            this.label = ((AlbumReferenceLinks) parseJson).label;
            temp = ((AlbumReferenceLinks) parseJson).tracks;
            this.tracks.addAll(temp);
        } else {
            throw new Exception();
        }
    }
}
