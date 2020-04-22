package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jetmentor.ui.forum.ForumAdapter;
import com.example.jetmentor.ui.forum.ForumCommentAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class openForumPostActivity extends AppCompatActivity {

    private TextView title, body; //add user, date
    private RecyclerView commentsRecyclerView;
    private List<String> commentList;
    private EditText newComment;
    private Button postCommentBtn;
    private FirebaseFirestore db;


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
        newComment = findViewById(R.id.newComment);
        postCommentBtn = findViewById(R.id.postCommentButton);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);

        title.setText(getIntent().getStringExtra("title"));
        body.setText(getIntent().getStringExtra("body"));


//        String[] commentArray = getIntent().getStringArrayExtra("comments");
//        Log.d("1", commentArray.toString());
//        commentList = new ArrayList<String>(Arrays.asList(commentArray));
        commentList = new ArrayList<String>();
        commentList = getIntent().getStringArrayListExtra("comments");

//        if(commentArray.length == 1)
//            commentList.add(commentArray[0]);
//
        final ForumCommentAdapter forumCommentsAdapter = new ForumCommentAdapter(getApplicationContext(), commentList);
        commentsRecyclerView.setAdapter(forumCommentsAdapter);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        postCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCommentString = newComment.getText().toString();
                if(!newCommentString.equals("")) {


                    db = FirebaseFirestore.getInstance();
                    CollectionReference posts = db.collection("posts");
                    Map<String, Object> data = new HashMap<>();
                    data.put("title", getIntent().getStringExtra("title"));
                    data.put("body", getIntent().getStringExtra("body"));
                    data.put("user", getIntent().getStringExtra("user"));
                    data.put("date", getIntent().getStringExtra("date"));
                    data.put("commentCount", 0);
                    data.put("id", 0);

                    ArrayList<String> updatedComments = getIntent().getStringArrayListExtra("comments");
                    updatedComments.add(newComment.getText().toString());

                    data.put("comments", updatedComments);

                    posts.document(getIntent().getStringExtra("docID")).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(openForumPostActivity.this, "Comment Posted Successfully!", Toast.LENGTH_LONG).show();
                            newComment.setText("");
                        }
                    });

                }

            }
        });


        return;
    }
}
