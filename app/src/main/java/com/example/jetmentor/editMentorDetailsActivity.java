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
import com.google.firebase.firestore.FirebaseFirestore;

public class editMentorDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitChanges, cancelChanges;
    private EditText inName, inCompany, inPosition, inYoe;
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

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

//        mentorInfo changedMentor = new mentorInfo(inName.getText().toString(), inCompany.getText().toString(), inPosition.getText().toString());
        mentorInfo changedMentor = new mentorInfo(currentUser.getUid(), inName.getText().toString(), inCompany.getText().toString(), inPosition.getText().toString(), Double.parseDouble(inYoe.getText().toString()), inAvailability.isChecked());
//        mentors.add(changedMentor).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(editMentorDetailsActivity.this, "submitted changes", Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(editMentorDetailsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
        mentors.add(changedMentor).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
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
