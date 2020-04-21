package com.example.jetmentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jetmentor.ui.mentorInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class viewProfileActivity extends AppCompatActivity {

    private TextView data_name, data_company, data_position, data_yoe, data_availability, data_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        data_name = findViewById(R.id.name_val);
        data_company = findViewById(R.id.company_val);
        data_position = findViewById(R.id.position_val);
        data_yoe = findViewById(R.id.yoe_val);
        data_availability = findViewById(R.id.availability_val);
        data_message = findViewById(R.id.message_val);

        String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore.getInstance().collection("mentorsExtended").document(currentUid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                mentorInfo thisMentor = task.getResult().toObject(mentorInfo.class);
                data_name.setText(thisMentor.getName());
                data_company.setText(thisMentor.getCompany());
                data_position.setText(thisMentor.getPosition());
                data_yoe.setText(Double.toString(thisMentor.getYearsOfExperience()));
                data_availability.setText(String.valueOf(thisMentor.getAvailable()));
                data_message.setText(thisMentor.getMessage());
            }
        });
    }
}
