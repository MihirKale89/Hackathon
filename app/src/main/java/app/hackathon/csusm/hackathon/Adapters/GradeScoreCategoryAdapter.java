package app.hackathon.csusm.hackathon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Activities.EditScoreActivity;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 31-Mar-15.
 */
public class GradeScoreCategoryAdapter extends ArrayAdapter<Score> {
    private LayoutInflater inflater;
    public static ArrayList<Score> score_Items;
    private int viewResourceId;
    private TextView textViewAchievedScore;
    private Context Mycontext;

    public GradeScoreCategoryAdapter(Context context,
                                int viewResourceId, ArrayList<Score> score_Items) {
        super(context, viewResourceId, score_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.score_Items = score_Items;
        this.viewResourceId = viewResourceId;
    }

    public static ArrayList<Score> getScore_Items() {
        return score_Items;
    }

    public void setScore_Items(ArrayList<Score> score_Items) {
        this.score_Items = score_Items;
    }

    @Override
    public int getCount() {
        return score_Items.size();
    }

    @Override
    public Score getItem(int position) {
        return score_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);
        Log.d("watching score items in the adapter", ">>>><<<<<" + score_Items.toString());
        TextView textViewMetric = (TextView) convertView.findViewById(R.id.textViewJudgeScoreCategoryListItem);
        textViewMetric.append("" + score_Items.get(position).getMetric());
        TextView textViewTopScore = (TextView) convertView.findViewById(R.id.textViewJudgeTopScoreValueListItem);
        textViewTopScore.append(""+score_Items.get(position).getTop_score());
        textViewAchievedScore = (TextView) convertView.findViewById(R.id.textViewJudgeAchievedScoreValueListItem);
        textViewAchievedScore.append(""+score_Items.get(position).getAchieved_score());
        final int pos = position;
        Mycontext = getContext();
        SeekBar seekBarAchievedScore = (SeekBar) convertView.findViewById(R.id.seekBarGradeCategoryListItem);
        //spinnerAchievedScore.setOnSeekBarChangeListener(this);
        seekBarAchievedScore.setMax(score_Items.get(position).getTop_score());
        seekBarAchievedScore.setProgress(score_Items.get(position).getAchieved_score());
        attachProgressUpdatedListener(seekBarAchievedScore, textViewAchievedScore, score_Items.get(position), position);
        return convertView;
    }

    private void attachProgressUpdatedListener(SeekBar seekBar, final TextView positionTextView, final Score positionScore, final int position) {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                positionTextView.setText("" + progress);
                positionScore.setAchieved_score(progress);
                score_Items.set(position, positionScore);
            }
        });

    }
}
