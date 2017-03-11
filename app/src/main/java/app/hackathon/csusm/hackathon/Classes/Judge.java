package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;

/**
 * Created by Mihir on 02-Mar-15.
 */
public class Judge implements Serializable{

    private String name, judgeUserId, judgePassword;
    private String created_at;
    int id, status;

    public int getId() {
        return id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Judge() {
    }

    public Judge(String name) {
        this.name = name;
    }

    public String getJudgeUserId() {
        return judgeUserId;
    }

    public void setJudgeUserId(String judgeUserId) {
        this.judgeUserId = judgeUserId;
    }

    public String getJudgePassword() {
        return judgePassword;
    }

    public void setJudgePassword(String judgePassword) {
        this.judgePassword = judgePassword;
    }
}
