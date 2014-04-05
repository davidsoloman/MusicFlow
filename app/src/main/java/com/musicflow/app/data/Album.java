package com.musicflow.app.data;

public class Album extends BaseJson {

    protected String type;
    protected String id;
    protected String title;
    protected Integer duration;
    protected Boolean parentalAdvisory;
    protected String releaseDate;
    protected String releaseFormat;
    protected Integer rating;
    protected Integer popularity;
    protected Boolean streamable;
    protected String artistDisplayName;
    protected Boolean canonical;
    protected Integer totalCompanionAlbums;
    protected Integer totalTracks;
    protected Boolean essential;

    protected AlbumReferenceLinks refs;

    public Album() {
        refs = new AlbumReferenceLinks();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof Album) {
            this.type = ((Album) parseJson).type;
            this.id = ((Album) parseJson).id;
            this.title = ((Album) parseJson).title;
            this.duration = ((Album) parseJson).duration;
            this.parentalAdvisory = ((Album) parseJson).parentalAdvisory;
            this.releaseDate = ((Album) parseJson).releaseDate;
            this.releaseFormat = ((Album) parseJson).releaseFormat;
            this.rating = ((Album) parseJson).rating;
            this.popularity = ((Album) parseJson).popularity;
            this.streamable = ((Album) parseJson).streamable;
            this.artistDisplayName = ((Album) parseJson).artistDisplayName;
            this.refs = ((Album) parseJson).refs;
            this.canonical = ((Album) parseJson).canonical;
            this.totalCompanionAlbums = ((Album) parseJson).totalCompanionAlbums;
            this.totalTracks = ((Album) parseJson).totalTracks;
            this.essential = ((Album) parseJson).essential;
        } else {
            throw new Exception();
        }
    }
}
