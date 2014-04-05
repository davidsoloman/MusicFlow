package com.musicflow.app.data;

public class AlbumWrapper extends BaseJson {
    protected Album data;
    protected String code;

    public AlbumWrapper() {
        data = new Album();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof AlbumWrapper) {
            data = ((AlbumWrapper) parseJson).data;
            code = ((AlbumWrapper) parseJson).code;
        } else {
            throw new Exception();
        }
    }
}
