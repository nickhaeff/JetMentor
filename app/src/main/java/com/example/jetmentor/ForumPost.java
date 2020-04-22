package com.example.jetmentor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForumPost {
    private String title;
    private String body;
    private String user;
    private String date;
    private int commentCount;
    private int id;
    private List<String> comments;

    public ForumPost(){}

    public ForumPost(String title, String body){
        this.title = title;
        this.body = body;
        user = "phil";

        date = new SimpleDateFormat("MM/dd").format(new Date());
    }



    public ForumPost(String title, String body, String user, int commentCount, int id){
        this.title = title;
        this.body = body;
        this.user = user;
        this.commentCount = commentCount;
        this.id = id;
        this.comments = new ArrayList<>();

        date = new SimpleDateFormat("MM/dd").format(new Date());
    }

    public String getTitle() {return title;}
    public String getBody() {return body;}
    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}
    public int getCommentCount() {return commentCount;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getDate() {return date;}
    public List<String> getComments() {return this.comments;}
}
