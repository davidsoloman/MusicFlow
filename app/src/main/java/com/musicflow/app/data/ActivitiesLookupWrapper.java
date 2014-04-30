package com.musicflow.app.data;

public class ActivitiesLookupWrapper extends BaseJson {
    protected ActivitiesLookup data;
    protected String code;

    public ActivitiesLookupWrapper() {
        this.data = new ActivitiesLookup();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof ActivitiesLookupWrapper) {
            data = ((ActivitiesLookupWrapper) parseJson).data;
            code = ((ActivitiesLookupWrapper) parseJson).code;
        } else {
            throw new Exception();
        }
    }

    public ActivitiesLookup getData() {
        return data;
    }

    public String getCode() {
        return code;
    }
}
