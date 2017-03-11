package app.hackathon.csusm.hackathon.JudgesActivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Activities.MenuActivity;
import app.hackathon.csusm.hackathon.Adapters.GradeTeamsAdapter;
import app.hackathon.csusm.hackathon.Adapters.TeamsAdapter;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class ViewTeamsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{


    public DatabaseHelper db;
    public ArrayList<Team> team_Items = new ArrayList<Team>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        Log.d("Teams Count", "Teams Count: " + db.getAllTeams().size());
        team_Items = db.getAllTeams();

        ListView teams_list = (ListView) findViewById(R.id.listView_Grade_Teams);
        teams_list.setAdapter(new GradeTeamsAdapter(context,R.layout.grade_teams_list_item, team_Items));
        teams_list.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_teams, menu);
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

        team_Items = db.getAllTeams();

        ListView teams_list = (ListView) findViewById(R.id.listView_Grade_Teams);
        teams_list.setAdapter(new GradeTeamsAdapter(getBaseContext(),R.layout.grade_teams_list_item, team_Items));
        teams_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void showJudgesMenu(View view) {
        Intent intent = new Intent(this, JudgesMenuActivity.class);
        startActivity(intent);
    }
}
