package com.example.ssleepy.utils;

import com.example.ssleepy.models.Keyboard;
import com.example.ssleepy.models.Mouse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static JSONObject objectToJson(Mouse mouse) {
        JSONObject obj = new JSONObject();

        try {
            final Gson gson = new Gson();
            obj = new JSONObject(gson.toJson(mouse));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj;
    }

    public static JSONObject objectToJson(Keyboard keyboard) {
        JSONObject obj = new JSONObject();

        try {
            final Gson gson = new Gson();
            obj = new JSONObject(gson.toJson(keyboard));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj;
    }
}
