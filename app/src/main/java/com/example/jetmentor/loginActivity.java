package com.example.jetmentor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
//        String expectedUsername = "Philip";
//        String expectedPassword = "password";
//
//        if(username.equalsIgnoreCase(expectedUsername) && password.equals(expectedPassword)){
//            Intent intent = new Intent(loginActivity.this, landingStrip.class);
//            startActivity(intent);
//        }else{
//        }

        if(username.equals("")){
            et_loginUsername.setError("can't be blank");
        }
        else if(password.equals("")){
            et_loginPassword.setError("can't be blank");
        }
        else{
            String url = "https://androidchat-9c94e.firebaseio.com/users.json";
            final ProgressDialog pd = new ProgressDialog(loginActivity.this);
            pd.setMessage("Loading...");
            pd.show();

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String s) {
                    if(s.equals("null")){
                        Toast.makeText(loginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                    }
                    else{
                        try {
                            JSONObject obj = new JSONObject(s);

                            if(!obj.has(username)){
                                Toast.makeText(loginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                            }
                            else if(obj.getJSONObject(username).getString("password").equals(password)){
                                UserDetails.username = username;
                                UserDetails.password = password;
                                startActivity(new Intent(loginActivity.this, Users.class));
                            }
                            else {
                                Toast.makeText(loginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    pd.dismiss();
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    System.out.println("" + volleyError);
                    pd.dismiss();
                }
            });

            RequestQueue rQueue = Volley.newRequestQueue(loginActivity.this);
            rQueue.add(request);
        }


    }



}
