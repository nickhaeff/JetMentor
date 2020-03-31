package com.example.jetmentor.ui.scout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoutMentorsRVAdapter extends RecyclerView.Adapter<ScoutMentorsRVAdapter.ScoutMentorsRVVH> {

    Context scoutMentorContext;
    String users[], companies[], positions[];

    public ScoutMentorsRVAdapter(Context ct, String inUsers[], String inCompanies[], String inPositions[]) {
        scoutMentorContext = ct;
        users = inUsers;
        companies = inCompanies;
        positions = inPositions;
    }

    @NonNull
    @Override
    public ScoutMentorsRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutMentorsRVVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScoutMentorsRVVH extends RecyclerView.ViewHolder {

        public ScoutMentorsRVVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
