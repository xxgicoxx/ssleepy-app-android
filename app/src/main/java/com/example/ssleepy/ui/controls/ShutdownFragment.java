package com.example.ssleepy.ui.controls;

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

public class ShutdownFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shutdown, container, false);

        final Button shutdown = root.findViewById(R.id.shutdown);
        final Button restart = root.findViewById(R.id.restart);

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