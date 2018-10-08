package com.example.fly_s_y.applemusicalbumviewer.JSON;

import com.example.fly_s_y.applemusicalbumviewer.ConnectionHandler;
import com.example.fly_s_y.applemusicalbumviewer.JSON.JSONAccessor;
import com.example.fly_s_y.applemusicalbumviewer.JSON.JSONAccessorType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles fetching JSON from a single domain.
 */
public class JSONFetcher extends ConnectionHandler {
    public JSONFetcher(String domain) {
        super(domain);
    }

    /**
     * @param JSON Either a JSONObject or a JSONArray to be searched
     * @param jsAccessor Javascript that would normally follow the JSON object to access its members
     * @return
     *  The value jsAccessor accesses from the JSON on success
     *  Null on failure
     */
    protected Object getValue(Object JSON, String jsAccessor){
        //No access to the object attempted
        if(jsAccessor.length() == 0){
            return JSON;
        }

        ArrayList<JSONAccessor> accessors = new ArrayList<JSONAccessor>();

        //Input check for jsAccessor
        Pattern dotAccessor = Pattern.compile("(^\\.[_\\$a-zA-Z][_\\$a-zA-Z0-9]*)"),
                bracketAccessor = Pattern.compile("(^\\['[_\\$a-zA-Z][_\\$a-zA-Z0-9]*'\\])"),
                bracketIndexAccessor = Pattern.compile("(^\\[[0-9]+\\])");
        String accessorsExpression = jsAccessor;
        Matcher capturedAccessor;

        while(accessorsExpression.length() > 0){
            switch(accessorsExpression.charAt(0)){
                case '.':
                    capturedAccessor = dotAccessor.matcher(accessorsExpression);

                    if(capturedAccessor.find()){
                        String accessorExpression = capturedAccessor.group(1);

                        accessors.add(new JSONAccessor(JSONAccessorType.Key, accessorExpression.substring(1)));
                        accessorsExpression = accessorsExpression.substring(accessorExpression.length());
                        break;
                    }

                    throw new IllegalArgumentException("Invalid format for input.\nInput should be in the form of accessorsExpression usually following a javascript object variable");
                case '[':
                    if(accessorsExpression.length() > 1){
                        if(accessorsExpression.charAt(1) == '\''){
                            capturedAccessor = bracketAccessor.matcher(accessorsExpression);

                            if(capturedAccessor.find()){
                                String accessorExpression = capturedAccessor.group(1);

                                accessors.add(new JSONAccessor(JSONAccessorType.Key, accessorExpression.substring(2, accessorExpression.length() - 2)));
                                accessorsExpression = accessorsExpression.substring(accessorExpression.length());
                                break;
                            }
                        } else {
                            capturedAccessor = bracketIndexAccessor.matcher(accessorsExpression);

                            if(capturedAccessor.find()){
                                String accessorExpression = capturedAccessor.group(1);

                                accessors.add(new JSONAccessor(JSONAccessorType.Index, accessorExpression.substring(1, accessorExpression.length() - 1)));
                                accessorsExpression = accessorsExpression.substring(accessorExpression.length());
                                break;
                            }
                        }
                    }

                    throw new IllegalArgumentException("Invalid format for input.\nInput should be in the form of accessorsExpression usually following a javascript object variable");
                default:
                    throw new IllegalArgumentException("Invalid format for input.\nInput should be in the form of accessorsExpression usually following a javascript object variable");
            }
        }

        //Search the object using the accessors
        Object value = JSON;

        for(JSONAccessor accessor: accessors){
            if(accessor.getType() == JSONAccessorType.Key){

            } else {

            }
        }

        return value;
    }

    /**
     * @param path The path from domain to reach the JSON
     * @return
     *  Either a JSONObject or a JSONArray on success
     *  Null on failure
     */
    protected Object fetchJSON(String path){
        return null;
    }
}