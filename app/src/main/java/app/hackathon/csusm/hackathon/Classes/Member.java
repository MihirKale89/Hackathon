package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;

/**
 * Created by Mihir on 16-Feb-15.
 */
public class Member implements Serializable{
    private String memberName,schoolName;

    public String getMemberName() {
        return memberName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
