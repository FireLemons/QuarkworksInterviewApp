package com.example.fly_s_y.Request;

import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Handles fetching requests from a single domain.
 */
public class RequestFetcher extends ConnectionHandler {
    protected OkHttpClient client;
    protected ErrorDisplay errorDisplay;
    protected String scheme;

    public RequestFetcher(String scheme, String domain, ErrorDisplay errorDisplay) {
        super(domain);

        this.errorDisplay = errorDisplay;
        this.client = new OkHttpClient();
        this.scheme = scheme;
    }

    /**
     * @param path The path from domain to reach the JSON
     * @return
     *  Either a JSONObject or a JSONArray on success
     *  Null on failure
     */
    protected void fetchRequest(String path, Callback callback){
        Request request = new Request.Builder().url(scheme + domain + path).build();
        Call call = client.newCall(request);

        call.enqueue(callback);
    }
}