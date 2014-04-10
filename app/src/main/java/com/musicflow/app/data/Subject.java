package com.musicflow.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by whitney on 4/10/14.
 */
public class Subject extends BaseJson {
    @JsonProperty("ref_type")
    protected String refType;
    protected String id;

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof Subject) {
            this.refType = ((Subject) parseJson).refType;
            this.id = ((Subject) parseJson).id;
        } else {
            throw new Exception();
        }
    }

    public String getRefType() {
        return refType;
    }

    public String getId() {
        return id;
    }
}
