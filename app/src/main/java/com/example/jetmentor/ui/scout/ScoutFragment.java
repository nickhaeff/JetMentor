package com.example.jetmentor.ui.scout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.ui.settings.SettingsViewModel;

public class ScoutFragment extends Fragment {

    private ScoutViewModel scoutViewModel;

    RecyclerView scoutMentorsRV;
    String mockUsers[], mockCompanies[], mockPositions[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scoutViewModel =
                ViewModelProviders.of(this).get(ScoutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scout, container, false);
        final TextView textView = root.findViewById(R.id.text_scout);
        scoutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        scoutMentorsRV = root.findViewById(R.id.scoutMentorsRV);
        mockUsers = getResources().getStringArray(R.array.mockProfileNames);
        mockCompanies = getResources().getStringArray(R.array.mockProfileCompanies);
        mockPositions = getResources().getStringArray(R.array.mockProfilePositions);

        ScoutMentorsRVAdapter scoutMentorsRVAdapter = new ScoutMentorsRVAdapter(root.getContext(), mockUsers, mockCompanies, mockPositions);
        scoutMentorsRV.setAdapter(scoutMentorsRVAdapter);
        scoutMentorsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }
}