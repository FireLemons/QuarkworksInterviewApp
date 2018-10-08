package com.example.fly_s_y.applemusicalbumviewer;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONAccessorTestSuite extends JSONFetcher{
    public JSONAccessorTestSuite() {
        super("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor(){

        getValue(new JSONObject(), "test");
    }

    @Test
    public void accessJSON(){

    }
}
