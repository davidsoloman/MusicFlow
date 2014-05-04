package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.TrackWrapper;

import java.io.IOException;

public class TrackMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        TrackWrapper trackWrapper = new TrackWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            trackWrapper = objMapper.readValue(jsonParser, TrackWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trackWrapper.getData();
    }
}
