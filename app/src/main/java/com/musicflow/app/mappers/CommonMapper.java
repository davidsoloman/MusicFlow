package com.musicflow.app.mappers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicflow.app.data.BaseJson;

public abstract class CommonMapper {
    protected ObjectMapper objMapper;
    protected JsonFactory jsonFactory;
    protected JsonParser jsonParser;

    public CommonMapper() {
        objMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
        jsonParser = null;
    }

    public abstract BaseJson parseJson(String json);
}
