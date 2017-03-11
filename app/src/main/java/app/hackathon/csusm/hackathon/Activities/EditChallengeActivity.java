package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class EditChallengeActivity extends ActionBarActivity {

    EditText editTextChallengeName;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_challenge);
        editTextChallengeName = (EditText) findViewById(R.id.editTextChallengeName);
        Intent intent = getIntent();
        Challenge getChallenge = (Challenge) intent.getSerializableExtra("challenge");
        // String challengeName = intent.getStringExtra("challenge");
        editTextChallengeName.setText(getChallenge.getChallenge_name());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_challenge, menu);
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

    public void deleteChallenge(View view) {
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        Challenge delChallenge = (Challenge) intent.getSerializableExtra("challenge");
        db.deleteChallenge(delChallenge.getId());
        Intent intent2 = new Intent(this, ChallengesActivity.class);
        startActivity(intent2);
        finish();
    }

    public void editChallenge(View view) {
        editTextChallengeName = (EditText) findViewById(R.id.editTextChallengeName);
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        /*String challengeName = intent.getStringExtra("challenge");
        int challenge_Id = intent.getIntExtra("challenge_id", 3);*/
        Challenge editChallenge = (Challenge) intent.getSerializableExtra("challenge");
        // Challenge editChallenge = new Challenge(challengeName);
        // editChallenge.setId(challenge_Id);
        editChallenge.setChallenge_name(editTextChallengeName.getText().toString());
        db.updateChallenge(editChallenge);
        Intent intent2 = new Intent(this, ChallengesActivity.class);
        startActivity(intent2);
        finish();
    }
}
