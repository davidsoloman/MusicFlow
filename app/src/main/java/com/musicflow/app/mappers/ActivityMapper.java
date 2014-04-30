package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.Activities;
import com.musicflow.app.data.Activity;
import com.musicflow.app.data.ActivityWrapper;
import com.musicflow.app.data.BaseJson;

import java.io.IOException;

public class ActivityMapper extends CommonMapper {
    
    @Override
    public BaseJson parseJson(String json) {
        ActivityWrapper activity = new ActivityWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            activity = objMapper.readValue(jsonParser, ActivityWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return activity.getData();
    }
}
