package com.musicflow.app.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by dsabin on 4/30/14.
 */


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AlbumRecommendationContent.class, name = "album"),
        @JsonSubTypes.Type(value = PlaylistRecommendationContent.class, name = "playlist"),
        @JsonSubTypes.Type(value = TrackRecommendationContent.class, name = "track"),
        @JsonSubTypes.Type(value = ArtistRecommendationContent.class, name = "artist")
})
public class RecommendationContent extends BaseJson{
    protected String type;

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof RecommendationContent) {
            this.type = ((RecommendationContent) parseJson).type;
        } else {
            throw new Exception();
        }
    }

    public String getType() {
        return type;
    }
}
