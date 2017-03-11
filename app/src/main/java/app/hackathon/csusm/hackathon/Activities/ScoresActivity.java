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
import android.widget.ListView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Adapters.JudgesAdapter;
import app.hackathon.csusm.hackathon.Adapters.ScoreCategoryAdapter;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class ScoresActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    public DatabaseHelper db;
    public ArrayList<Score> score_Items = new ArrayList<Score>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        //        score_Items.add(new Score("Appropriateness to theme",40));
        //        score_Items.add(new Score("User Experience and Functionality",20));
        //        score_Items.add(new Score("Originality and impact",20));
        //        score_Items.add(new Score("Technical Difficulty",20));
        Log.d("Scores Count", "Scores Count: " + db.getAllScoreCategories().size());

        score_Items = db.getAllScoreCategories();

        ListView scores_list = (ListView) findViewById(R.id.listView_Score_Categories);
        scores_list.setAdapter(new ScoreCategoryAdapter(context,R.layout.scores_list_item, score_Items));
        scores_list.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("listView2", "listView:" + parent +
                ", view:" + view.getClass() +
                ", position:" + position);
    }

    @Override
    protected void onResume() {
        super.onResume();

        score_Items = db.getAllScoreCategories();

        ListView scores_list = (ListView) findViewById(R.id.listView_Score_Categories);
        scores_list.setAdapter(new ScoreCategoryAdapter(getBaseContext(),R.layout.scores_list_item, score_Items));
        scores_list.setOnItemClickListener(this);
    }

    public void addScoreCategory(View view) {
        Intent intent = new Intent(this, AddScoreActivity.class);
        startActivity(intent);
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
