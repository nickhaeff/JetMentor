package com.example.jetmentor.ui.scout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.openMentorDetailsActivity;
import com.example.jetmentor.ui.mentorInfo;
import com.example.jetmentor.ui.settings.SettingsFragment;
import com.example.jetmentor.ui.settings.SettingsViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScoutFragment extends Fragment {

    public static List<mentorInfo> ContentSearch(String searchText, List<mentorInfo> inMentorsList, String fbauid)
    {

        int[] includedIndeces = new int[inMentorsList.size()];
        int total = 0;
        for(int i = 0; i<inMentorsList.size(); i++){
            if(inMentorsList.get(i).getName().contains(searchText) || inMentorsList.get(i).getCompany().contains(searchText) || inMentorsList.get(i).getPosition().contains(searchText)||searchText.equals("")){
                if(inMentorsList.get(i).getAvailable()&&!((inMentorsList.get(i).getUserId()).equals(fbauid)))
//                if(inMentorsList.get(i).getAvailable()&&!((inMentorsList.get(i).getUserId()).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())))
                {
                    includedIndeces[total] = i;
                    total++;
                }
            }
        }
        includedIndeces = Arrays.copyOf(includedIndeces, total);

        String updatedUsers[], updatedCompanies[], updatedPositions[];

        updatedUsers = new String[total];
        updatedCompanies = new String[total];
        updatedPositions = new String[total];


        for (int i = 0; i < total; i++){
            updatedUsers[i] = inMentorsList.get(includedIndeces[i]).getName();
            updatedCompanies[i] = inMentorsList.get(includedIndeces[i]).getCompany();
            updatedPositions[i] = inMentorsList.get(includedIndeces[i]).getPosition();
        }


        String updates[][] = {updatedUsers, updatedCompanies, updatedPositions};

        List<mentorInfo> updatedList = new ArrayList<>();


        for(int i = 0; i < updates[0].length; i++)
        {
            updatedList.add((new mentorInfo(updates[0][i],updates[1][i],updates[2][i])));
        }


        return updatedList;
    }

    private ScoutViewModel scoutViewModel;

    private TextView et_searchScout;

    private RecyclerView scoutMentorsRV;
    private List<mentorInfo> mentorsList;
    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scoutViewModel =
                ViewModelProviders.of(this).get(ScoutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scout, container, false);
        final TextView textView = root.findViewById(R.id.text_scout);
        scoutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        et_searchScout = root.findViewById(R.id.et_searchScout);

        final ScoutMentorsRVAdapter scoutMentorsRVAdapter = buildRecyclerView(root);


        et_searchScout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                //Take in string , 3 params, return updated/filtered params


                String searchText = s.toString();

                List<mentorInfo> updatedList = ScoutFragment.ContentSearch(searchText, mentorsList, FirebaseAuth.getInstance().getCurrentUser().getUid());

                scoutMentorsRVAdapter.updateScoutMentors(updatedList);
                scoutMentorsRV.setAdapter(scoutMentorsRVAdapter);

            }
        });

        return root;
    }

    private ScoutMentorsRVAdapter buildRecyclerView(View root)
    {
        final Context localContext = root.getContext();
        scoutMentorsRV = root.findViewById(R.id.scoutMentorsRV);
        mentorsList = new ArrayList<>();
        final ScoutMentorsRVAdapter scoutMentorsRVAdapter = new ScoutMentorsRVAdapter(root.getContext(), mentorsList);
        scoutMentorsRV.setAdapter(scoutMentorsRVAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection("mentorsExtended").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                mentorInfo p = d.toObject(mentorInfo.class);
                                if(p.getAvailable()&&!((p.getUserId()).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())))
                                {
                                    mentorsList.add(p);
                                }
                            }
                            scoutMentorsRVAdapter.notifyDataSetChanged();
                        }
                    }
                });

        scoutMentorsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));

        scoutMentorsRVAdapter.setOnItemClickListener(new ScoutMentorsRVAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                Intent nextIntent = new Intent(getActivity(), openMentorDetailsActivity.class);
//                Toast.makeText(localContext, "working", Toast.LENGTH_LONG).show();
                nextIntent.putExtra("clickedUserName", mentorsList.get(position).getName());
                nextIntent.putExtra("clickedUserCompany", mentorsList.get(position).getCompany());
                nextIntent.putExtra("clickedUserPosition", mentorsList.get(position).getPosition());
                nextIntent.putExtra("clickedUserYoe", Double.toString(mentorsList.get(position).getYearsOfExperience()));
                nextIntent.putExtra("clickedUserMessage", mentorsList.get(position).getMessage());
                nextIntent.putExtra("clickedUserId", mentorsList.get(position).getUserId());
                nextIntent.putExtra("clickedUserEmail", mentorsList.get(position).getEmail());
                startActivity(nextIntent);
            }
        });


        return scoutMentorsRVAdapter;
    }

}