package com.example.jetmentor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutInfo = findViewById(R.id.about_info_text);
        String infoString = "This app was created to connect people" +
                " who are seeking a job at a perticular company with current employees" +
                " from that company. These employees would act as mentors for the candidates" +
                " and help prepare them to both understand what the application and interviewing" +
                " processes are and what its like to work there. The goal is to help candidates and" +
                " and companies make better decisions in the interviewing proces." ;
        aboutInfo.setText(infoString);
    }
}