package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class EditJudgeActivity extends ActionBarActivity {

    EditText editTextJudgeName, editTextJudgeUserId;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_judge);
        editTextJudgeName = (EditText) findViewById(R.id.editTextEditJudgeName);
        editTextJudgeUserId = (EditText) findViewById(R.id.editTextEditJudgeUserId);
        Intent intent = getIntent();
        Judge getJudge = (Judge) intent.getSerializableExtra("judge");
        // String judgeName = intent.getStringExtra("judge");
        // int judge_Id = intent.getIntExtra("judge_id", 3);
        editTextJudgeName.setText(getJudge.getName());
        editTextJudgeUserId.setText(getJudge.getJudgeUserId());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_judge, menu);
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

    public void deleteJudge(View view) {
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        //String judgeName = intent.getStringExtra("judge");
        //int judge_Id = intent.getIntExtra("judge_id", 3);
        //Judge delJudge = new Judge(judgeName);
        Judge delJudge = (Judge) intent.getSerializableExtra("judge");
        db.deleteJudge(delJudge.getId());
        Intent intent2 = new Intent(this, JudgesActivity.class);
        startActivity(intent2);
        finish();

    }


    public void editJudge(View view) {

        try {
            editTextJudgeName = (EditText) findViewById(R.id.editTextEditJudgeName);
            db = new DatabaseHelper(getApplicationContext());
            Intent intent = getIntent();
            /*String judgeName = intent.getStringExtra("judge");
            int judge_Id = intent.getIntExtra("judge_id", 3);*/
            Judge editJudge = (Judge) intent.getSerializableExtra("judge");
            // Judge editJudge = new Judge(judgeName);
            // editJudge.setId(judge_Id);
            editJudge.setName(editTextJudgeName.getText().toString());
            editJudge.setJudgeUserId(editTextJudgeUserId.getText().toString());
            db.updateJudge(editJudge);
        }
        catch (SQLiteConstraintException exception) {
            Log.i("SQLITE ERROR::", "On the next line");
            exception.printStackTrace();
            Toast.makeText(getApplicationContext(), "Please enter a unique judge id", Toast.LENGTH_LONG).show();
        }
        finally {

        }
        Intent intent2 = new Intent(this, JudgesActivity.class);
        startActivity(intent2);
        finish();
    }
}
