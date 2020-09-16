package com.example.ssleepy.controllers;

import android.content.Context;

import com.example.ssleepy.services.ApiService;

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
}
