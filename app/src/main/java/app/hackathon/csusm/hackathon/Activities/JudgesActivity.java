package app.hackathon.csusm.hackathon.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Adapters.JudgesAdapter;
import app.hackathon.csusm.hackathon.Adapters.TeamsAdapter;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class JudgesActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{


    public DatabaseHelper db;
    public ArrayList<Judge> judge_Items = new ArrayList<Judge>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judges);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        /*score_Items.add(new Judge("Judge 1"));
        score_Items.add(new Judge("Judge 2"));
        score_Items.add(new Judge("Judge 3"));
        score_Items.add(new Judge("Judge 4"));*/
        // Creating tags
       /* Judge judge1 = new Judge("Judge 1");
        Judge judge2 = new Judge("Judge 2");
        Judge judge3 = new Judge("Judge 3");
        Judge judge4 = new Judge("Judge 4");

        // Inserting tags in db
        long judge1_id = db.createJudge(judge1);
        long judge2_id = db.createJudge(judge2);
        long judge3_id = db.createJudge(judge3);
        long judge4_id = db.createJudge(judge4);*/

        Log.d("Judges Count", "Judges Count: " + db.getAllJudges().size());
        judge_Items = db.getAllJudges();

        ListView judges_list = (ListView) findViewById(R.id.listView_Judges);
        judges_list.setAdapter(new JudgesAdapter(context,R.layout.judges_list_item, judge_Items));
        judges_list.setOnItemClickListener(this);
        // Button editJudge = (Button) findViewById(R.id.buttonEditJudge);
        // editJudge.setOnClickListener(editJudgeListener);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("listView2", "listView:" + parent +
                ", view:" + view.getClass() +
                ", position:" + position);
    }

    @Override
    protected void onResume() {
        super.onResume();

        judge_Items = db.getAllJudges();

        ListView judges_list = (ListView) findViewById(R.id.listView_Judges);
        judges_list.setAdapter(new JudgesAdapter(getBaseContext(),R.layout.judges_list_item, judge_Items));
        judges_list.setOnItemClickListener(this);
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void addJudge(View view) {
        Intent intent = new Intent(this, AddJudgeActivity.class);
        startActivity(intent);
    }

    /*
    private View.OnClickListener editJudgeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View parentRow = (View) v.getParent();
            ListView listView = (ListView) parentRow.getParent();
            final int position = listView.getPositionForView(parentRow);
            Intent intent = new Intent(getBaseContext(), EditJudgeActivity.class);
            intent.putExtra("judge", (java.io.Serializable) score_Items.get(position));
            startActivity(intent);

        }
    };
    */

    /*public void editJudge(View view, int position) {
        Intent intent = new Intent(this, EditJudgeActivity.class);
        intent.putExtra("judge", (java.io.Serializable) score_Items.get(position));
        startActivity(intent);
    }*/

}
