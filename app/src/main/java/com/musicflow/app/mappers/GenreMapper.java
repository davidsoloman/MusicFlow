package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.GenreWrapper;

public class GenreMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        GenreWrapper genre = new GenreWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            genre = objMapper.readValue(jsonParser, GenreWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genre.getData();
    }
}
