package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class AddTeamActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    DatabaseHelper db;
    EditText teamName;
    Spinner challenges_spinner;
    Challenge selected_challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        challenges_spinner = (Spinner) findViewById(R.id.spinnerChallenges);
        loadSpinnerData();

    }

    public void loadSpinnerData() {
        // database handler
        db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop down elements
        ArrayList<Challenge> challenges_list = db.getAllChallenges();

        // Creating adapter for spinner
        ArrayAdapter<Challenge> dataAdapter = new ArrayAdapter<Challenge>(this,
                android.R.layout.simple_spinner_item, challenges_list);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        challenges_spinner.setAdapter(dataAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_team, menu);
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

    public void addTeamObject(View view) {
        db = new DatabaseHelper(getApplicationContext());
        teamName = (EditText) findViewById(R.id.editTextAddTeam_name);
        selected_challenge = (Challenge)challenges_spinner.getSelectedItem();
        Team team = new Team(teamName.getText().toString());
        team.setChallenge_name(selected_challenge.getChallenge_name());
        team.setChallenge_id_fk(selected_challenge.getId());
        long team_id = db.createTeam(team);
        Intent intent2 = new Intent(this, TeamsActivity.class);
        startActivity(intent2);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
