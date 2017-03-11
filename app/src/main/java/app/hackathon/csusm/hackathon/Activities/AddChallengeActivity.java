package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class AddChallengeActivity extends ActionBarActivity {

    DatabaseHelper db;
    EditText challengeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_challenge, menu);
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

    public void addChallengeObject(View view) {
        db = new DatabaseHelper(getApplicationContext());
        challengeName = (EditText) findViewById(R.id.editTextAddChallenge_name);
        Challenge challenge = new Challenge(challengeName.getText().toString());
        long challenge_id = db.createChallenge(challenge);
        Intent intent2 = new Intent(this, ChallengesActivity.class);
        startActivity(intent2);
        finish();
    }
}
