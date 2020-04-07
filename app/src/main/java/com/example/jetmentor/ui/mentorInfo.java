package com.example.jetmentor.ui;

public class mentorInfo {

    private String name;
    private String company;
    private String position;

    public mentorInfo() {};

    public mentorInfo(String inName, String inCompany, String inPosition)
    {
        name = inName;
        company = inCompany;
        position = inPosition;
    }

    public String getName(){return name;}
    public String getCompany(){return company;}
    public String getPosition(){return position;}
    public void setName(String inName){name = inName;}
    public void setCompany(String inCompany){company = inCompany;}
    public void setPosition(String inPosition){position = inPosition;}
}
