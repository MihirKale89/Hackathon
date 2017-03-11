package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mihir on 16-Feb-15.
 */
public class Challenge implements Serializable{
    private String challenge_name;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private String created_at;
    int id, status;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
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

    public Challenge() {
    }

    @Override
    public String toString() {
        return challenge_name;
    }

    public Challenge(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
