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

import app.hackathon.csusm.hackathon.Adapters.CategoryResultRankAdapter;
import app.hackathon.csusm.hackathon.Adapters.CategoryResultTeamsAdapter;
import app.hackathon.csusm.hackathon.Adapters.TeamsAdapter;
import app.hackathon.csusm.hackathon.Classes.CategoryResult;
import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.R;

public class CategoryRankActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    public DatabaseHelper db;
    public CategoryResultRankAdapter categoryResultRankAdapter;
    public ArrayList<CategoryResult> categoryResult_Items = new ArrayList<CategoryResult>();
    public Spinner sortBySpinner;
    public ListView categoryResultRankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_rank);
        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        sortBySpinner = (Spinner) findViewById(R.id.spinnerTeamSortRankSelector);
        loadSpinnerData();
        sortBySpinner.setOnItemSelectedListener(this);

        Log.d("Teams Count", "Teams Count: " + db.getAllTeams().size());
        categoryResult_Items = db.getCategoryResults(sortBySpinner.getSelectedItem().toString());
        Log.i("Checking rank "," >>>>> "+categoryResult_Items.toString());
        categoryResultRankAdapter = new CategoryResultRankAdapter(context,R.layout.result_categoryrank_list_item, categoryResult_Items);
        categoryResultRankList = (ListView) findViewById(R.id.listView_Teams_Rank);
        categoryResultRankList.setAdapter(categoryResultRankAdapter);
        categoryResultRankList.setOnItemClickListener(this);
    }

    public void loadSpinnerData() {

        // Spinner Drop down elements
        ArrayList<String> sort_by_list = new ArrayList<String>();
        ArrayList<Score> scores_Items = db.getAllScoreCategories();
        for (Score s : scores_Items){
            sort_by_list.add(s.getMetric());
        }
        sort_by_list.add("Grand Total");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sort_by_list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sortBySpinner.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Context context = getApplicationContext();
        db = new DatabaseHelper(getApplicationContext());
        sortBySpinner = (Spinner) findViewById(R.id.spinnerTeamSortRankSelector);
        loadSpinnerData();
        sortBySpinner.setOnItemSelectedListener(this);

        categoryResult_Items = db.getCategoryResults(sortBySpinner.getSelectedItem().toString());
        categoryResultRankAdapter = new CategoryResultRankAdapter(context,R.layout.result_categoryrank_list_item, categoryResult_Items);
        categoryResultRankList = (ListView) findViewById(R.id.listView_Teams_Rank);
        categoryResultRankList.setAdapter(categoryResultRankAdapter);
        categoryResultRankList.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_rank, menu);
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

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoryResult_Items = db.getCategoryResults(sortBySpinner.getSelectedItem().toString());
        categoryResultRankAdapter = new CategoryResultRankAdapter(getBaseContext(),R.layout.result_categoryrank_list_item, categoryResult_Items);
        categoryResultRankList = (ListView) findViewById(R.id.listView_Teams_Rank);
        categoryResultRankList.setAdapter(categoryResultRankAdapter);
        categoryResultRankList.setOnItemClickListener(this);
        categoryResultRankAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
