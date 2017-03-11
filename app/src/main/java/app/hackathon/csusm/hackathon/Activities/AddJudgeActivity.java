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

public class AddJudgeActivity extends ActionBarActivity {

    private DatabaseHelper db;
    private EditText judgeName, judgeUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_judge);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_judge, menu);
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

    public void addJudgeObject(View view) {

        try {
            db = new DatabaseHelper(getApplicationContext());
            judgeName = (EditText) findViewById(R.id.editTextAddJudge_name);
            judgeUserId = (EditText) findViewById(R.id.editTextAddJudgeUsername);
            Judge judge = new Judge(judgeName.getText().toString());
            judge.setJudgeUserId(judgeUserId.getText().toString());
            judge.setJudgePassword("hackathon");
            long judge_id = db.createJudge(judge);
        }
        catch (SQLiteConstraintException exception) {
            Log.i("SQLITE ERROR::","On the next line");
            exception.printStackTrace();
            Toast.makeText(getApplicationContext(),"Please enter a unique judge id", Toast.LENGTH_LONG).show();
        }
        finally {

        }

        Intent intent2 = new Intent(this, JudgesActivity.class);
        startActivity(intent2);
        finish();
    }
}
