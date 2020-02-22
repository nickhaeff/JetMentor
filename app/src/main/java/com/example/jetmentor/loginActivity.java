package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText et_loginUsername;
    private EditText et_loginPassword;

    private Button btn_loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_loginUsername = (EditText) findViewById(R.id.et_loginUsername);
        et_loginPassword = (EditText) findViewById(R.id.et_loginPassword);
        btn_loginButton = (Button) findViewById(R.id.btn_login);

        btn_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(et_loginUsername.getText().toString(), et_loginPassword.getText().toString());
            }
        });
    }

    private void validate(String username, String password){
        String expectedUsername = "Philip";
        String expectedPassword = "password";

        if(username.equalsIgnoreCase(expectedUsername) && password.equals(expectedPassword)){
            Intent intent = new Intent(loginActivity.this, landingStrip.class);
            startActivity(intent);
        }else{
        }
    }
}
