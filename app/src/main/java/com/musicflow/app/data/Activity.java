package com.musicflow.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity extends BaseJson {
    
    protected String type; 
    protected String name; 
    protected String id;
    @JsonProperty("created_at")
    protected String createdAt;
    @JsonProperty("total_editorial_playlists")
    protected Integer totalEditorialPlaylists;
    
    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof Activity) {
            this.type = ((Activity) parseJson).type;
            this.name = ((Activity) parseJson).name;
            this.id = ((Activity) parseJson).id;
            this.createdAt = ((Activity) parseJson).createdAt;
            this.totalEditorialPlaylists = ((Activity) parseJson).totalEditorialPlaylists;
        } else {
            throw new Exception();
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getTotalEditorialPlaylists() {
        return totalEditorialPlaylists;
    }
}
