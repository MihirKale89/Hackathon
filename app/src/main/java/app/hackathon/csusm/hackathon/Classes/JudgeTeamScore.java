package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;

/**
 * Created by Mihir on 01-Apr-15.
 */
public class JudgeTeamScore implements Serializable {
    private int id, achieved_score_jts;
    private String judge_user_id_fk, team_name_fk, score_category_fk, json_scores, created_at;

    public JudgeTeamScore() {
    }

    @Override
    public String toString() {
        return "JudgeTeamScore{" +
                "id=" + id +
                ", judge_user_id_fk='" + judge_user_id_fk + '\'' +
                ", team_name_fk='" + team_name_fk + '\'' +
                ", json_scores='" + json_scores + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }



    public JudgeTeamScore(int achieved_score_jts, String judge_user_id_fk, String team_name_fk, String score_category_fk, String json_scores) {
        this.achieved_score_jts = achieved_score_jts;
        this.judge_user_id_fk = judge_user_id_fk;
        this.team_name_fk = team_name_fk;
        this.score_category_fk = score_category_fk;
        this.json_scores = json_scores;
    }

    public JudgeTeamScore(String judge_user_id_fk, String team_name_fk, String json_scores) {
        this.judge_user_id_fk = judge_user_id_fk;
        this.team_name_fk = team_name_fk;
        this.json_scores = json_scores;
    }





    public int getAchieved_score_jts() {
        return achieved_score_jts;
    }

    public void setAchieved_score_jts(int achieved_score_jts) {
        this.achieved_score_jts = achieved_score_jts;
    }

    public String getScore_category_fk() {
        return score_category_fk;
    }

    public void setScore_category_fk(String score_category_fk) {
        this.score_category_fk = score_category_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudge_user_id_fk() {
        return judge_user_id_fk;
    }

    public void setJudge_user_id_fk(String judge_user_id_fk) {
        this.judge_user_id_fk = judge_user_id_fk;
    }

    public String getTeam_name_fk() {
        return team_name_fk;
    }

    public void setTeam_name_fk(String team_name_fk) {
        this.team_name_fk = team_name_fk;
    }

    public String getJson_scores() {
        return json_scores;
    }

    public void setJson_scores(String json_scores) {
        this.json_scores = json_scores;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
