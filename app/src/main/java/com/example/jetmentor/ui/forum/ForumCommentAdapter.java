package com.example.jetmentor.ui.forum;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;

import java.util.List;

public class ForumCommentAdapter extends RecyclerView.Adapter<ForumCommentAdapter.MyViewHolder> {

    private List<String> commentList;
    Context context;



    public ForumCommentAdapter(Context ct, List<String> c){
        Log.d("yes ", "after");

        context = ct;
        commentList = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.forum_comment_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.comment.setText(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView comment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);


        }


    }
}
