package com.example.jetmentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class openMentorDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView user, company, position, yoe, message, requestMessage;
    private Button requestMentorship;
    private String potMentorId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_mentor_details);

        user = findViewById(R.id.mentor_name);
        company = findViewById(R.id.company_name);
        position = findViewById(R.id.position_name);
        yoe = findViewById(R.id.mentors_years_of_experience);
        message = findViewById(R.id.mentors_message);

        user.setText(getIntent().getStringExtra("clickedUserName"));
        company.setText(getIntent().getStringExtra("clickedUserCompany"));
        position.setText(getIntent().getStringExtra("clickedUserPosition"));
        yoe.setText(getIntent().getStringExtra("clickedUserYoe"));
        message.setText(getIntent().getStringExtra("clickedUserMessage"));

        requestMessage = findViewById(R.id.request_mentor_message);

        requestMentorship = findViewById(R.id.request_mentorship_button);
        requestMentorship.setOnClickListener(this);

        potMentorId = getIntent().getStringExtra("clickedUserId");

    }

    private void UploadRequest()
    {
        CollectionReference connects = FirebaseFirestore.getInstance().collection("connections");

        String potMenteeId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String docId = potMenteeId + potMentorId;
        String reqMess = requestMessage.getText().toString();

        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("menteeId", potMenteeId);
        reqMap.put("mentorId", potMentorId);
        reqMap.put("reqMessage", reqMess);
        reqMap.put("status", 1);

        connects.document(docId).set(reqMap);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.request_mentorship_button)
        {
            Intent nextIntent = new Intent(this, landingStrip.class);
            UploadRequest();
            startActivity(nextIntent);
        }
    }
}
