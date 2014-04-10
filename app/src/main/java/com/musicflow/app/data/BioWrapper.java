package com.musicflow.app.data;

public class BioWrapper extends BaseJson {
    protected Bio data;
    protected String code;

    public BioWrapper() {
        this.data = new Bio();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof BioWrapper) {
            data = ((BioWrapper) parseJson).data;
            code = ((BioWrapper) parseJson).code;
        } throw new Exception();
    }

    public Bio getData() {
        return data;
    }

    public String getCode() {
        return code;
    }
}
