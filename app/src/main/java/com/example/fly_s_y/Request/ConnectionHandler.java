package com.example.fly_s_y.Request;

import java.io.IOException;

/**
 * Handles communication between the app and a single domain.
 */
public class ConnectionHandler {
    protected String domain;

    public ConnectionHandler(String domain) {
        this.domain = domain;
    }

    /**
     * Determines whether there is a connection to domain
     * taken from https://stackoverflow.com/a/27312494/3667444
     * @return
     *      true on successful ping
     *      false otherwise
     */
    public boolean isConnection() {
        Runtime runtime = Runtime.getRuntime();

        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 " + domain);
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}