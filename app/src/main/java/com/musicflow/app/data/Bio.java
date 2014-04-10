package com.musicflow.app.data;


public class Bio extends BaseJson{
    protected String type;
    protected String content;
    protected String headline;
    protected String length;
    protected Subject subject;
    protected PagingInfo info;

    public Bio() {
        subject = new Subject();
        info = new PagingInfo();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof Bio) {
            this.type = ((Bio) parseJson).type;
            this.content = ((Bio) parseJson).content;
            this.headline = ((Bio) parseJson).headline;
            this.length = ((Bio) parseJson).length;
        } else {
            throw new Exception();
        }
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
