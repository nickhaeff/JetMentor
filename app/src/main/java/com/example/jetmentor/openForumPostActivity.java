package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class openForumPostActivity extends AppCompatActivity {

    private TextView title, body; //add user, date
    private RecyclerView commentsRecyclerView;

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
        body.setText("lorem ipsum dolar ");


        return;
    }
}
