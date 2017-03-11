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

import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class EditTeamActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    EditText editTextTeamName;
    DatabaseHelper db;
    Spinner challenges_spinner;
    Challenge selected_challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        editTextTeamName = (EditText) findViewById(R.id.editTextTeamName);
        Intent intent = getIntent();
        Team getTeam = (Team) intent.getSerializableExtra("team");
        //String teamName = intent.getStringExtra("team");
        editTextTeamName.setText(getTeam.getTeamname());
        challenges_spinner = (Spinner)findViewById(R.id.spinnerEditChallenge_FK);
        challenges_spinner.setOnItemSelectedListener(this);
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
        Intent intent = getIntent();
        Team getTeam = (Team) intent.getSerializableExtra("team");
        challenges_spinner.setAdapter(dataAdapter);
        challenges_spinner.setSelection(challenges_list.indexOf(getTeam.getChallenge_id_fk()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editTeam(View view) {
        editTextTeamName = (EditText) findViewById(R.id.editTextTeamName);
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        Challenge editChallenge;
        /*String teamName = intent.getStringExtra("team");
        int team_Id = intent.getIntExtra("team_id", 3);*/
        editChallenge = (Challenge) challenges_spinner.getSelectedItem();
        Team editTeam = (Team) intent.getSerializableExtra("team");
        // Team editTeam = new Team(teamName);
        // editTeam.setId(team_Id);
        editTeam.setTeamname(editTextTeamName.getText().toString());
        editTeam.setChallenge_id_fk(editChallenge.getId());
        editTeam.setChallenge_name(editChallenge.getChallenge_name());
        db.updateTeam(editTeam);
        Intent intent2 = new Intent(this, TeamsActivity.class);
        startActivity(intent2);
        finish();
    }

    public void deleteTeam(View view) {
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        Team delTeam = (Team) intent.getSerializableExtra("team");
        db.deleteTeam(delTeam.getId());
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
