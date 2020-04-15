package com.example.jetmentor;

public class connectInfo {

    private String docId, menteeId, mentorId, message;
    private int status;

    public connectInfo(){};

    public connectInfo(String inMenteeId, String inMentorId, String inMessage, int inStatus)
    {
        docId = menteeId + mentorId;
        menteeId = inMenteeId;
        mentorId = inMentorId;
        message = inMessage;
        status = inStatus;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setMenteeId(String menteeId) {
        this.menteeId = menteeId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDocId() {
        return docId;
    }

    public int getStatus() {
        return status;
    }

    public String getMenteeId() {
        return menteeId;
    }

    public String getMentorId() {
        return mentorId;
    }

    public String getMessage() {
        return message;
    }

}
