package com.example.jetmentor.ui.scout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.util.Arrays;

public class ScoutFragment extends Fragment {

    private ScoutViewModel scoutViewModel;

    private TextView et_searchScout;

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

        et_searchScout = root.findViewById(R.id.et_searchScout);


        scoutMentorsRV = root.findViewById(R.id.scoutMentorsRV);
        mockUsers = getResources().getStringArray(R.array.mockProfileNames);
        mockCompanies = getResources().getStringArray(R.array.mockProfileCompanies);
        mockPositions = getResources().getStringArray(R.array.mockProfilePositions);

        final ScoutMentorsRVAdapter scoutMentorsRVAdapter = new ScoutMentorsRVAdapter(root.getContext(), mockUsers, mockCompanies, mockPositions);
        scoutMentorsRV.setAdapter(scoutMentorsRVAdapter);
        scoutMentorsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));

        et_searchScout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();

                int[] includedIndeces = new int[mockUsers.length];
                int total = 0;
                for(int i = 0; i<mockUsers.length; i++){
                    if(mockUsers[i].contains(searchText) || mockCompanies[i].contains(searchText) || mockPositions[i].contains(searchText)||searchText.equals("")){
                        includedIndeces[total] = i;
                        total++;
                    }
                }
                includedIndeces = Arrays.copyOf(includedIndeces, total);

                String updatedUsers[], updatedCompanies[], updatedPositions[];

                updatedUsers = new String[total];
                updatedCompanies = new String[total];
                updatedPositions = new String[total];


                for (int i = 0; i < total; i++){
                    updatedUsers[i] = mockUsers[includedIndeces[i]];
                    updatedCompanies[i] = mockCompanies[includedIndeces[i]];
                    updatedPositions[i] = mockPositions[includedIndeces[i]];
                }



                scoutMentorsRVAdapter.updateScoutMentors(updatedUsers, updatedCompanies, updatedPositions);
                scoutMentorsRV.setAdapter(scoutMentorsRVAdapter);

            }
        });
        return root;
    }
}