package com.example.jetmentor.ui.scout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;

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
        LayoutInflater inflater = LayoutInflater.from(scoutMentorContext);
        View view = inflater.inflate(R.layout.scout_mentors_row, parent, false);
        return new ScoutMentorsRVVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutMentorsRVVH holder, int position) {
        holder.user.setText(users[position]);
        holder.company.setText(companies[position]);
        holder.position.setText(positions[position]);
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    public class ScoutMentorsRVVH extends RecyclerView.ViewHolder {

        TextView user, company, position;

        public ScoutMentorsRVVH(@NonNull View itemView)
        {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            company = itemView.findViewById(R.id.company);
            position = itemView.findViewById(R.id.position);
        }
    }
}
