package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.AlbumList;
import com.musicflow.app.data.AlbumWrapper;
import com.musicflow.app.data.BaseJson;

import java.io.IOException;

public class AlbumListMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        AlbumList albumList = new AlbumList();

        try {
            jsonParser = jsonFactory.createParser(json);
            albumList = objMapper.readValue(jsonParser, AlbumList.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albumList;
    }
}
