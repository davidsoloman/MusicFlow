package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.AlbumWrapper;
import com.musicflow.app.data.BaseJson;

public class AlbumMapper extends CommonMapper{
    @Override
    public BaseJson parseJson(String json) {
        AlbumWrapper albumWrapper = new AlbumWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            albumWrapper = objMapper.readValue(jsonParser, AlbumWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albumWrapper.getAlbum();
    }
}
