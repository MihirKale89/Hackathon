package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;

/**
 * Created by Mihir on 22-Apr-15.
 */
public class CategoryResult implements Serializable, Comparable {
    String teamName;
    int id, total_score;
    private String created_at;

    public CategoryResult() {
    }

    public CategoryResult(String teamName, int total_score) {
        this.teamName = teamName;
        this.total_score = total_score;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    @Override
    public int compareTo(Object another) {
        int compareTotal = ((CategoryResult)another).getTotal_score();
        /* For Ascending order*/
        //return this.getTotal_score()-compareTotal;

        /* For Descending order do like this */
        return compareTotal-this.getTotal_score();
    }
}
