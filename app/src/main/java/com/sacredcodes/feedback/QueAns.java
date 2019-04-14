package com.sacredcodes.feedback;

public class QueAns {

    String userEmail;
    String que1ans;
    String que2ans;
    String que3ans;
    String que4ans;
    String que5ans;
    String que6ans;

    public QueAns(String userEmail, String que1ans, String que2ans, String que3ans, String que4ans, String que5ans, String que6ans) {
        this.userEmail = userEmail;
        this.que1ans = que1ans;
        this.que2ans = que2ans;
        this.que3ans = que3ans;
        this.que4ans = que4ans;
        this.que5ans = que5ans;
        this.que6ans = que6ans;
    }

    public QueAns() {


    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getQue1ans() {
        return que1ans;
    }

    public void setQue1ans(String que1ans) {
        this.que1ans = que1ans;
    }

    public String getQue2ans() {
        return que2ans;
    }

    public void setQue2ans(String que2ans) {
        this.que2ans = que2ans;
    }

    public String getQue3ans() {
        return que3ans;
    }

    public void setQue3ans(String que3ans) {
        this.que3ans = que3ans;
    }

    public String getQue4ans() {
        return que4ans;
    }

    public void setQue4ans(String que4ans) {
        this.que4ans = que4ans;
    }

    public String getQue5ans() {
        return que5ans;
    }

    public void setQue5ans(String que5ans) {
        this.que5ans = que5ans;
    }

    public String getQue6ans() {
        return que6ans;
    }

    public void setQue6ans(String que6ans) {
        this.que6ans = que6ans;
    }
}
