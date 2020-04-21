package com.example.jetmentor.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetmentor.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("");
            }
        });


        final Intent intent = new Intent(getActivity(), com.example.jetmentor.viewProfileActivity.class);
        final Button viewProfile = root.findViewById(R.id.view_profile);

        viewProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(intent);
            }
        });

        final Intent intent1 = new Intent(getActivity(), com.example.jetmentor.editMentorDetailsActivity.class);
        final Button editProfile = root.findViewById(R.id.edit_profile);

        editProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(intent1);
            }
        });

        final Intent aboutIntent = new Intent(getActivity(), com.example.jetmentor.aboutActivity.class);
        final Button aboutButton = root.findViewById(R.id.about);

        aboutButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(aboutIntent);
            }
        });

        final Intent supportIntent = new Intent(getActivity(), com.example.jetmentor.supportActivity.class);
        final Button supportButton = root.findViewById(R.id.support);

        supportButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(supportIntent);
            }
        });

        return root;
    }
}