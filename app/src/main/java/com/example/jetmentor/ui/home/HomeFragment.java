package com.example.jetmentor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView postsRecyclerView;

    String mockTitles[], mockUsers[], mockDates[], mockCommentCounts[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        postsRecyclerView = root.findViewById(R.id.postsRecyclerView);

        mockTitles = getResources().getStringArray(R.array.mockTitles);
        mockUsers = getResources().getStringArray(R.array.mockUsers);
        mockDates = getResources().getStringArray(R.array.mockDates);
        mockCommentCounts = getResources().getStringArray(R.array.mockCommentCounts);

        MyAdapter myAdapter = new MyAdapter( root.getContext(), mockTitles, mockUsers, mockDates, mockCommentCounts);
        postsRecyclerView.setAdapter(myAdapter);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }
}