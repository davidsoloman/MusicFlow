package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.UserWrapper;

public class UserMapper extends CommonMapper {
    @Override
    public BaseJson parseJson(String json) {
        UserWrapper userWrapper = new UserWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            userWrapper = objMapper.readValue(jsonParser, UserWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userWrapper.getData();
    }
}
