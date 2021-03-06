package com.example.jetmentor.ui.forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.ForumPost;
import com.example.jetmentor.R;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.MyViewHolder> {

    private List<ForumPost> postList;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ForumAdapter(Context ct, List<ForumPost> p){
        context = ct;
        postList = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.forum_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(postList.get(position).getTitle());
        //holder.user.setText(postList.get(position).getUser());
        holder.date.setText(postList.get(position).getDate());

        int size;
        if (postList.get(position).getComments() == null){
            size = 0;
        }else
            size = postList.get(position).getComments().size();

        holder.commentCount.setText(Integer.toString(size));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, user, date, commentCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postTitle);
            //user = itemView.findViewById(R.id.user);
            date = itemView.findViewById(R.id.date);
            commentCount = itemView.findViewById(R.id.commentCount);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
