package com.ighan.analytics;

import android.os.Build;
import android.util.JsonReader;

import com.ighan.analytics.abstractions.ErrorListener;
import com.ighan.analytics.models.ErrorModel;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class IghanAnalytics {

    private static String BASE_URL = "http://analytics.sobhaniyan.ir/api/";

    public void add(String key, String value) {
        if (Configuration.getToken() == null) {
            ErrorModel error = new ErrorModel("Token not provided (use 'Configuration.setToken(String token)')", key, value);
            for (ErrorListener errorListener : Configuration.getErrorListeners())
                errorListener.errorHappened(error);
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
            jsonParam.put("token", Configuration.getToken());
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

            String response = conn.getResponseMessage();

            JSONObject responseObject = new JSONObject(response);
            boolean success = responseObject.getBoolean("Success");
            if (!success)
                throw new Exception(responseObject.getString("Message"));

            conn.disconnect();
        } catch (Throwable exception) {
            ErrorModel error = new ErrorModel(exception, key, value);
            for (ErrorListener errorListener : Configuration.getErrorListeners())
                errorListener.errorHappened(error);
        }
    }
}
