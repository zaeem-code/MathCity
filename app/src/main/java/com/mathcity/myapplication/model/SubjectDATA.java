package com.mathcity.myapplication.model;

public class SubjectDATA {
    int totalattemps,totalFails,totalpass,FailurePercentage;

    public int getTotalattemps() {
        return totalattemps;
    }

    public void setTotalattemps(int totalattemps) {
        this.totalattemps = totalattemps;
    }

    public int getTotalFails() {
        return totalFails;
    }

    public void setTotalFails(int totalFails) {
        this.totalFails = totalFails;
    }

    public int getTotalpass() {
        return totalpass;
    }

    public void setTotalpass(int totalpass) {
        this.totalpass = totalpass;
    }

    public int getFailurePercentage() {
        return FailurePercentage;
    }

    public void setFailurePercentage(int failurePercentage) {
        FailurePercentage = failurePercentage;
    }
}
