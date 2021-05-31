package com.ighan.analytics;

import com.ighan.analytics.abstractions.ErrorListener;

import java.util.ArrayList;
import java.util.List;

public final class Configuration {

    private static String token;

    private static List<ErrorListener> errorListeners = new ArrayList<>();

    protected static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Configuration.token = token;
    }

    protected static List<ErrorListener> getErrorListeners() {
        return errorListeners;
    }

    public static void addErrorListener(ErrorListener errorListener) {
        errorListeners.add(errorListener);
    }
}
