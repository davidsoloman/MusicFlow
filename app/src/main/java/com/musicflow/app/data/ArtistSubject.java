package com.musicflow.app.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whitney on 4/10/14.
 */
public class ArtistSubject extends BaseJson {
    protected List<Subject> similars;

    public ArtistSubject() {
        this.similars = new ArrayList<Subject>();
    }

    public List<Subject> getSimilars() {
        return similars;
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof ArtistSubject) {
            List<Subject> temp = ((ArtistSubject) parseJson).similars;
            this.similars.addAll(temp);
        } else {
            throw new Exception();
        }
    }
}
