package com.ighan.analytics;

import android.os.Build;

import com.ighan.analytics.abstractions.ErrorListener;
import com.ighan.analytics.models.ErrorModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class IghanAnalytics {

    private static String BASE_URL = "http://192.168.1.51:41506/api/";

    public static void addValue(String key, String value) {
        new Thread(() -> {
            if (IghanAnalyticsConfiguration.getToken() == null) {
                error(new ErrorModel("Token not provided (use 'Configuration.setToken(String token)')", key, value));
                return;
            }
            try {
                URL url = new URL(BASE_URL + "statistics");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("token", IghanAnalyticsConfiguration.getToken());
                jsonParam.put("key", key);
                jsonParam.put("value", value);
                jsonParam.put("Brand", Build.BRAND);
                jsonParam.put("Model", Build.MODEL);
                jsonParam.put("Manufacturer", Build.MANUFACTURER);
                jsonParam.put("Release", Build.VERSION.RELEASE);
                jsonParam.put("Sdkint", Build.VERSION.SDK_INT);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();

                JSONObject responseObject = new JSONObject(response);
                boolean success = responseObject.getBoolean("success");
                if (!success)
                    throw new Exception(responseObject.getString("message"));

                conn.disconnect();
            } catch (Throwable exception) {
                error(new ErrorModel(exception, key, value));
            }
        }).start();
    }

    private static void error(ErrorModel error) {
        for (ErrorListener errorListener : IghanAnalyticsConfiguration.getErrorListeners())
            errorListener.errorHappened(error);
    }
}
