package com.example.jetmentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;


public class createForumPostActivity extends AppCompatActivity implements View.OnClickListener{

    private Button cancelBtn, postBtn;
    private EditText postTitle, postBody;
    private FirebaseFirestore db;
    private CollectionReference posts;
    //private String user, date;
   // private int

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum_post);

        setUpButtons();
        db = FirebaseFirestore.getInstance();
        posts = db.collection("posts");
        return;
    }

    public void setUpButtons(){
        cancelBtn = findViewById(R.id.create_post_cancel_btn);
        postBtn = findViewById(R.id.create_post_post_btn);

        cancelBtn.setOnClickListener(this);
        postBtn.setOnClickListener(this);

        return;
    }

    public void makePost(){
        postTitle = findViewById(R.id.create_post_title);
        postBody = findViewById(R.id.create_post_body);

        ForumPost post = new ForumPost(postTitle.getText().toString(), postBody.getText().toString());

        posts.add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(createForumPostActivity.this, "Added post", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(createForumPostActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, landingStrip.class);
        switch(v.getId()){
            case R.id.create_post_cancel_btn:
                startActivity(intent);
                break;
            case R.id.create_post_post_btn:
                makePost();
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
