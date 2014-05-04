package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.Genres;

/**
 * Maps the list of genres.
 */
public class GenresMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        Genres genres = new Genres();

        try {
            jsonParser = jsonFactory.createParser(json);
            genres = objMapper.readValue(jsonParser, Genres.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genres;
    }
}
