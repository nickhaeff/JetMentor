package com.example.jetmentor.ui.notifications;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.connectInfo;
import com.google.android.gms.tasks.OnSuccessListener;
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
                textView.setText(s);
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
                                connectInfoList.add(p);
                            }
                            connectionsRVAdapter.notifyDataSetChanged();
                        }
                    }
                });

        currentConnectionsRV.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return connectionsRVAdapter;
    }
}