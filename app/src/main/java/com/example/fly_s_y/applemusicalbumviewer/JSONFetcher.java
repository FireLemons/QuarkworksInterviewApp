package com.example.fly_s_y.applemusicalbumviewer;

/**
 * Handles fetching JSON from a single domain.
 */
public class JSONFetcher extends ConnectionHandler {
    public JSONFetcher(String domain) {
        super(domain);
    }

    /**
     * @param JSON Either a JSONObject or a JSONArray to be searched
     * @param js Javascript that would normally follow the JSON object to access its members
     * @return
     *  The value js accesses from the JSON on success
     *  Null on failure
     */
    protected Object getValue(Object JSON, String js){
        return null;
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