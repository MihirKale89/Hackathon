package app.hackathon.csusm.hackathon.JudgesActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.hackathon.csusm.hackathon.Activities.TeamsActivity;
import app.hackathon.csusm.hackathon.Adapters.GradeScoreCategoryAdapter;
import app.hackathon.csusm.hackathon.Adapters.ScoreCategoryAdapter;
import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class GradeTeamActivity extends ActionBarActivity {

    public DatabaseHelper db;
    public ArrayList<Score> score_Items;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public String userId, teamName;
    //public int previousTotal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_team);
        Context context = getApplicationContext();
        Intent intent = getIntent();
        Team gradeTeam = (Team) intent.getSerializableExtra("gradeTeam");
        Log.d("Old grand total for"+gradeTeam.getTeamname()," ::::: "+gradeTeam.getGrand_total());
        TextView textViewGradeTeam = (TextView) findViewById(R.id.textViewJudge_Grade_Team);
        textViewGradeTeam.append("" + gradeTeam.getTeamname());
        db = new DatabaseHelper(getApplicationContext());
        Log.d("Scores Count", "Scores Count: " + db.getAllScoreCategories().size());

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        /*String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            userId = prefs.getString("user_id", "No user_id defined");//"No name defined" is the default value.
        }*/
        ArrayList<JudgeTeamScore> judgeTeamScores = new ArrayList<JudgeTeamScore>();
        userId = prefs.getString("user_id", "No user id defined");
        teamName = gradeTeam.getTeamname();
        if (db.existsJudgeTeamScore(userId, teamName)){
            judgeTeamScores = db.getSpecificJudgeTeamScores(userId, teamName);
            for (JudgeTeamScore judgeTeamScore : judgeTeamScores) {
                String jsonScores = judgeTeamScore.getJson_scores();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Score>>() {
                }.getType();
                score_Items = gson.fromJson(jsonScores, type);

                Log.d("judgeTeamScore", ">>>><<<<<" + judgeTeamScore.toString());
            }
        }
        else {
            score_Items = db.getAllScoreCategories();
        }




        ListView scores_list = (ListView) findViewById(R.id.listView_Grade_Score_Categories);
        Log.d("watching score items",">>>><<<<<"+score_Items.toString());
        scores_list.setAdapter(new GradeScoreCategoryAdapter(context,R.layout.judge_scores_list_item, score_Items));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<JudgeTeamScore> judgeTeamScores = new ArrayList<JudgeTeamScore>();
        if (db.existsJudgeTeamScore(userId, teamName)){
            judgeTeamScores = db.getSpecificJudgeTeamScores(userId, teamName);
            for (JudgeTeamScore judgeTeamScore : judgeTeamScores) {
                String jsonScores = judgeTeamScore.getJson_scores();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Score>>() {
                }.getType();
                score_Items = gson.fromJson(jsonScores, type);

                Log.d("judgeTeamScore", ">>>><<<<<" + judgeTeamScore.toString());
            }
        }
        else {
            score_Items = db.getAllScoreCategories();
        }

        ListView scores_list = (ListView) findViewById(R.id.listView_Grade_Score_Categories);
        scores_list.setAdapter(new GradeScoreCategoryAdapter(getApplicationContext(),R.layout.judge_scores_list_item, score_Items));
    }

    public void saveScores(View view) {
        //setStringArrayPref(getBaseContext(), "judgeTeam", score_Items);
        ArrayList<JudgeTeamScore> judgeTeamScores = new ArrayList<JudgeTeamScore>();
        db = new DatabaseHelper(getApplicationContext());
        Gson gson = new Gson();
        int newTotal = 0;
        int previousTotal = 0;
        String jsonScore_Items = gson.toJson(GradeScoreCategoryAdapter.getScore_Items());
        Log.d("Saving ...","jsonScore_Items = " + jsonScore_Items);
        //JudgeTeamScore judgeTeamScore;
        if (db.existsJudgeTeamScore(userId, teamName)){
            judgeTeamScores = db.getSpecificJudgeTeamScores(userId, teamName);
            //Gson gson = new Gson();
            for(JudgeTeamScore judgeTeamScore : judgeTeamScores) {
                Type type = new TypeToken<List<Score>>() {}.getType();
                score_Items = gson.fromJson(judgeTeamScore.getJson_scores(), type);
                if (previousTotal == 0) {
                    for (Score s : score_Items) {
                        previousTotal += s.getAchieved_score();
                    }
                }
                // judgeTeamScore.setJson_scores(jsonScore_Items);
                // db.updateJudgeTeamScore(judgeTeamScore);
            }
            for(JudgeTeamScore judgeTeamScore : judgeTeamScores) {
                Type type = new TypeToken<List<Score>>() {}.getType();
                score_Items = gson.fromJson(jsonScore_Items, type);
                for (Score s : score_Items) {
                        if(judgeTeamScore.getScore_category_fk().equals(s.getMetric())){
                            judgeTeamScore.setAchieved_score_jts(s.getAchieved_score());
                        }
                }

                judgeTeamScore.setJson_scores(jsonScore_Items);
                db.updateJudgeTeamScore(judgeTeamScore);
            }
        }
        else{
            Type type = new TypeToken<List<Score>>() {}.getType();
            score_Items = gson.fromJson(jsonScore_Items, type);
            for(Score s : score_Items) {
                JudgeTeamScore judgeTeamScore = new JudgeTeamScore(s.getAchieved_score(), userId, teamName, s.getMetric(), jsonScore_Items);
                Log.d("Saving judgeTeamScore", ">>>>" + judgeTeamScore.toString());
                long judgeTeamScore_id = db.createJudgeTeamScore(judgeTeamScore);
            }
        }
        Intent intent = getIntent();
        Team gradeTeam = (Team) intent.getSerializableExtra("gradeTeam");

        for(Score s : GradeScoreCategoryAdapter.getScore_Items()){
           newTotal += s.getAchieved_score();
        }
        Log.d("Displaying previous total for "+gradeTeam.getTeamname()," >>>>>>"+previousTotal );
        gradeTeam.setGrand_total((gradeTeam.getGrand_total() - previousTotal) + newTotal);
        db.updateTeam(gradeTeam);
        Log.d("Displaying new total for "+gradeTeam.getTeamname()," >>>>>>"+ newTotal);
        previousTotal = 0;
        newTotal = 0;
        Log.d("New grand total for"+gradeTeam.getTeamname()," ::::: "+gradeTeam.getGrand_total());
        Intent intent2 = new Intent(this, ViewTeamsActivity.class);
        startActivity(intent2);
    }

    public void showJudgeMenu(View view) {
        Intent intent = new Intent(this, JudgesMenuActivity.class);
        startActivity(intent);
        finish();
    }

    /*public static void setStringArrayPref(Context context, String key, ArrayList<Score> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray scoresArray = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            scoresArray.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, scoresArray.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }*/
}
