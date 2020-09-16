package com.example.ssleepy.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssleepy.R;
import com.example.ssleepy.controllers.ApiController;

public class HomeFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final Button playPause = root.findViewById(R.id.playPause);
        final Button next = root.findViewById(R.id.next);
        final Button previous = root.findViewById(R.id.previous);
        final Button volumeUp = root.findViewById(R.id.volumeUp);
        final Button volumeDown = root.findViewById(R.id.volumeDown);
        final Button mute = root.findViewById(R.id.mute);
        final Button fullscreen = root.findViewById(R.id.fullscreen);
        final Button close = root.findViewById(R.id.close);
        final Button shutdown = root.findViewById(R.id.shutdown);
        final Button restart = root.findViewById(R.id.restart);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.playPause();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.next();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.previous();
            }
        });

        volumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.volumeUp();
            }
        });

        volumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.volumeDown();
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.mute();
            }
        });

        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.fullscreen();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.close();
            }
        });

        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.shutdown();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.restart();
            }
        });

        return root;
    }
}
