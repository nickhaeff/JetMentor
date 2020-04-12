package com.example.jetmentor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class openMentorDetailsActivity extends AppCompatActivity {

    private TextView user, company, position, yoe, message;

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

    }

}
