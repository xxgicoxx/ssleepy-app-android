package com.example.ssleepy.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssleepy.R;
import com.example.ssleepy.utils.APIConstants;
import com.example.ssleepy.utils.PreferencesConstants;
import com.google.android.material.textfield.TextInputEditText;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        final SharedPreferences prefs = context.getSharedPreferences(PreferencesConstants.PREFERENCES_SETTINGS, MODE_PRIVATE);
        final String ip = prefs.getString(PreferencesConstants.PREFERENCES_IP, APIConstants.IP_DEFAULT);
        final Button btnSave = root.findViewById(R.id.btnSave);
        final TextInputEditText edtIp = root.findViewById(R.id.edtIp);

        edtIp.setText(ip);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = context.getSharedPreferences(PreferencesConstants.PREFERENCES_SETTINGS, MODE_PRIVATE).edit();
                editor.putString(PreferencesConstants.PREFERENCES_IP, edtIp.getText().toString());
                editor.apply();

                CharSequence text = "Success ".concat(edtIp.getText().toString());
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return root;
    }
}
