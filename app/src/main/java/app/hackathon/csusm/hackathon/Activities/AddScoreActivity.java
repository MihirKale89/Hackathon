package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class AddScoreActivity extends ActionBarActivity {

    DatabaseHelper db;
    EditText scoreCategory, maxScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_score, menu);
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

    public void addScoreCategory(View view) {
        db = new DatabaseHelper(getApplicationContext());
        scoreCategory = (EditText) findViewById(R.id.editTextAddScoreCategory);
        maxScore = (EditText) findViewById(R.id.editTextAddTopScore);
        Score score = new Score(scoreCategory.getText().toString(), Integer.parseInt(maxScore.getText().toString()));
        long score_id = db.createScoreCategory(score);
        Intent intent2 = new Intent(this, ScoresActivity.class);
        startActivity(intent2);
        finish();
    }
}
