package com.example.jetmentor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class supportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        TextView supportInfo = findViewById(R.id.support_info_text);
        String infoString = "If you are experiencing issues with this app"+
                " please make sure you are using the latest version. If no updates" +
                " are available and your android operating system is up to date then" +
                " feel free to reach out to us at jetmentor2020@gmail.com .";
        supportInfo.setText(infoString);
    }
}