package app.hackathon.csusm.hackathon.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.hackathon.csusm.hackathon.Helper.DatabaseHelper;
import app.hackathon.csusm.hackathon.JudgesActivities.JudgesMenuActivity;
import app.hackathon.csusm.hackathon.JudgesActivities.ViewTeamsActivity;
import app.hackathon.csusm.hackathon.R;


public class MainActivity extends ActionBarActivity {

    EditText editTextUserId, editTextUserPassword;
    public DatabaseHelper db;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void loginValidation(View view) {
        editTextUserId = (EditText) findViewById(R.id.editTextUsername);
        editTextUserPassword = (EditText) findViewById(R.id.editTextPassword);
        String userId = editTextUserId.getText().toString();
        String userPassword = editTextUserPassword.getText().toString();
        db = new DatabaseHelper(getApplicationContext());
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("user_id", ""+userId);
        editor.commit();

        if(userId.equals("scribe") && userPassword.equals("scribe123")) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
        else if(db.isValidJudge(userId, userPassword)){
            Intent intent = new Intent(this, JudgesMenuActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Log.i("LOGIN ERROR::", "Wrong credentials");
            Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG).show();
        }
    }
}
