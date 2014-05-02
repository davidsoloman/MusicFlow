package com.musicflow.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationRequest extends BaseJson {

    @JsonProperty("client_secret")
    protected String clientSecret;
    @JsonProperty("client_id")
    protected String clientId;
    @JsonProperty("redirect_uri")
    protected String redirectUri;
    protected String code;
    @JsonProperty("grant_type")
    protected String grantType;


    public AuthorizationRequest(String clientSecret, String clientId, String code, String grantType) {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
        this.code = code;
        this.grantType = grantType;
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {

    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
