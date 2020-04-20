package com.example.jetmentor.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ForumFragment extends Fragment implements View.OnClickListener{

    private ForumViewModel homeViewModel;
    private RecyclerView postsRecyclerView;
    private Button btnCreatePost;
    private Spinner postSort;
    private FirebaseFirestore db;
    private List<ForumPost> postList;
    private ForumAdapter forumAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);

        buildRecyclerView(root);
        setUpButton(root);
        setUpSpinner(root);
        return root;
    }

    private void setUpButton(View root) {
        btnCreatePost = (Button) root.findViewById(R.id.forum_create_post_btn);
        btnCreatePost.setOnClickListener(this);
    }

    private void setUpSpinner(View root) {
        postSort = (Spinner) root.findViewById(R.id.forum_sort_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.post_sorts, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postSort.setAdapter(spinnerAdapter);
        postSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        sortNewest();
                        forumAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        sortOldest();
                        forumAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        sortAZ();
                        forumAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        sortZA();
                        forumAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        sortRandom();
                        forumAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void sortNewest() {
        Collections.sort(postList, new Comparator<ForumPost>(){
            public int compare(ForumPost p1, ForumPost p2){
                return p2.getDate().compareTo(p1.getDate());
            }
        });
    }
    private void sortOldest() {
        Collections.sort(postList, new Comparator<ForumPost>(){
            public int compare(ForumPost p1, ForumPost p2){
                return p1.getDate().compareTo(p2.getDate());
            }
        });
    }
    private void sortAZ(){
        Collections.sort(postList, new Comparator<ForumPost>() {
            public int compare(ForumPost p1, ForumPost p2) {
               return p1.getTitle().compareToIgnoreCase(p2.getTitle());
            }
        });
    }
    private void sortZA() {
        Collections.sort(postList, new Comparator<ForumPost>() {
            public int compare(ForumPost p1, ForumPost p2) {
                return p2.getTitle().compareToIgnoreCase(p1.getTitle());
            }
        });
    }
    private void sortRandom() {
        Collections.shuffle(postList);
    }

    public void buildRecyclerView(View root){
        postsRecyclerView = root.findViewById(R.id.postsRecyclerView);
        postList = new ArrayList<>();
        forumAdapter = new ForumAdapter( root.getContext(), postList);
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
                                sortNewest();
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