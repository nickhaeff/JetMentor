package com.example.jetmentor.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.connectInfo;
import com.example.jetmentor.openConnectionDetailsActivity;
import com.example.jetmentor.openECConnectionDetailsActivity;
import com.example.jetmentor.openEPConnectionDetailsActivity;
import com.example.jetmentor.openMCConnectionDetailsActivity;
import com.example.jetmentor.openMPConnectionDetailsActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private RecyclerView currentConnectionsRV;
    private List<connectInfo> connectInfoList;
    private FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("");
            }
        });


        final ConnectionsRVAdapter connectionsRVAdapter = buildRecyclerView(root);



        return root;
    }

    private ConnectionsRVAdapter buildRecyclerView(View root)
    {
        final Context localContext = root.getContext();
        currentConnectionsRV = root.findViewById(R.id.current_connections);
        connectInfoList = new ArrayList<>();
        final ConnectionsRVAdapter connectionsRVAdapter = new ConnectionsRVAdapter(root.getContext(), connectInfoList);
        currentConnectionsRV.setAdapter(connectionsRVAdapter);

        db = FirebaseFirestore.getInstance();


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();


        db.collection("connections").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list)
                            {
                                connectInfo p = d.toObject(connectInfo.class);
                                if((p.getMenteeId().equals(currentUser.getUid())||p.getMentorId().equals(currentUser.getUid()))&&(p.getStatus()<3)){
                                connectInfoList.add(p);
                            }
                            }
                            connectionsRVAdapter.notifyDataSetChanged();
                        }
                    }
                });


        currentConnectionsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));


        connectionsRVAdapter.setOnItemClickListener(new ConnectionsRVAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position) {

                if(connectInfoList.get(position).getMentorId().equals(currentUser.getUid())&&connectInfoList.get(position).getStatus()==1)
                {
                    //pending, from mentor side
                    Intent nextIntent = new Intent(getActivity(), openMPConnectionDetailsActivity.class);
                    nextIntent.putExtra("menteeMessage", connectInfoList.get(position).getReqMessage());
                    nextIntent.putExtra("docId1", connectInfoList.get(position).getMenteeId());
                    nextIntent.putExtra("docId2", connectInfoList.get(position).getMentorId());
                    nextIntent.putExtra("menteeEmailIn", connectInfoList.get(position).getMenteeEmail());
                    nextIntent.putExtra("mentorEmailIn", connectInfoList.get(position).getMentorEmail());
                    startActivity(nextIntent);
                }
                if(connectInfoList.get(position).getMenteeId().equals(currentUser.getUid())&&connectInfoList.get(position).getStatus()==1)
                {
                    //pending, from mentee side
                    Intent nextIntent = new Intent(getActivity(), openEPConnectionDetailsActivity.class);
                    startActivity(nextIntent);
                }
                if(connectInfoList.get(position).getMentorId().equals(currentUser.getUid())&&connectInfoList.get(position).getStatus()==2)
                {
                    //connected from mentorside
                    Intent nextIntent = new Intent(getActivity(), openMCConnectionDetailsActivity.class);
                    nextIntent.putExtra("menteeEmail", connectInfoList.get(position).getMenteeEmail());
                    startActivity(nextIntent);
                }
                if(connectInfoList.get(position).getMenteeId().equals(currentUser.getUid())&&connectInfoList.get(position).getStatus()==2)
                {
                    //connected from menteeside
                    Intent nextIntent = new Intent(getActivity(), openECConnectionDetailsActivity.class);
                    nextIntent.putExtra("mentorEmail", connectInfoList.get(position).getMentorEmail());
                    startActivity(nextIntent);
                }

//                Intent nextIntent = new Intent(getActivity(), openConnectionDetailsActivity.class);
//                startActivity(nextIntent);
            }
        });

        currentConnectionsRV.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));

        return connectionsRVAdapter;
    }
}