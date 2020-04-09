package com.example.jetmentor.ui;

public class mentorInfo {

    private String userId, name, company, position;
    private double yearsOfExperience;
    private boolean available;

    public mentorInfo() {};

    public mentorInfo(String inName, String inCompany, String inPosition)
    {
        userId = "need it";
        name = inName;
        company = inCompany;
        position = inPosition;
        yearsOfExperience = 0;
        available = false;
    }

    public mentorInfo(String inUserId, String inName, String inCompany, String inPosition, double inYearsOfExperience, boolean inAvailable)
    {
        userId = inUserId;
        name = inName;
        company = inCompany;
        position = inPosition;
        yearsOfExperience = 0;
        available = inAvailable;
    }

    public String getUserId(){return userId;}
    public String getName(){return name;}
    public String getCompany(){return company;}
    public String getPosition(){return position;}
    public double getYearsOfExperience(){return yearsOfExperience;}
    public boolean getAvailable(){return available;}
    public void setUserId(String inUserId){userId = inUserId;}
    public void setName(String inName){name = inName;}
    public void setCompany(String inCompany){company = inCompany;}
    public void setPosition(String inPosition){position = inPosition;}
    public void setYearsOfExperience(double inYearsOfExperience){yearsOfExperience = inYearsOfExperience;}
    public void setAvailable(boolean inAvailable){available = inAvailable;}
}
