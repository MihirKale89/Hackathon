package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Adapters.CategoryResultTeamsAdapter;
import app.hackathon.csusm.hackathon.Adapters.ResultTeamsAdapter;
import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class CategoryTeamsResultActivity extends ActionBarActivity {

    public DatabaseHelper db;
    public ArrayList<JudgeTeamScore> judgeTeamScore_Items = new ArrayList<JudgeTeamScore>();
    public CategoryResultTeamsAdapter categoryResultTeamsAdapter;
    public ListView judge_score_category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_teams_result);
        db = new DatabaseHelper(getApplicationContext());
        Log.d("Teams Count", "Teams Count: " + db.getAllTeams().size());
        judgeTeamScore_Items = db.getAllJudgeTeamScores();
        categoryResultTeamsAdapter = new CategoryResultTeamsAdapter(getBaseContext(),R.layout.result_category_teams_list_item, judgeTeamScore_Items);
        judge_score_category_list = (ListView) findViewById(R.id.listView_Category_Result_Teams);
        judge_score_category_list.setAdapter(categoryResultTeamsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_teams_result, menu);
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

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
