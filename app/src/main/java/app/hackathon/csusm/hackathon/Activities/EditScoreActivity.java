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

public class EditScoreActivity extends ActionBarActivity {

    EditText editTextScoreCategory, editTextMaxScore;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_score);
        editTextScoreCategory = (EditText) findViewById(R.id.editTextEditScoreCategory);
        editTextMaxScore = (EditText) findViewById(R.id.editTextEditTopScore);
        Intent intent = getIntent();
        Score getScore = (Score) intent.getSerializableExtra("score_category");
        // String judgeName = intent.getStringExtra("judge");
        // int judge_Id = intent.getIntExtra("judge_id", 3);
        editTextScoreCategory.setText(getScore.getMetric());
        editTextMaxScore.append(""+getScore.getTop_score());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_score, menu);
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

    public void deleteScoreCategory(View view) {
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        //String judgeName = intent.getStringExtra("judge");
        //int judge_Id = intent.getIntExtra("judge_id", 3);
        //Judge delJudge = new Judge(judgeName);
        Score delScore = (Score) intent.getSerializableExtra("score_category");
        db.deleteScoreCategory(delScore.getId());
        Intent intent2 = new Intent(this, ScoresActivity.class);
        startActivity(intent2);
        finish();
    }

    public void editScoreCategory(View view) {
        editTextScoreCategory = (EditText) findViewById(R.id.editTextEditScoreCategory);
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        /*String judgeName = intent.getStringExtra("judge");
        int judge_Id = intent.getIntExtra("judge_id", 3);*/
        Score editScore = (Score) intent.getSerializableExtra("score_category");
        // Judge editJudge = new Judge(judgeName);
        // editJudge.setId(judge_Id);
        editScore.setMetric(editTextScoreCategory.getText().toString());
        editScore.setTop_score(Integer.parseInt(editTextMaxScore.getText().toString()));
        db.updateScoreCategory(editScore);
        Intent intent2 = new Intent(this, ScoresActivity.class);
        startActivity(intent2);
        finish();
    }
}
