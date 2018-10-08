package com.example.fly_s_y.applemusicalbumviewer;

import com.example.fly_s_y.applemusicalbumviewer.JSON.JSONFetcher;

import org.json.JSONObject;
import org.junit.Test;

public class JSONAccessorTestSuite extends JSONFetcher {
    public JSONAccessorTestSuite() {
        super("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoSyntax(){
        getValue(new JSONObject(), "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierDot(){
        getValue(new JSONObject(), ".");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierBracket(){
        getValue(new JSONObject(), "[]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierBracket2(){
        getValue(new JSONObject(), "['']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets(){
        getValue(new JSONObject(), "[0");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets2(){
        getValue(new JSONObject(), "0]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets3(){
        getValue(new JSONObject(), "['test'");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets4(){
        getValue(new JSONObject(), "'test']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedSingleQuotes(){
        getValue(new JSONObject(), "['test]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedSingleQuotes2(){
        getValue(new JSONObject(), "[test']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_PartiallyFormedExpression(){
        getValue(new JSONObject(), ".valid['invalid");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_PartiallyFormedExpression2(){
        getValue(new JSONObject(), "[12].");
    }

    @Test
    public void accessJSON(){

    }
}
