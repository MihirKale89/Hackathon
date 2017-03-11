package app.hackathon.csusm.hackathon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Activities.EditScoreActivity;
import app.hackathon.csusm.hackathon.Classes.Score;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 29-Mar-15.
 */
public class ScoreCategoryAdapter extends ArrayAdapter<Score>{
    private LayoutInflater inflater;
    ArrayList<Score> score_Items = new ArrayList<Score>();
    private int viewResourceId;
    private Context Mycontext;

    public ScoreCategoryAdapter(Context context,
                         int viewResourceId, ArrayList<Score> score_Items) {
        super(context, viewResourceId, score_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.score_Items = score_Items;
        this.viewResourceId = viewResourceId;
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
        TextView textViewMetric = (TextView) convertView.findViewById(R.id.textViewScoreCategoryListItem);
        textViewMetric.setText(score_Items.get(position).getMetric());
        TextView textViewTopScore = (TextView) convertView.findViewById(R.id.textViewTopScoreListItem);
        textViewTopScore.append(": "+score_Items.get(position).getTop_score());
        final int pos = position;
        Mycontext = getContext();
        Button editScore = (Button) convertView.findViewById(R.id.buttonScoreCategory);
        View.OnClickListener editJudgeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = (Integer) v.getTag();
                Intent intent = new Intent(Mycontext, EditScoreActivity.class);
                intent.putExtra("score_category", (Serializable) score_Items.get(pos));
                // intent.putExtra("judge", score_Items.get(pos).getName());
                // intent.putExtra("judge_id", score_Items.get(pos).getId());
                // startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mycontext.startActivity(intent);
            }
        };
        editScore.setOnClickListener(editJudgeListener);

        return convertView;
    }
}
