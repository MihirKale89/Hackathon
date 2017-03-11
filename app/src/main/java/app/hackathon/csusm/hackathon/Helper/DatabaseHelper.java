package app.hackathon.csusm.hackathon.Helper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import app.hackathon.csusm.hackathon.Classes.CategoryResult;
import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.Classes.Team;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "hackathonDB";

    // Table Names
    private static final String TABLE_JUDGES = "judges";
    private static final String TABLE_TEAMS = "teams";
    private static final String TABLE_CHALLENGES = "challenges";
    private static final String TABLE_SCORE_CATEGORIES = "score_categories";
    private static final String TABLE_JUDGE_TEAM_SCORES = "judge_team_scores";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // JUDGES Table - column names
    private static final String KEY_JUDGE_NAME = "judge_name";
    private static final String KEY_JUDGE_USER_ID = "judge_user_id";
    private static final String KEY_JUDGE_PASSWORD = "judge_password";

    // TEAMS Table - column names
    private static final String KEY_TEAM_NAME = "team_name";
    private static final String KEY_CHALLENGE_ID_FK = "challenge_id_fk";
    private static final String KEY_GRAND_TOTAL = "grand_total";

    // CHALLENGES Table - column names
    private static final String KEY_CHALLENGE_NAME = "challenge_name";


    // SCORE CATEGORIES Table - column names
    private static final String KEY_SCORE_CATEGORY = "score_category_name";
    private static final String KEY_MAX_POINTS = "max_points";
    private static final String KEY_ACHIEVED_POINTS = "achieved_points";

    // JUDGE_TEAM_SCORE Table - column names
    private static final String KEY_JUDGE_USER_ID_FK = "judge_user_id_fk";
    private static final String KEY_TEAM_NAME_FK = "team_name_fk";
    private static final String KEY_SCORE_CATEGORY_FK = "score_category_name_fk";
    private static final String KEY_ACHIEVED_POINTS_JTS = "achieved_points_jts";
    private static final String KEY_JSON_SCORES = "json_scores";

    // NOTE_TAGS Table - column names
    private static final String KEY_JUDGE_ID = "judge_id";
    private static final String KEY_TEAM_ID = "team_id";
    private static final String KEY_CHALLENGE_ID = "challenge_id";
    private static final String KEY_SCORE_CATEGORY_ID = "score_category_id";
    private static final String KEY_JUDGE_TEAM_SCORES_ID = "judge_team_score_id";

    // Table Create Statements
    // Judges table create statement
    private static final String CREATE_TABLE_JUDGE = "CREATE TABLE "
            + TABLE_JUDGES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JUDGE_NAME + " TEXT,"
            + KEY_JUDGE_USER_ID + " TEXT UNIQUE,"
            + KEY_JUDGE_PASSWORD + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Teams table create statement
    private static final String CREATE_TABLE_TEAMS = "CREATE TABLE " + TABLE_TEAMS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEAM_NAME + " TEXT UNIQUE,"
            + KEY_CHALLENGE_ID_FK + " INTEGER,"
            + KEY_CHALLENGE_NAME + " TEXT,"
            + KEY_GRAND_TOTAL + " NUMBER,"
            + KEY_CREATED_AT + " DATETIME,"
            + " FOREIGN KEY ("+ KEY_CHALLENGE_ID_FK +") REFERENCES "+ TABLE_CHALLENGES+"("+KEY_ID+"))";

    // Challenges table create statement
    private static final String CREATE_TABLE_CHALLENGES = "CREATE TABLE "
            + TABLE_CHALLENGES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CHALLENGE_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Score Categories table create statement
    private static final String CREATE_TABLE_SCORE_CATEGORIES = "CREATE TABLE "
            + TABLE_SCORE_CATEGORIES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SCORE_CATEGORY + " TEXT,"
            + KEY_MAX_POINTS + " INTEGER,"
            + KEY_ACHIEVED_POINTS + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // JUDGE_TEAM_SCORES table create statement
    private static final String CREATE_TABLE_JUDGE_TEAM_SCORES = "CREATE TABLE "
            + TABLE_JUDGE_TEAM_SCORES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JUDGE_USER_ID_FK + " TEXT,"
            + KEY_TEAM_NAME_FK + " TEXT,"
            + KEY_SCORE_CATEGORY_FK + " TEXT,"
            + KEY_ACHIEVED_POINTS_JTS + " INTEGER,"
            + KEY_JSON_SCORES + " TEXT,"
            + KEY_CREATED_AT + " DATETIME,"
            + " FOREIGN KEY ("+ KEY_JUDGE_USER_ID_FK +") REFERENCES "+ TABLE_JUDGES+"("+KEY_JUDGE_USER_ID+"),"
            + " FOREIGN KEY ("+ KEY_TEAM_NAME_FK +") REFERENCES "+ TABLE_TEAMS+"("+KEY_TEAM_NAME+"),"
            + " FOREIGN KEY ("+ KEY_SCORE_CATEGORY_FK +") REFERENCES "+ TABLE_SCORE_CATEGORIES+"("+KEY_SCORE_CATEGORY+"))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_JUDGE);
        db.execSQL(CREATE_TABLE_CHALLENGES);
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_SCORE_CATEGORIES);
        db.execSQL(CREATE_TABLE_JUDGE_TEAM_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUDGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHALLENGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_JUDGE_TEAM_SCORES);
        // create new tables
        onCreate(db);
    }

    // ------------------------ "Delete table" methods ----------------//

    public void deleteChallenges(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CHALLENGES);

    }

    public void deleteTeams(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TEAMS);

    }

    public void deleteJudges(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_JUDGES);

    }

    public void deleteScoreCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SCORE_CATEGORIES);

    }

    public void deleteJudgeScores(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_JUDGE_TEAM_SCORES);

    }

    // ------------------------ "Judges" table methods ----------------//

    /*
     * Creating a Judge
     */
    public long createJudge(Judge judge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JUDGE_NAME, judge.getName());
        values.put(KEY_JUDGE_USER_ID, judge.getJudgeUserId());
        values.put(KEY_JUDGE_PASSWORD, judge.getJudgePassword());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long judge_id = db.insert(TABLE_JUDGES, null, values);

        // insert tag_ids
        /*for (long tag_id : tag_ids) {
            createTodoTag(judge_id, tag_id);
        }*/

        return judge_id;
    }

    /*
     * get single Judge
     */
    public Judge getJudge(long judge_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_JUDGES + " WHERE "
                + KEY_ID + " = " + judge_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Judge judge = new Judge();
        judge.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        judge.setName((c.getString(c.getColumnIndex(KEY_JUDGE_NAME))));
        judge.setJudgeUserId(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID)));
        judge.setJudgePassword(c.getString(c.getColumnIndex(KEY_JUDGE_PASSWORD)));
        judge.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return judge;
    }


    public ArrayList<Judge> getAllJudges() {
        ArrayList<Judge> judges = new ArrayList<Judge>();
        String selectQuery = "SELECT  * FROM " + TABLE_JUDGES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Judge judge = new Judge();
                judge.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                judge.setName((c.getString(c.getColumnIndex(KEY_JUDGE_NAME))));
                judge.setJudgeUserId(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID)));
                judge.setJudgePassword(c.getString(c.getColumnIndex(KEY_JUDGE_PASSWORD)));
                judge.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                judges.add(judge);
            } while (c.moveToNext());
        }

        return judges;
    }

    public boolean isValidJudge(String judgeUserId, String judgePassword){
        String countQuery = "SELECT  * FROM " + TABLE_JUDGES + " WHERE "
                +KEY_JUDGE_USER_ID+" = '"+judgeUserId+"' AND "
                +KEY_JUDGE_PASSWORD+" = '"+judgePassword+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        if (count == 1) return true;
        else return false;
    }


    public int getJudgesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_JUDGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a Judge
     */
    public int updateJudge(Judge judge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JUDGE_NAME, judge.getName());
        values.put(KEY_JUDGE_USER_ID, judge.getJudgeUserId());
        values.put(KEY_JUDGE_PASSWORD, judge.getJudgePassword());
        //values.put(KEY_STATUS, judge.getStatus());

        // updating row
        return db.update(TABLE_JUDGES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(judge.getId()) });
    }

    /*
     * Deleting a judge
     */
    public void deleteJudge(long judge_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUDGES, KEY_ID + " = ?",
                new String[] { String.valueOf(judge_id) });
    }

    // ------------------------ "Teams" table methods ----------------//
    public long createTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM_NAME, team.getTeamname());
        // values.put(KEY_STATUS, judge.getStatus());
        values.put(KEY_CHALLENGE_ID_FK, team.getChallenge_id_fk());
        values.put(KEY_CHALLENGE_NAME, team.getChallenge_name());
        values.put(KEY_GRAND_TOTAL, team.getGrand_total());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long team_id = db.insert(TABLE_TEAMS, null, values);

        // insert tag_ids
        /*for (long tag_id : tag_ids) {
            createTodoTag(judge_id, tag_id);
        }*/

        return team_id;
    }

    /*
     * get single Team
     */
    public Team getTeam(long team_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS + " WHERE "
                + KEY_ID + " = " + team_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Challenge challenge_fk = new Challenge();
        Team team = new Team();
        team.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        team.setTeamname((c.getString(c.getColumnIndex(KEY_TEAM_NAME))));
        team.setChallenge_id_fk(c.getInt(c.getColumnIndex(KEY_CHALLENGE_ID_FK)));
        team.setChallenge_name(c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME)));
        team.setGrand_total(c.getInt(c.getColumnIndex(KEY_GRAND_TOTAL)));
        team.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        return team;
    }


    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teams = new ArrayList<Team>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                team.setTeamname((c.getString(c.getColumnIndex(KEY_TEAM_NAME))));
                team.setChallenge_id_fk(c.getInt(c.getColumnIndex(KEY_CHALLENGE_ID_FK)));
                team.setChallenge_name(c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME)));
                team.setGrand_total(c.getInt(c.getColumnIndex(KEY_GRAND_TOTAL)));
                team.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                teams.add(team);
            } while (c.moveToNext());
        }

        return teams;
    }

    public ArrayList<Team> getAllTeamsByTeamNames() {
        ArrayList<Team> teams = new ArrayList<Team>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS + " ORDER BY " + KEY_TEAM_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                team.setTeamname((c.getString(c.getColumnIndex(KEY_TEAM_NAME))));
                team.setChallenge_id_fk(c.getInt(c.getColumnIndex(KEY_CHALLENGE_ID_FK)));
                team.setChallenge_name(c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME)));
                team.setGrand_total(c.getInt(c.getColumnIndex(KEY_GRAND_TOTAL)));
                team.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                teams.add(team);
            } while (c.moveToNext());
        }

        return teams;
    }

    public ArrayList<Team> getAllTeamsByChallengeNames() {
        ArrayList<Team> teams = new ArrayList<Team>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS + " ORDER BY " + KEY_CHALLENGE_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                team.setTeamname((c.getString(c.getColumnIndex(KEY_TEAM_NAME))));
                team.setChallenge_id_fk(c.getInt(c.getColumnIndex(KEY_CHALLENGE_ID_FK)));
                team.setChallenge_name(c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME)));
                team.setGrand_total(c.getInt(c.getColumnIndex(KEY_GRAND_TOTAL)));
                team.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                teams.add(team);
            } while (c.moveToNext());
        }

        return teams;
    }

    public ArrayList<Team> getAllTeamsByGrandTotal() {
        ArrayList<Team> teams = new ArrayList<Team>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS + " ORDER BY " + KEY_GRAND_TOTAL + " DESC";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                team.setTeamname((c.getString(c.getColumnIndex(KEY_TEAM_NAME))));
                team.setChallenge_id_fk(c.getInt(c.getColumnIndex(KEY_CHALLENGE_ID_FK)));
                team.setChallenge_name(c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME)));
                team.setGrand_total(c.getInt(c.getColumnIndex(KEY_GRAND_TOTAL)));
                team.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                teams.add(team);
            } while (c.moveToNext());
        }

        return teams;
    }
    public int getTeamsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TEAMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a Team
     */
    public int updateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM_NAME, team.getTeamname());
        values.put(KEY_CHALLENGE_ID_FK, team.getChallenge_id_fk());
        values.put(KEY_CHALLENGE_NAME, team.getChallenge_name());
        values.put(KEY_GRAND_TOTAL,team.getGrand_total());
        //values.put(KEY_STATUS, judge.getStatus());

        // updating row
        return db.update(TABLE_TEAMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(team.getId()) });
    }

    /*
     * Deleting a Team
     */
    public void deleteTeam(long team_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAMS, KEY_ID + " = ?",
                new String[] { String.valueOf(team_id) });
    }

    // ------------------------ "Challenges" table methods ----------------//
    public long createChallenge(Challenge challenge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALLENGE_NAME, challenge.getChallenge_name());
        // values.put(KEY_STATUS, judge.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long team_id = db.insert(TABLE_CHALLENGES, null, values);

        // insert tag_ids
        /*for (long tag_id : tag_ids) {
            createTodoTag(judge_id, tag_id);
        }*/

        return team_id;
    }

    /*
     * get single Team
     */
    public Challenge getChallenge(long team_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CHALLENGES + " WHERE "
                + KEY_ID + " = " + team_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Challenge challenge = new Challenge();
        challenge.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        challenge.setChallenge_name((c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME))));
        challenge.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        return challenge;
    }


    public ArrayList<Challenge> getAllChallenges() {
        ArrayList<Challenge> teams = new ArrayList<Challenge>();
        String selectQuery = "SELECT  * FROM " + TABLE_CHALLENGES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Challenge challenge = new Challenge();
                challenge.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                challenge.setChallenge_name((c.getString(c.getColumnIndex(KEY_CHALLENGE_NAME))));
                challenge.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                teams.add(challenge);
            } while (c.moveToNext());
        }

        return teams;
    }


    public int getChallengesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CHALLENGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a Team
     */
    public int updateChallenge(Challenge challenge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHALLENGE_NAME, challenge.getChallenge_name());
        //values.put(KEY_STATUS, judge.getStatus());

        // updating row
        return db.update(TABLE_CHALLENGES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(challenge.getId()) });
    }

    /*
     * Deleting a Team
     */
    public void deleteChallenge(long challenge_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHALLENGES, KEY_ID + " = ?",
                new String[] { String.valueOf(challenge_id) });
    }

    // ------------------------ "Score Categories" table methods ----------------//

    /*
     * Creating a Score Category
     */
    public long createScoreCategory(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SCORE_CATEGORY, score.getMetric());
        values.put(KEY_MAX_POINTS, score.getTop_score());
        values.put(KEY_ACHIEVED_POINTS, score.getAchieved_score());
        values.put(KEY_CREATED_AT, getDateTime());


        // insert row
        long score_category_id = db.insert(TABLE_SCORE_CATEGORIES, null, values);
        return score_category_id;
    }

    /*
     * get single Score Category
     */
    public Score getScoreCategory(long score_category_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCORE_CATEGORIES + " WHERE "
                + KEY_ID + " = " + score_category_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Score score = new Score();
        score.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        score.setMetric((c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY))));
        score.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        score.setTop_score(c.getInt(c.getColumnIndex(KEY_MAX_POINTS)));
        score.setAchieved_score(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS)));

        return score;
    }


    public ArrayList<Score> getAllScoreCategories() {
        ArrayList<Score> score_categories = new ArrayList<Score>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCORE_CATEGORIES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Score score = new Score();
                score.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                score.setMetric((c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY))));
                score.setTop_score(c.getInt(c.getColumnIndex(KEY_MAX_POINTS)));
                score.setAchieved_score(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS)));
                score.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                score_categories.add(score);
            } while (c.moveToNext());
        }

        return score_categories;
    }


    public int getScoreCategoriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SCORE_CATEGORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a Score category
     */
    public int updateScoreCategory(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE_CATEGORY, score.getMetric());
        values.put(KEY_MAX_POINTS, score.getTop_score());
        values.put(KEY_ACHIEVED_POINTS, score.getAchieved_score());
        //values.put(KEY_STATUS, judge.getStatus());

        // updating row
        return db.update(TABLE_SCORE_CATEGORIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(score.getId()) });
    }

    /*
     * Deleting a score category
     */
    public void deleteScoreCategory(long score_category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORE_CATEGORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(score_category_id) });
    }


    // ------------------------ "judge_team_scores" table methods ----------------//
    public long createJudgeTeamScore(JudgeTeamScore judgeTeamScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JUDGE_USER_ID_FK, judgeTeamScore.getJudge_user_id_fk());
        // values.put(KEY_STATUS, judge.getStatus());
        values.put(KEY_TEAM_NAME_FK, judgeTeamScore.getTeam_name_fk());
        values.put(KEY_SCORE_CATEGORY_FK, judgeTeamScore.getScore_category_fk());
        values.put(KEY_ACHIEVED_POINTS_JTS, judgeTeamScore.getAchieved_score_jts());
        values.put(KEY_JSON_SCORES, judgeTeamScore.getJson_scores());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long judgeTeamScore_id = db.insert(TABLE_JUDGE_TEAM_SCORES, null, values);
        return judgeTeamScore_id;
    }

    /*
     * get single judge_team_score
     */
    public JudgeTeamScore getJudgeTeamScore(long judgeTeamScore_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES + " WHERE "
                + KEY_ID + " = " + judgeTeamScore_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        JudgeTeamScore judgeTeamScore = new JudgeTeamScore();
        judgeTeamScore.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        judgeTeamScore.setJudge_user_id_fk(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID_FK)));
        judgeTeamScore.setTeam_name_fk(c.getString(c.getColumnIndex(KEY_TEAM_NAME_FK)));
        judgeTeamScore.setScore_category_fk(c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY_FK)));
        judgeTeamScore.setAchieved_score_jts(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS_JTS)));
        judgeTeamScore.setJson_scores(c.getString(c.getColumnIndex(KEY_JSON_SCORES)));
        judgeTeamScore.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        return judgeTeamScore;
    }


    public ArrayList<CategoryResult> getCategoryResults(String category) {
        ArrayList<CategoryResult> resultTeams;
        resultTeams = new ArrayList<CategoryResult>();

        /*String selectQuery = "SELECT SUM("+KEY_ACHIEVED_POINTS_JTS+"), "+KEY_TEAM_NAME_FK+" FROM " + TABLE_JUDGE_TEAM_SCORES +
                                " WHERE "+KEY_SCORE_CATEGORY_FK+" = '"+category+"' ORDER BY "+KEY_TEAM_NAME_FK;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                CategoryResult categoryResult = new CategoryResult();
                categoryResult.setTotal_score(c.getInt(1));
                categoryResult.setTeamName(c.getString(c.getColumnIndex(KEY_TEAM_NAME_FK)));
                resultTeams.add(categoryResult);
            } while (c.moveToNext());
        }*/

        int totalScore = 0;
        ArrayList<JudgeTeamScore> judgeTeamScores = getAllJudgeTeamScores();
        ArrayList<Team> teams = getAllTeams();
        for (Team t : teams) {
            for (JudgeTeamScore jts : judgeTeamScores) {
                if (t.getTeamname().equals(jts.getTeam_name_fk()) && category.equals("Grand Total")){
                    totalScore += jts.getAchieved_score_jts();
                }
                else if (t.getTeamname().equals(jts.getTeam_name_fk()) && jts.getScore_category_fk().equals(category)) {
                    totalScore += jts.getAchieved_score_jts();
                }
            }
            CategoryResult categoryResult = new CategoryResult();
            categoryResult.setTeamName(t.getTeamname());
            categoryResult.setTotal_score(totalScore);
            resultTeams.add(categoryResult);
            totalScore = 0;
        }
        //Collections.sort(resultTeams, comparator);
        Collections.sort(resultTeams);
        return resultTeams;
    }


    public ArrayList<JudgeTeamScore> getAllJudgeTeamScores() {
        ArrayList<JudgeTeamScore> judgeTeamScores = new ArrayList<JudgeTeamScore>();
        String selectQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                JudgeTeamScore judgeTeamScore = new JudgeTeamScore();
                judgeTeamScore.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                judgeTeamScore.setJudge_user_id_fk(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID_FK)));
                judgeTeamScore.setTeam_name_fk(c.getString(c.getColumnIndex(KEY_TEAM_NAME_FK)));
                judgeTeamScore.setScore_category_fk(c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY_FK)));
                judgeTeamScore.setAchieved_score_jts(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS_JTS)));
                judgeTeamScore.setJson_scores(c.getString(c.getColumnIndex(KEY_JSON_SCORES)));
                judgeTeamScore.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                judgeTeamScores.add(judgeTeamScore);
            } while (c.moveToNext());
        }

        return judgeTeamScores;
    }

    public JudgeTeamScore getSpecificJudgeTeamScores(String judgeUserId, String teamName, String scoreCategory) {
        JudgeTeamScore judgeTeamScore = new JudgeTeamScore();
        String selectQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES + " WHERE "
                +KEY_JUDGE_USER_ID_FK+" = '"+judgeUserId+"' AND "
                +KEY_TEAM_NAME_FK+" = '"+teamName+"' AND "
                +KEY_SCORE_CATEGORY_FK+" = '"+scoreCategory+"'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                judgeTeamScore.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                judgeTeamScore.setJudge_user_id_fk(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID_FK)));
                judgeTeamScore.setTeam_name_fk(c.getString(c.getColumnIndex(KEY_TEAM_NAME_FK)));
                judgeTeamScore.setScore_category_fk(c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY_FK)));
                judgeTeamScore.setAchieved_score_jts(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS_JTS)));
                judgeTeamScore.setJson_scores(c.getString(c.getColumnIndex(KEY_JSON_SCORES)));
                judgeTeamScore.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

            } while (c.moveToNext());
        }

        return judgeTeamScore;
    }

    public ArrayList<JudgeTeamScore> getSpecificJudgeTeamScores(String judgeUserId, String teamName) {
        ArrayList<JudgeTeamScore> judgeTeamScores = new ArrayList<JudgeTeamScore>();
        String selectQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES + " WHERE "
                +KEY_JUDGE_USER_ID_FK+" = '"+judgeUserId+"' AND "
                +KEY_TEAM_NAME_FK+" = '"+teamName+"'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                JudgeTeamScore judgeTeamScore = new JudgeTeamScore();
                judgeTeamScore.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                judgeTeamScore.setJudge_user_id_fk(c.getString(c.getColumnIndex(KEY_JUDGE_USER_ID_FK)));
                judgeTeamScore.setTeam_name_fk(c.getString(c.getColumnIndex(KEY_TEAM_NAME_FK)));
                judgeTeamScore.setScore_category_fk(c.getString(c.getColumnIndex(KEY_SCORE_CATEGORY_FK)));
                judgeTeamScore.setAchieved_score_jts(c.getInt(c.getColumnIndex(KEY_ACHIEVED_POINTS_JTS)));
                judgeTeamScore.setJson_scores(c.getString(c.getColumnIndex(KEY_JSON_SCORES)));
                judgeTeamScore.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                judgeTeamScores.add(judgeTeamScore);
            } while (c.moveToNext());
        }

        return judgeTeamScores;
    }


    public int getJudgeTeamScoresCount() {
        String countQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a JudgeTeamScore
     */
    public int updateJudgeTeamScore(JudgeTeamScore judgeTeamScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_JUDGE_USER_ID_FK, judgeTeamScore.getJudge_user_id_fk());
        values.put(KEY_TEAM_NAME_FK, judgeTeamScore.getTeam_name_fk());
        values.put(KEY_SCORE_CATEGORY_FK, judgeTeamScore.getScore_category_fk());
        values.put(KEY_ACHIEVED_POINTS_JTS, judgeTeamScore.getAchieved_score_jts());
        values.put(KEY_JSON_SCORES, judgeTeamScore.getJson_scores());
        //values.put(KEY_STATUS, judge.getStatus());

        // updating row
        return db.update(TABLE_JUDGE_TEAM_SCORES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(judgeTeamScore.getId()) });
    }

    /*
     * Deleting a JudgeTeamScore
     */
    public void deleteJudgeTeamScore(long judgeTeamScore_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUDGE_TEAM_SCORES, KEY_ID + " = ?",
                new String[] { String.valueOf(judgeTeamScore_id) });
    }

    public boolean existsJudgeTeamScore(String judgeUserId, String teamName, String scoreCategory){

        String countQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES + " WHERE "
                +KEY_JUDGE_USER_ID_FK+" = '"+judgeUserId+"' AND "
                +KEY_TEAM_NAME_FK+" = '"+teamName+"' AND "
                +KEY_SCORE_CATEGORY_FK+" = '"+scoreCategory+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        Log.d("INSIDE existsJudgeTeamScore","checking if this is called. Count ="+count);
        if (count >= 1) return true;
        else return false;
    }

    public boolean existsJudgeTeamScore(String judgeUserId, String teamName){

        String countQuery = "SELECT  * FROM " + TABLE_JUDGE_TEAM_SCORES + " WHERE "
                +KEY_JUDGE_USER_ID_FK+" = '"+judgeUserId+"' AND "
                +KEY_TEAM_NAME_FK+" = '"+teamName+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        Log.d("INSIDE existsJudgeTeamScore","checking if this is called. Count ="+count);
        if (count >= 1) return true;
        else return false;
    }

    // ------------------------ "created_at" method requirement ----------------//
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
