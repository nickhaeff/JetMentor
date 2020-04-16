package com.example.jetmentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class openMPConnectionDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView reqMessage;
    private Button accept, decline;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_mp_connection_details);
        reqMessage = findViewById(R.id.mentee_message_pending);
        reqMessage.setText(getIntent().getStringExtra("menteeMessage"));

        accept = findViewById(R.id.accept_mentee);
        decline = findViewById(R.id.decline_mentee);

        accept.setOnClickListener(this);
        decline.setOnClickListener(this);
    }


    public void UpdateStatus(int val)
    {
        HashMap<String, Object> updatedConnectDecision = new HashMap<>();
        updatedConnectDecision.put("menteeId", getIntent().getStringExtra("docId1"));
        updatedConnectDecision.put("mentorId", getIntent().getStringExtra("docId2"));
        updatedConnectDecision.put("reqMessage", getIntent().getStringExtra("menteeMessage"));
        updatedConnectDecision.put("menteeEmail", getIntent().getStringExtra("menteeEmailIn"));
        updatedConnectDecision.put("mentorEmail", getIntent().getStringExtra("mentorEmailIn"));
        updatedConnectDecision.put("status", val);
        FirebaseFirestore.getInstance().collection("connections").document(getIntent().getStringExtra("docId1")+getIntent().getStringExtra("docId2")).set(updatedConnectDecision);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.decline_mentee)
        {
//            FirebaseFirestore.getInstance().collection("connections").document(getIntent().getStringExtra("docId")).update("status",3);
//            FirebaseFirestore.getInstance().collection("connections").document(getIntent().getStringExtra("docId1")+getIntent().getStringExtra("docId2")).update("status",3);
            UpdateStatus(3);
            Intent nextScreen = new Intent(this, landingStrip.class);
            startActivity(nextScreen);
        }
        if(v.getId() == R.id.accept_mentee)
        {
            //FirebaseFirestore.getInstance().collection("connections").document(getIntent().getStringExtra("docId")).update("status",2);
            UpdateStatus(2);
            Intent nextScreen = new Intent(this, landingStrip.class);
            startActivity(nextScreen);
        }
    }
}
