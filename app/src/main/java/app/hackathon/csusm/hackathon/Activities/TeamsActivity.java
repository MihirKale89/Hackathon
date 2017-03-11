package app.hackathon.csusm.hackathon.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Adapters.TeamsAdapter;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class TeamsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    public DatabaseHelper db;
    public ArrayList<Team> team_Items = new ArrayList<Team>();
    public TeamsAdapter teamsAdapter;
    public Spinner sortBySpinner;
    public ListView teams_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        sortBySpinner = (Spinner) findViewById(R.id.spinnerTeamSortSelector);
        loadSpinnerData();
        sortBySpinner.setOnItemSelectedListener(this);

        /*
        team_Items.add(new Team("Team 21"));
        team_Items.add(new Team("Hiller Hackers"));
        team_Items.add(new Team("Fire Doctors"));
        team_Items.add(new Team("HackATack"));
        team_Items.add(new Team("Pc Gang - Too Strong"));
        team_Items.add(new Team("Ja-va-va-voom"));
        team_Items.add(new Team("Green Beans"));
        team_Items.add(new Team("Global Cat-Tastrophe"));
        */
        Log.d("Teams Count", "Teams Count: " + db.getAllTeams().size());
        team_Items = db.getAllTeams();
        teamsAdapter = new TeamsAdapter(context,R.layout.teams_list_item, team_Items);
        teams_list = (ListView) findViewById(R.id.listView_Teams);
        teams_list.setAdapter(teamsAdapter);
        teams_list.setOnItemClickListener(this);
    }

    public void loadSpinnerData() {

        // Spinner Drop down elements
        ArrayList<String> sort_by_list = new ArrayList<String>();
        sort_by_list.add("Team names");
        sort_by_list.add("Challenges");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sort_by_list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sortBySpinner.setAdapter(dataAdapter);
    }

    public void sortByNames(){
        team_Items = db.getAllTeamsByTeamNames();
        Log.d("Teams by team names",">>>>>>>"+team_Items.toString());
        teamsAdapter = new TeamsAdapter(getBaseContext(),R.layout.teams_list_item, team_Items);
        teams_list = (ListView) findViewById(R.id.listView_Teams);
        teams_list.setAdapter(teamsAdapter);
        teams_list.setOnItemClickListener(this);
        teamsAdapter.notifyDataSetChanged();
        //this.notifyAll();
        //teams_list.deferNotifyDataSetChanged();
    }

    public void sortByChallenges(){
        team_Items = db.getAllTeamsByChallengeNames();
        Log.d("Teams by challenges",">>>>>>>"+team_Items.toString());
        teamsAdapter = new TeamsAdapter(getBaseContext(),R.layout.teams_list_item, team_Items);
        teams_list = (ListView) findViewById(R.id.listView_Teams);
        teams_list.setAdapter(teamsAdapter);
        teams_list.setOnItemClickListener(this);
        teamsAdapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
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

    @Override
    protected void onResume() {
        super.onResume();

        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        sortBySpinner = (Spinner) findViewById(R.id.spinnerTeamSortSelector);
        loadSpinnerData();
        sortBySpinner.setOnItemSelectedListener(this);

        /*
        team_Items.add(new Team("Team 21"));
        team_Items.add(new Team("Hiller Hackers"));
        team_Items.add(new Team("Fire Doctors"));
        team_Items.add(new Team("HackATack"));
        team_Items.add(new Team("Pc Gang - Too Strong"));
        team_Items.add(new Team("Ja-va-va-voom"));
        team_Items.add(new Team("Green Beans"));
        team_Items.add(new Team("Global Cat-Tastrophe"));
        */
        Log.d("Teams Count", "Teams Count: " + db.getAllTeams().size());
        team_Items = db.getAllTeams();
        teamsAdapter = new TeamsAdapter(context,R.layout.teams_list_item, team_Items);
        teams_list = (ListView) findViewById(R.id.listView_Teams);
        teams_list.setAdapter(teamsAdapter);
        teams_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.listView_Teams){
            Log.d("listView2", "listView:" + parent +
                    ", view:" + view.getClass() +
                    ", position:" + position);
        }
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void addTeam(View view) {
        Intent intent = new Intent(this, AddTeamActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Spinner sortBySpinner = (Spinner) findViewById(R.id.spinnerTeamSortSelector);

            switch (sortBySpinner.getSelectedItemPosition()){
                case 0:
                    /*team_Items = db.getAllTeamsByTeamNames();
                    teams_list.setAdapter(new TeamsAdapter(getBaseContext(),R.layout.teams_list_item, team_Items));
                    teams_list.setOnItemClickListener(this);
                    */
                    Log.d("Spinner item", " >>>>>>>"+sortBySpinner.getSelectedItem());
                    sortByNames();
                    break;
                case 1:
                    /*team_Items = db.getAllTeamsByChallengeNames();
                    teams_list.setAdapter(new TeamsAdapter(getBaseContext(),R.layout.teams_list_item, team_Items));
                    teams_list.setOnItemClickListener(this);
                    teams_list.deferNotifyDataSetChanged();*/
                    Log.d("Spinner item", " >>>>>>>"+sortBySpinner.getSelectedItem());
                    sortByChallenges();
                    break;
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
