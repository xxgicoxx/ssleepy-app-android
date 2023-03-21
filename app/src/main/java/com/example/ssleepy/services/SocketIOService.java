package com.example.ssleepy.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ssleepy.utils.APIConstants;
import com.example.ssleepy.utils.PreferencesConstants;
import com.example.ssleepy.utils.SocketConstants;
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

    private String ip;

    private SocketIOService(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PreferencesConstants.PREFERENCES_SETTINGS, MODE_PRIVATE);

        this.ip = prefs.getString(PreferencesConstants.PREFERENCES_IP, APIConstants.IP_DEFAULT);

        socketIOInitialization.put(SocketConstants.USER, connectSocket());
        sockets.put(SocketConstants.USER, this.socket);
    }

    public static SocketIOService getSocketInstance(Context context) {
        if (null == socketIOInitialization.get(SocketConstants.USER)) {
            new SocketIOService(context);
        }

        return socketIOInitialization.get(SocketConstants.USER);
    }

    public Socket getSocket() {
        return sockets.get(SocketConstants.USER);
    }

    private SocketIOService connectSocket() {
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[] { WebSocket.NAME };
            options.query = String.format("userId=%s", SocketConstants.USER);

            socket = IO.socket(ip, options);

            socket.connect();

            return this;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
