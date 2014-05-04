package com.musicflow.app.mappers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.data.ReviewWrapper;

public class ReviewMapper extends CommonMapper {

    @Override
    public BaseJson parseJson(String json) {
        ReviewWrapper reviewWrapper = new ReviewWrapper();

        try {
            jsonParser = jsonFactory.createParser(json);
            reviewWrapper = objMapper.readValue(jsonParser, ReviewWrapper.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reviewWrapper.getData();
    }
}
