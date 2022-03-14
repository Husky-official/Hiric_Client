package models;

import java.io.Serializable;

public class CheckUserExistence implements Serializable {
    String memberName;
    int memberID;

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getMemberID() {
        return memberID;
    }
}
