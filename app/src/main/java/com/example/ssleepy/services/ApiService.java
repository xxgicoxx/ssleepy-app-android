package com.example.ssleepy.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.content.Context.MODE_PRIVATE;

public class ApiService {

    private Context context;
    private SharedPreferences prefs;
    private String ip;

    public ApiService(Context context) {
        this.context = context;
        this.prefs  = context.getSharedPreferences("settings", MODE_PRIVATE);
        this.ip = prefs.getString("ip", "http://192.168.0.1:1905");
    }

    public void post(String uri) {
        request(ip.concat(uri), Request.Method.POST);
    }

    private void request(String url, int type) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(type, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );

        queue.add(stringRequest);
    }
}
