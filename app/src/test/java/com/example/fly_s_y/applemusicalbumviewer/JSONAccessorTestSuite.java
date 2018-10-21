package com.example.fly_s_y.applemusicalbumviewer;

import com.example.fly_s_y.Request.RequestFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class JSONAccessorTestSuite extends RequestFetcher {
    public JSONAccessorTestSuite() {
        super("", "", new ErrorDisplay());
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoSyntax() throws JSONException{
        getValue(new JSONObject(), "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierDot() throws JSONException{
        getValue(new JSONObject(), ".");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierBracket() throws JSONException{
        getValue(new JSONObject(), "[]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_NoIdentifierBracket2() throws JSONException{
        getValue(new JSONObject(), "['']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets() throws JSONException{
        getValue(new JSONObject(), "[0");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets2() throws JSONException{
        getValue(new JSONObject(), "0]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets3() throws JSONException{
        getValue(new JSONObject(), "['test'");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedBrackets4() throws JSONException{
        getValue(new JSONObject(), "'test']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedSingleQuotes() throws JSONException{
        getValue(new JSONObject(), "['test]");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_MismatchedSingleQuotes2() throws JSONException{
        getValue(new JSONObject(), "[test']");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_PartiallyFormedExpression() throws JSONException{
        getValue(new JSONObject(), ".valid['invalid");
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchInvalidJSONAccessor_PartiallyFormedExpression2() throws JSONException{
        getValue(new JSONObject(), "[12].");
    }

    @Test (expected = JSONException.class)
    public void accessJSON_BadAccessor() throws JSONException {
        JSONObject object;

        object = new JSONObject(
        "{" +
            "str:\"String\"," +
            "int:12" +
        "}");

        getValue(object, ".nonExistant");
    }

    @Test
    public void accessJSON_NoAccessors(){
        JSONObject object;

        try{
            object = new JSONObject(
            "{" +
                "str:\"String\"," +
                "int:12" +
            "}");

            assertEquals(object, getValue(object, ""));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_dotAccessor(){
        JSONObject object;

        try{
            object = new JSONObject(
            "{" +
                "str:\"String\"," +
                "int:12" +
            "}");

            assertTrue(((JSONObject)object).get("str").equals(getValue(object, ".str")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_bracketAccessor(){
        JSONObject object;

        try{
            object = new JSONObject(
            "{" +
                "str:\"String\"," +
                "int:12" +
            "}");

            assertTrue(((JSONObject)object).get("str").equals(getValue(object, "['str']")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_bracketAccessor2(){
        JSONArray object;

        try{
            object = new JSONArray(
            "[" +
                "{a: 'test'}," +
                "{b: 'test'}" +
            "]");

            assertTrue(((JSONArray)object).get(0).equals(getValue(object, "['0']")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_bracketAccessor3(){
        JSONArray object;

        try{
            object = new JSONArray(
                    "[" +
                            "{a: 'test'}," +
                            "{b: 'test'}" +
                            "]");

            assertTrue(((JSONArray)object).get(0).equals(getValue(object, "[\"0\"]")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_bracketIndexAccessor(){
        JSONArray object;

        try{
            object = new JSONArray(
            "[" +
                "{a: 'test'}," +
                "{b: 'test'}" +
            "]");

            assertTrue(((JSONArray)object).get(0).equals(getValue(object, "[0]")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_mixedAccessors(){
        JSONArray object;

        try{
            object = new JSONArray(
            "[" +
                "{a: 'test'}," +
                "{b: 'test'}" +
            "]");

            assertTrue(((JSONObject)((JSONArray)object).get(1)).get("b").equals(getValue(object, "[1].b")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void accessJSON_mixedAccessors2(){
        JSONObject object;

        try{
            object = new JSONObject(
            "{" +
                "depth0:[" +
                    "{a: 'test'}," +
                    "{b: 'test'}" +
                "]" +
            "}");

            assertTrue(((JSONObject)((JSONArray)((JSONObject)object).get("depth0")).get(0)).get("a").equals(getValue(object, ".depth0['0'][\"a\"]")));
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }
}
