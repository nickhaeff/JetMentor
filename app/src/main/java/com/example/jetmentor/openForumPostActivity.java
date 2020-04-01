package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class openForumPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_forum_post);

        String title = getIntent().getStringExtra("title");
        String user = getIntent().getStringExtra("user");
        String date = getIntent().getStringExtra("date");
        String commentCount = getIntent().getStringExtra("commentCount");

    }
}
