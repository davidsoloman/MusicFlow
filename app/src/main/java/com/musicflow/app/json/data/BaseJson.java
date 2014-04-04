package com.musicflow.app.json.data;

import java.io.Serializable;

public abstract class BaseJson implements Serializable {
    public abstract void fillIn(BaseJson parseJson) throws Exception;
}
