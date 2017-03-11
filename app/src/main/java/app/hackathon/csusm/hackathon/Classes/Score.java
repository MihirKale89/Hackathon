package app.hackathon.csusm.hackathon.Classes;

import java.io.Serializable;

/**
 * Created by Mihir on 28-Mar-15.
 */
public class Score implements Serializable{
    private String metric, created_at;
    private int top_score,achieved_score,id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Score() {
        //this.achieved_score = 0;
    }

    public Score(String metric, int top_score) {
        this.metric = metric;
        this.top_score = top_score;
        //this.achieved_score = 0;
    }

    public Score(String metric, String created_at, int top_score, int achieved_score, int id) {
        this.metric = metric;
        this.created_at = created_at;
        this.top_score = top_score;
        this.achieved_score = achieved_score;
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getAchieved_score() {
        return achieved_score;
    }

    public void setAchieved_score(int achieved_score) {
        this.achieved_score = achieved_score;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public int getTop_score() {
        return top_score;
    }

    public void setTop_score(int top_score) {
        this.top_score = top_score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "metric='" + metric + '\'' +
                ", created_at='" + created_at + '\'' +
                ", top_score=" + top_score +
                ", achieved_score=" + achieved_score +
                ", id=" + id +
                '}';
    }


}
