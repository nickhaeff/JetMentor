package com.example.jetmentor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class openECConnectionDetailsActivity extends AppCompatActivity {

    private TextView mentorEmailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ec_connection_details);
        mentorEmailMessage = findViewById(R.id.connected_mentor_message);
        mentorEmailMessage.setText("You can contact your mentor at : " + getIntent().getStringExtra("mentorEmail"));
    }
}
