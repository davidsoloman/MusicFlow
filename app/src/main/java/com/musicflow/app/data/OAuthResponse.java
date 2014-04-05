package com.musicflow.app.data;

public class OAuthResponse extends BaseJson {

    /* SAMPLE RESPONSE
        {
            "jsonrpc": "2.0",
            "result": {
                AuthResult.java
            },
            "id": 1,
            "code": "OK"
        }
     */

    String jsonRPC;
    Integer id;
    String code;
    AuthResult authResult;

    public OAuthResponse() {
        authResult = new AuthResult();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof OAuthResponse) {
            this.id =  ((OAuthResponse) parseJson).id;
            this.code = ((OAuthResponse) parseJson).code;
            this.authResult = ((OAuthResponse) parseJson).authResult;
            this.jsonRPC = ((OAuthResponse) parseJson).jsonRPC;
        } else {
            throw new Exception();
        }
    }

    private class AuthResult extends BaseJson {

        /*
            {
                "return_type": "json",
                "access_token": "[ NEW_ACCESS_TOKEN ]",
                "token_type": "bearer",
                "expires_in": 3600,
                "refresh_token": "[ NEW_REFRESH_TOKEN ]",
                "scope": "uUmMa",
                "state": null,
                "uri": null,
                "extended": null
            }
         */

        String returnType;
        String accessToken;
        String tokenType;
        Integer expiresIn;
        String refreshToken;
        String scope;
        String state;
        String uri;
        String extended;

        @Override
        public void fillIn(BaseJson parseJson) throws Exception {
            if (parseJson instanceof AuthResult) {
                this.returnType =  ((AuthResult) parseJson).returnType;
                this.accessToken = ((AuthResult) parseJson).accessToken;
                this.tokenType = ((AuthResult) parseJson).tokenType;
                this.expiresIn =  ((AuthResult) parseJson).expiresIn;
                this.refreshToken = ((AuthResult) parseJson).refreshToken;
                this.scope = ((AuthResult) parseJson).scope;
                this.state =  ((AuthResult) parseJson).state;
                this.uri = ((AuthResult) parseJson).uri;
                this.extended = ((AuthResult) parseJson).extended;
            } else {
                throw new Exception();
            }
        }
    }
}
