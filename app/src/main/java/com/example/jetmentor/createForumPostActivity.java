package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createForumPostActivity extends AppCompatActivity implements View.OnClickListener{

    private Button cancelBtn, postBtn;
    private EditText postTitle, postBody;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum_post);

        setUpButtons();

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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase

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
