package com.musicflow.app.json.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.musicflow.app.json.data.OAuthResponse;
import java.io.IOException;

public class OAuthMapper extends CommonMapper {
    @Override
    public OAuthResponse parseJson(String json) {
        OAuthResponse authResponse = new OAuthResponse();

        try {
            jsonParser = jsonFactory.createParser(json);
            authResponse = objMapper.readValue(jsonParser, OAuthResponse.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authResponse;
    }
}
