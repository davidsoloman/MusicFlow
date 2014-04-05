package com.musicflow.app.data;

public class PagingInfo extends BaseJson {

    protected Integer offset;
    protected Integer count;
    protected Integer total;


    @Override
    public void fillIn(BaseJson parseJson) throws Exception {
        if (parseJson instanceof PagingInfo) {
            this.offset = ((PagingInfo) parseJson).offset;
            this.count = ((PagingInfo) parseJson).count;
            this.total = ((PagingInfo) parseJson).total;
        }
    }
}
