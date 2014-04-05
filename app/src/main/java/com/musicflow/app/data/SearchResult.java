package com.musicflow.app.data;

public class SearchResult extends BaseJson{

    protected String type;
    protected String resultType;
    protected String id;
    protected String display;
    protected String detail;
    protected ReferenceLink related;

    public SearchResult() {
        related = new ReferenceLink();
    }

    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof SearchResult) {
            this.related = ((SearchResult) parseJson).related;
            this.type = ((SearchResult) parseJson).type;
            this.resultType = ((SearchResult) parseJson).resultType;
            this.id = ((SearchResult) parseJson).id;
            this.display = ((SearchResult) parseJson).display;
            this.detail = ((SearchResult) parseJson).detail;
        } else {
            throw new Exception();
        }
    }
}
