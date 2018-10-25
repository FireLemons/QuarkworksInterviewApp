package com.example.fly_s_y.Request;

import com.example.fly_s_y.applemusicalbumviewer.ErrorDisplay;

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

    /**
     * Cancel wating on all active requests
     */
    public void cancelAllRequests(){
        for(Call call : client.dispatcher().queuedCalls()) {
            call.cancel();
        }
        for(Call call : client.dispatcher().runningCalls()) {
            call.cancel();
        }
    }
}