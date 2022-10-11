package com.example.ssleepy.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static android.content.Context.MODE_PRIVATE;

public class ApiService {

    private Context context;
    private SharedPreferences prefs;
    private String ip;

    public ApiService(Context context) {
        this.context = context;
        this.prefs  = context.getSharedPreferences("settings", MODE_PRIVATE);
        this.ip = prefs.getString("ip", "http://127.0.0.1:1905");
    }

    public void post(String uri) {
        request(ip.concat(uri), Request.Method.POST, null);
    }

    public void post(String uri, JSONObject body) {
        request(ip.concat(uri), Request.Method.POST, body);
    }

    private void request(String url, int type, JSONObject body) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String requestBody = body != null ? body.toString() : null;

        StringRequest stringRequest = new StringRequest(type, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";

                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }

                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        queue.add(stringRequest);
    }
}
