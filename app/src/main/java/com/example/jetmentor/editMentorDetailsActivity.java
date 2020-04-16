package com.example.jetmentor;

import com.example.jetmentor.ui.mentorInfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;

public class editMentorDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitChanges, cancelChanges;
    private EditText inName, inCompany, inPosition, inYoe, inMessage;
    private CheckBox inAvailability;
    private FirebaseFirestore db;
    private CollectionReference mentors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SetupButtons();
        db = FirebaseFirestore.getInstance();
        mentors = db.collection("mentorsExtended");
    }

    private void SetupButtons()
    {
        submitChanges = findViewById(R.id.submit_changes);
        cancelChanges = findViewById(R.id.cancel_changes);

        submitChanges.setOnClickListener(this);
        cancelChanges.setOnClickListener(this);
    }

    private void UploadChanges()
    {
        inName = findViewById(R.id.name_input);
        inCompany = findViewById(R.id.company_input);
        inPosition = findViewById(R.id.position_input);
        inYoe = findViewById(R.id.years_of_experience);
        inAvailability = findViewById(R.id.availability_input);
        inMessage = findViewById(R.id.mentor_message);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        mentorInfo changedMentor = new mentorInfo(currentUser.getUid(), inName.getText().toString(), inCompany.getText().toString(), inPosition.getText().toString(), Double.parseDouble(inYoe.getText().toString()), inAvailability.isChecked(), inMessage.getText().toString(), currentUser.getEmail());


        HashMap<String, Object> mentorMap = new HashMap<String, Object>();
        mentorMap.put("available", changedMentor.getAvailable());
        mentorMap.put("company", changedMentor.getCompany());
        mentorMap.put("name", changedMentor.getName());
        mentorMap.put("position", changedMentor.getPosition());
        mentorMap.put("userId", changedMentor.getUserId());
        mentorMap.put("yearsOfExperience", changedMentor.getYearsOfExperience());
        mentorMap.put("message", changedMentor.getMessage());
        mentorMap.put("email", changedMentor.getEmail());

        mentors.document(changedMentor.getUserId()).set(mentorMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(editMentorDetailsActivity.this, "submitted changes", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(editMentorDetailsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent nextScreen = new Intent(this, landingStrip.class);
        switch(v.getId())
        {
            case R.id.submit_changes:
                UploadChanges();
                startActivity(nextScreen);
                break;
            case R.id.cancel_changes:
                startActivity(nextScreen);
                break;
            default:
                break;
        }
    }
}
