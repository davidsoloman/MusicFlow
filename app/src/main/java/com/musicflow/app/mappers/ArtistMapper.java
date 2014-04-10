package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.ArtistWrapper;
import com.musicflow.app.data.BaseJson;

public class ArtistMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        ArtistWrapper artistWrapper = new ArtistWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            artistWrapper = objMapper.readValue(jsonParser, ArtistWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artistWrapper.getArtist();
    }
}