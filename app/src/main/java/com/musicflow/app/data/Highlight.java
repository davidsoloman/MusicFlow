package com.musicflow.app.data;

public class Highlight extends BaseJson {
    protected String type;
    protected RecommendationContent content;

    public Highlight() {
        this.content = new RecommendationContent();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof Highlight) {
            this.type = ((Highlight) parseJson).type;
            this.content = ((Highlight) parseJson).content;
        } else {
            throw new Exception();
        }
    }

    public String getType() {
        return type;
    }

    public RecommendationContent getContent() {
        return content;
    }
}
