package com.example.jetmentor;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class openMCConnectionDetailsActivity extends AppCompatActivity {

    private TextView menteeEmailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_mc_connection_details);
        menteeEmailMessage = findViewById(R.id.connected_mentor_message);
        menteeEmailMessage.setText("You can contact your mentee at : " + getIntent().getStringExtra("menteeEmail"));
    }
}
