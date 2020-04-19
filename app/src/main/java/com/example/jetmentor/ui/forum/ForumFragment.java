package com.example.jetmentor.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.ForumPost;
import com.example.jetmentor.R;
import com.example.jetmentor.createForumPostActivity;
import com.example.jetmentor.openForumPostActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment implements View.OnClickListener{

    private ForumViewModel homeViewModel;
    private RecyclerView postsRecyclerView;
    private String mockTitles[], mockUsers[], mockDates[], mockCommentCounts[];
    private Button btnCreatePost;
    private FirebaseFirestore db;
    private List<ForumPost> postList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        buildRecyclerView(root);

        btnCreatePost = (Button) root.findViewById(R.id.forum_create_post_btn);
        btnCreatePost.setOnClickListener(this);
        return root;
    }

    public void buildRecyclerView(View root){
        postsRecyclerView = root.findViewById(R.id.postsRecyclerView);
        postList = new ArrayList<>();
        final ForumAdapter forumAdapter = new ForumAdapter( root.getContext(), postList);
        postsRecyclerView.setAdapter(forumAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection("posts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                          if(!queryDocumentSnapshots.isEmpty()){
                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d : list){
                                    ForumPost p = d.toObject(ForumPost.class);
                                    postList.add(p);
                                }

                                forumAdapter.notifyDataSetChanged();
                          }
                    }
                });

        postsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));

        forumAdapter.setOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), openForumPostActivity.class);
                intent.putExtra("title", postList.get(position).getTitle());
                intent.putExtra("user",  postList.get(position).getUser());
                intent.putExtra("body", postList.get(position).getBody());
            //    intent.putExtra("date",  postList.get(position).getDate());
            //    intent.putExtra("commentCount", postList.get(position).getCommentCount());


             //   intent.putExtra("post", postList.get(position));

                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v){
        Intent intent = new Intent(getActivity(), createForumPostActivity.class);
        startActivity(intent);
    }
}