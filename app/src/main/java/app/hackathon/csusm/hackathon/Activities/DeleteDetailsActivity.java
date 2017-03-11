package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class DeleteDetailsActivity extends ActionBarActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_details);
        db = new DatabaseHelper(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_details, menu);
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

    public void delete_challenges(View view) {
        db.deleteChallenges();
        Toast.makeText(getApplicationContext(), "Deleted All Challenges", Toast.LENGTH_LONG).show();
    }

    public void delete_teams(View view) {
        db.deleteTeams();
        Toast.makeText(getApplicationContext(), "Deleted All Teams", Toast.LENGTH_LONG).show();
    }

    public void delete_score_categories(View view) {
        db.deleteScoreCategories();
        Toast.makeText(getApplicationContext(), "Deleted All Score Categories", Toast.LENGTH_LONG).show();
    }

    public void delete_judges(View view) {
        db.deleteJudges();
        Toast.makeText(getApplicationContext(), "Deleted All Judges", Toast.LENGTH_LONG).show();
    }


    public void delete_judge_scores(View view) {
        db.deleteJudgeScores();
        Toast.makeText(getApplicationContext(), "Deleted All Judge Scores", Toast.LENGTH_LONG).show();
    }

    public void delete_all_details(View view) {
        db.deleteChallenges();
        db.deleteTeams();
        db.deleteScoreCategories();
        db.deleteJudges();
        db.deleteJudgeScores();
        Toast.makeText(getApplicationContext(), "Deleted All details", Toast.LENGTH_LONG).show();
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
