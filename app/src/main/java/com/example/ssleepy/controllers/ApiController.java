package com.example.ssleepy.controllers;

import android.content.Context;

import com.example.ssleepy.models.Keyboard;
import com.example.ssleepy.services.ApiService;
import com.example.ssleepy.utils.Utils;

public class ApiController {

    private Context context;

    public ApiController(Context context) {
        this.context = context;
    }

    public void playPause() {
        ApiService apiService = new ApiService(context);
        apiService.post("/playpause/");
    }

    public void next() {
        ApiService apiService = new ApiService(context);
        apiService.post("/next/");
    }

    public void previous() {
        ApiService apiService = new ApiService(context);
        apiService.post("/previous/");
    }

    public void volumeUp() {
        ApiService apiService = new ApiService(context);
        apiService.post("/volumeup/");
    }

    public void volumeDown() {
        ApiService apiService = new ApiService(context);
        apiService.post("/volumedown/");
    }

    public void mute() {
        ApiService apiService = new ApiService(context);
        apiService.post("/mute/");
    }

    public void fullscreen() {
        ApiService apiService = new ApiService(context);
        apiService.post("/fullscreen/");
    }

    public void refresh() {
        ApiService apiService = new ApiService(context);
        apiService.post("/refresh/");
    }

    public void close() {
        ApiService apiService = new ApiService(context);
        apiService.post("/close/");
    }

    public void shutdown() {
        ApiService apiService = new ApiService(context);
        apiService.post("/shutdown/");
    }

    public void restart() {
        ApiService apiService = new ApiService(context);
        apiService.post("/restart/");
    }

    public void leftClick() {
        ApiService apiService = new ApiService(context);
        apiService.post("/leftclick/");
    }

    public void rightClick() {
        ApiService apiService = new ApiService(context);
        apiService.post("/rightclick/");
    }

    public void write(String value) {
        ApiService apiService = new ApiService(context);

        Keyboard keyboard = new Keyboard();
        keyboard.setText(value);

        apiService.post("/write/", Utils.objectToJson(keyboard));
    }
}
