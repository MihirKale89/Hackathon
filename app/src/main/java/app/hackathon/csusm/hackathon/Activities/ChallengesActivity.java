package app.hackathon.csusm.hackathon.Activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Adapters.ChallengesAdapter;
import app.hackathon.csusm.hackathon.Adapters.JudgesAdapter;
import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class ChallengesActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    public DatabaseHelper db;
    public ArrayList<Challenge> challenge_Items = new ArrayList<Challenge>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());

        // challenge_Items.add(new Challenge("Climate Change"));
        // challenge_Items.add(new Challenge("Natural Disaster"));
        Log.d("Challenges Count", "Challenges Count: " + db.getAllChallenges().size());
        challenge_Items = db.getAllChallenges();

        ListView challenges_list = (ListView) findViewById(R.id.listView_Challenges);
        challenges_list.setAdapter(new ChallengesAdapter(context, R.layout.challenges_list_item, challenge_Items));
        challenges_list.setOnItemClickListener(this);
        //challenges_list.setListAdapter(new ChallengesAdapter(context, R.layout.challenges_list_item, challenge_Items));

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("listView2", "listView:" + parent +
                ", view:" + view.getClass() +
                ", position:" + position);
    }

    @Override
    protected void onResume() {
        super.onResume();

        challenge_Items = db.getAllChallenges();

        ListView challenges_list = (ListView) findViewById(R.id.listView_Challenges);
        challenges_list.setAdapter(new ChallengesAdapter(getBaseContext(),R.layout.challenges_list_item, challenge_Items));
        challenges_list.setOnItemClickListener(this);
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void addChallenge(View view) {
        Intent intent = new Intent(this, AddChallengeActivity.class);
        startActivity(intent);
    }
}
