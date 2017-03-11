package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mihir on 28-Mar-15.
 */
public class Score_set implements Serializable{
    private ArrayList<Score> scores_list = new ArrayList<Score>();
    int id, team_id;

    public Score_set() {
    }

    public ArrayList<Score> getScores_list() {
        return scores_list;
    }

    public void setScores_list(ArrayList<Score> scores_list) {
        this.scores_list = scores_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
