package com.example.ssleepy.controllers;

import android.content.Context;

import com.example.ssleepy.models.Keyboard;
import com.example.ssleepy.services.ApiService;
import com.example.ssleepy.utils.APIConstants;
import com.example.ssleepy.utils.Utils;

public class ApiController {

    private Context context;

    public ApiController(Context context) {
        this.context = context;
    }

    public void playPause() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_PLAY_PAUSE);
    }

    public void next() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_NEXT);
    }

    public void previous() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_PREVIOUS);
    }

    public void volumeUp() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_VOLUME_UP);
    }

    public void volumeDown() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_VOLUME_DOWN);
    }

    public void mute() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_MUTE);
    }

    public void fullscreen() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_FULLSCREEN);
    }

    public void refresh() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_REFRESH);
    }

    public void close() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_CLOSE);
    }

    public void shutdown() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_SHUTDOWN);
    }

    public void restart() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_RESTART);
    }

    public void leftClick() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_LEFT_CLICK);
    }

    public void rightClick() {
        ApiService apiService = new ApiService(context);
        apiService.post(APIConstants.POST_RIGHT_CLICK);
    }

    public void write(String value) {
        ApiService apiService = new ApiService(context);

        Keyboard keyboard = new Keyboard();
        keyboard.setText(value);

        apiService.post(APIConstants.POST_WRITE, Utils.objectToJson(keyboard));
    }
}
