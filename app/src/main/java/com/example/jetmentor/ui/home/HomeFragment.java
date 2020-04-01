package com.example.jetmentor.ui.home;

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

import com.example.jetmentor.R;
import com.example.jetmentor.createForumPostActivity;
import com.example.jetmentor.openForumPostActivity;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private RecyclerView postsRecyclerView;
    private String mockTitles[], mockUsers[], mockDates[], mockCommentCounts[];
    private Button btnCreatePost;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
      /*  final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
*/
        buildRecyclerView(root);

        btnCreatePost = (Button) root.findViewById(R.id.forum_create_post_btn);
        btnCreatePost.setOnClickListener(this);
        return root;
    }

    public void buildRecyclerView(View root){
        postsRecyclerView = root.findViewById(R.id.postsRecyclerView);

        mockTitles = getResources().getStringArray(R.array.mockTitles);
        mockUsers = getResources().getStringArray(R.array.mockUsers);
        mockDates = getResources().getStringArray(R.array.mockDates);
        mockCommentCounts = getResources().getStringArray(R.array.mockCommentCounts);

        MyAdapter myAdapter = new MyAdapter( root.getContext(), mockTitles, mockUsers, mockDates, mockCommentCounts);
        postsRecyclerView.setAdapter(myAdapter);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), openForumPostActivity.class);
                intent.putExtra("title", mockTitles[position]);
                intent.putExtra("user", mockUsers[position]);
                intent.putExtra("date", mockDates[position]);
                intent.putExtra("commentCount", mockCommentCounts[position]);
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