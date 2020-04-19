package com.example.jetmentor.ui.scout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.ui.mentorInfo;

import java.util.List;

public class ScoutMentorsRVAdapter extends RecyclerView.Adapter<ScoutMentorsRVAdapter.ScoutMentorsRVVH> {

    Context scoutMentorContext;
    private List<mentorInfo> mentorList;
    private OnItemClickListener mentorClickListener;

    public interface OnItemClickListener
    {
        void onItemCLick(int position);
    }


    public ScoutMentorsRVAdapter(Context ct, List<mentorInfo> inMentorList) {

        scoutMentorContext = ct;
        mentorList = inMentorList;
    }

    public void updateScoutMentors(List<mentorInfo> inMentorList) {
        mentorList = inMentorList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){ mentorClickListener = listener;}

    @NonNull
    @Override
    public ScoutMentorsRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(scoutMentorContext);
        View view = inflater.inflate(R.layout.scout_mentors_row, parent, false);
        return new ScoutMentorsRVVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutMentorsRVVH holder, int position) {
        holder.user.setText(mentorList.get(position).getName());
        holder.company.setText(mentorList.get(position).getCompany());
        holder.position.setText(mentorList.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public class ScoutMentorsRVVH extends RecyclerView.ViewHolder {

        TextView user, company, position;

        public ScoutMentorsRVVH(@NonNull View itemView)
        {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            company = itemView.findViewById(R.id.company);
            position = itemView.findViewById(R.id.position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mentorClickListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            mentorClickListener.onItemCLick(position);
                        }
                    }
                }
            });
        }
    }
}
