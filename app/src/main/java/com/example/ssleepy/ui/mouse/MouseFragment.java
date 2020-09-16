package com.example.ssleepy.ui.mouse;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssleepy.R;
import com.example.ssleepy.controllers.ApiController;
import com.example.ssleepy.models.Mouse;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import static android.content.Context.MODE_PRIVATE;

public class MouseFragment extends Fragment {

    private float lastPosX = 0;
    private float lastPosY = 0;

    private boolean moved = false;

    private Context context;
    private Socket mSocket;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mouse, container, false);

        final SharedPreferences prefs = context.getSharedPreferences("settings", MODE_PRIVATE);
        final String ip = prefs.getString("ip", "http://192.168.0.1:1905");
        final Mouse mouse = new Mouse();

        socketConnect(ip);

        root.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    moved = false;
                } else if(e.getAction() == MotionEvent.ACTION_MOVE){
                    moved = true;

                    lastPosX = lastPosX != 0 ? lastPosX : e.getX();
                    lastPosY = lastPosY != 0 ? lastPosY : e.getY();

                    mouse.setX(e.getX() - lastPosX);
                    mouse.setY(e.getY() - lastPosY);

                    mSocket.emit("movemouse", objectToJson(mouse));

                    lastPosX = e.getX();
                    lastPosY = e.getY();
                } else if (e.getAction() == MotionEvent.ACTION_UP) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                    int width = displayMetrics.widthPixels;

                    if(moved) {
                        lastPosX = 0;
                        lastPosY = 0;
                    }  else if(!moved && e.getX() <= (width / 2)) {
                        ApiController apiController = new ApiController(context);
                        apiController.leftClick();
                    } else if(!moved && e.getY() > (width / 2)) {
                        ApiController apiController = new ApiController(context);
                        apiController.rightClick();
                    }
                }

                return true;
            }
        });

        return root;
    }

    private void socketConnect(String ip) {
        try {
            mSocket = IO.socket(ip);
            mSocket.connect();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private JSONObject objectToJson(Mouse mouse) {
        JSONObject obj = new JSONObject();

        try {
            final Gson gson = new Gson();
            obj = new JSONObject(gson.toJson(mouse));
        } catch (JSONException ex) {
            ex.printStackTrace();
        } finally {
            return obj;
        }
    }
}
