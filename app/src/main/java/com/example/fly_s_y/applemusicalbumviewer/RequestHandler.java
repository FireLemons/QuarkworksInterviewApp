package com.example.fly_s_y.applemusicalbumviewer;

import java.io.IOException;

/**
 * Handles communication between the app and a single domain.
 */
public class RequestHandler {
    protected String domain;

    public RequestHandler(String domain) {
        this.domain = domain;
    }

    /**
     * Determines whether there is a connection to domain
     * @return
     *      true on successful ping
     *      false otherwise
     */
    public boolean isConnection() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1" + domain);
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}