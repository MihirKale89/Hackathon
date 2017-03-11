package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mihir on 16-Feb-15.
 */
public class Team implements Serializable{
    private String teamname, challenge_name;
    private ArrayList<Member> teamMembers = new ArrayList<Member>();
    private String created_at;
    int id, challenge_id_fk,grand_total, category_total;

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

    public Team() {
        this.grand_total = 0;
        this.category_total = 0;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamname + '\'' +
                '}';
    }

    public int getCategory_total() {
        return category_total;
    }

    public void setCategory_total(int category_total) {
        this.category_total = category_total;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public int getChallenge_id_fk() {
        return challenge_id_fk;
    }

    public void setChallenge_id_fk(int challenge_id_fk) {
        this.challenge_id_fk = challenge_id_fk;
    }

    public Team(String teamname) {
        this.teamname = teamname;
    }

    public String getTeamname() {
        return teamname;
    }

    public int getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(int grand_total) {
        this.grand_total = grand_total;
    }

    public ArrayList<Member> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public void setTeamMembers(ArrayList<Member> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
