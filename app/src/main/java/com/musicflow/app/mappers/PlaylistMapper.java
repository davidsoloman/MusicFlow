package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.PlaylistWrapper;

import java.io.IOException;

public class PlaylistMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        PlaylistWrapper playlist = new PlaylistWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            playlist = objMapper.readValue(jsonParser, PlaylistWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playlist.getData();
    }
}
