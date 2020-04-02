package com.example.jetmentor;

import java.text.SimpleDateFormat;

public class ForumPost {
    private String title;
    private String body;
    private String user;
    private String date; //make SimpleDateFormat
    private int commentCount;
    private int id;

    public ForumPost(){}

    public ForumPost(String title, String body){
        this.title = title;
        this.body = body;
        date = "4/1";
        user = "phil";
    }

    public ForumPost(String title, String body, String user, int commentCount, int id){
        this.title = title;
        this.body = body;
        this.user = user;
        this.commentCount = commentCount;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getCommentCount() {
        return commentCount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {return date; }
}
