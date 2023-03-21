package com.example.ssleepy.ui.keyboard;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssleepy.R;
import com.example.ssleepy.controllers.ApiController;
import com.example.ssleepy.models.Keyboard;
import com.example.ssleepy.services.SocketIOService;
import com.example.ssleepy.utils.LinkConstants;
import com.example.ssleepy.utils.SocketConstants;
import com.example.ssleepy.utils.Utils;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.textfield.TextInputEditText;


public class KeyboardFragment extends Fragment {

    private Context context;
    private Socket socket;

    private int lastLength = 0;
    private int length = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_keyboard, container, false);

        final TextInputEditText edtKeyboard = root.findViewById(R.id.edtKeyboard);

        final Button netflix = root.findViewById(R.id.netflix);
        final Button prime = root.findViewById(R.id.prime);
        final Button hbo = root.findViewById(R.id.hbo);
        final Button disney = root.findViewById(R.id.disney);
        final Button apple = root.findViewById(R.id.apple);
        final Button star = root.findViewById(R.id.star);

        final Keyboard keyboard = new Keyboard();

        socketConnect();

        netflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.NETFLIX);
            }
        });

        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.PRIME);
            }
        });

        hbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.HBO);
            }
        });

        disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.DISNEY);
            }
        });

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.APPLE);
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController apiController = new ApiController(context);
                apiController.write(LinkConstants.STAR);
            }
        });

        edtKeyboard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                length = edtKeyboard.getText().length();

                if (length > lastLength) {
                    keyboard.setText(edtKeyboard.getText().toString().substring(lastLength, length));
                    socket.emit(SocketConstants.EMIT_KEYBOARD, Utils.objectToJson(keyboard));
                } else if (length < lastLength) {
                    socket.emit(SocketConstants.EMIT_BACKSPACE, Utils.objectToJson(keyboard));
                }

                lastLength = edtKeyboard.getText().length();
            }
        });

        edtKeyboard.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN) {
                    return true;
                }

                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    socket.emit(SocketConstants.EMIT_BACKSPACE, Utils.objectToJson(keyboard));
                }

                return false;
            }
        });

        return root;
    }

    private void socketConnect() {
        socket = SocketIOService.getSocketInstance(context).getSocket();
    }
}