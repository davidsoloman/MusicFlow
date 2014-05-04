package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.Tracks;

public class TracksMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        Tracks tracks = new Tracks();

        try {
            jsonParser = jsonFactory.createParser(json);
            tracks = objMapper.readValue(jsonParser, Tracks.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tracks;
    }
}
