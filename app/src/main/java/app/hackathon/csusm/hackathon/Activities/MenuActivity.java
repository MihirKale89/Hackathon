package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.hackathon.csusm.hackathon.R;

public class MenuActivity extends ActionBarActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

    public void showChallenges(View view) {
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
    }

    public void showTeams(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        startActivity(intent);
    }

    public void showJudges(View view) {
        Intent intent = new Intent(this, JudgesActivity.class);
        startActivity(intent);
    }

    public void showScoreCategories(View view) {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    public void showLogIn(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void showTeamsResult(View view) {
        Intent intent = new Intent(this, TeamsResultActivity.class);
        startActivity(intent);
    }

    public void showCategoryTeamsResult(View view) {
        Intent intent = new Intent(this, CategoryTeamsResultActivity.class);
        startActivity(intent);
    }

    public void showCategoryRank(View view) {
        Intent intent = new Intent(this, CategoryRankActivity.class);
        startActivity(intent);
    }

    public void showDeleteDetails(View view) {
        Intent intent = new Intent(this, DeleteDetailsActivity.class);
        startActivity(intent);
    }
}
