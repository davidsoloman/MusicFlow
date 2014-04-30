package com.musicflow.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivitiesLookup extends BaseJson {
    protected String type;
    protected String name;
    protected String id;
    @JsonProperty("num_id")
    protected int numId;
    @JsonProperty("created_at")
    protected int createdAt;

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof ActivitiesLookup) {
            this.type = ((ActivitiesLookup) parseJson).type;
            this.name = ((ActivitiesLookup) parseJson).name;
            this.id = ((ActivitiesLookup) parseJson).id;
            this.numId = ((ActivitiesLookup) parseJson).numId;
            this.createdAt = ((ActivitiesLookup) parseJson).createdAt;
        } else {
            throw new Exception();
        }
    }
}