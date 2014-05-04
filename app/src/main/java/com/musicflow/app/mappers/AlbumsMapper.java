package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.Albums;
import com.musicflow.app.data.BaseJson;

import java.io.IOException;

public class AlbumsMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        Albums albums = new Albums();

        try {
            jsonParser = jsonFactory.createParser(json);
            albums = objMapper.readValue(jsonParser, Albums.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albums;
    }
}
