package com.example.jetmentor;

public class connectInfo {

    private String docId, menteeId, mentorId, reqMessage, mentorEmail, menteeEmail;
    private int status;

    public connectInfo(){};

    public connectInfo(String inMenteeId, String inMentorId, String inMessage, int inStatus, String inMentorEmail, String inMenteeEmail)
    {
        docId = menteeId + mentorId;
        menteeId = inMenteeId;
        mentorId = inMentorId;
        reqMessage = inMessage;
        status = inStatus;
        mentorEmail = inMentorEmail;
        menteeEmail = inMenteeEmail;
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

    public void setMenteeEmail(String menteeEmail) {
        this.menteeEmail = menteeEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public void setReqMessage(String message) {
        this.reqMessage = message;
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

    public String getMenteeEmail() {
        return menteeEmail;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public String getReqMessage() {
        return reqMessage;
    }

}
