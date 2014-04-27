package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.AlbumList;
import com.musicflow.app.data.Artists;
import com.musicflow.app.data.BaseJson;

public class ArtistsMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        Artists artists = new Artists();

        try {
            jsonParser = jsonFactory.createParser(json);
            artists = objMapper.readValue(jsonParser, Artists.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return artists;
    }
}
