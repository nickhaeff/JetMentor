package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jetmentor.ui.forum.ForumAdapter;
import com.example.jetmentor.ui.forum.ForumCommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class openForumPostActivity extends AppCompatActivity {

    private TextView title, body; //add user, date
    private RecyclerView commentsRecyclerView;
    private List<String> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_forum_post);

    /*    String title = getIntent().getStringExx`  tra("title");
        String user = getIntent().getStringExtra("user");
        String date = getIntent().getStringExtra("date");
        String commentCount = getIntent().getStringExtra("commentCount");
    */

        title = findViewById(R.id.view_post_title);
        body = findViewById(R.id.view_post_body);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);

        title.setText(getIntent().getStringExtra("title"));
        body.setText(getIntent().getStringExtra("body"));


        commentList = new ArrayList<String>();

        Log.d("yes ", "before");
        final ForumCommentAdapter forumCommentsAdapter = new ForumCommentAdapter(getApplicationContext(), commentList);
        commentsRecyclerView.setAdapter(forumCommentsAdapter);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        commentsRecyclerView.setLayoutManager(new LinearLayoutManager());


        return;
    }
}
