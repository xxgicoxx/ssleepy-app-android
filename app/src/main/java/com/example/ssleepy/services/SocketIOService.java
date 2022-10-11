package com.example.ssleepy.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SocketIOService {

    public Socket socket;

    private static final Map<String, SocketIOService> socketIOInitialization = new HashMap<>();
    private static final Map<String, Socket> sockets = new HashMap<>();
    private static final String USER = "ANDROID";

    private String ip;

    private SocketIOService(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences("settings", MODE_PRIVATE);

        this.ip = prefs.getString("ip", "http://127.0.0.1:1905");

        socketIOInitialization.put(USER, connectSocket());
        sockets.put(USER, this.socket);
    }

    public static SocketIOService getSocketInstance(Context context) {
        if (null == socketIOInitialization.get(USER)) {
            new SocketIOService(context);
        }

        return socketIOInitialization.get(USER);
    }

    public Socket getSocket() {
        return sockets.get(USER);
    }

    private SocketIOService connectSocket() {
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[] { WebSocket.NAME };
            options.query = "userId=" + USER;

            socket = IO.socket(ip, options);

            socket.connect();

            return this;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
