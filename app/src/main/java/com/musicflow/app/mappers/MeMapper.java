package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.Me;

public class MeMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        Me me = new Me();

        try {
            jsonParser = jsonFactory.createParser(json);
            me = objMapper.readValue(jsonParser, Me.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return me;
    }
}
