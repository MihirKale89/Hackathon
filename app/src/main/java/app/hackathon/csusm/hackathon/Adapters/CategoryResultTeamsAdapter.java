package app.hackathon.csusm.hackathon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 22-Apr-15.
 */
public class CategoryResultTeamsAdapter extends ArrayAdapter<JudgeTeamScore> {
    private LayoutInflater inflater;
    ArrayList<JudgeTeamScore> judgeTeamScores_Items = new ArrayList<JudgeTeamScore>();
    private int viewResourceId;
    private Context Mycontext;

    public CategoryResultTeamsAdapter(Context context,
                              int viewResourceId, ArrayList<JudgeTeamScore> judgeTeamScores_Items) {
        super(context, viewResourceId, judgeTeamScores_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.judgeTeamScores_Items = judgeTeamScores_Items;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return judgeTeamScores_Items.size();
    }

    @Override
    public JudgeTeamScore getItem(int position) {
        return judgeTeamScores_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);


        TextView textViewCategoryResultTeamName = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultTeamName);
        textViewCategoryResultTeamName.append(" " + judgeTeamScores_Items.get(position).getTeam_name_fk());

        TextView textViewCategoryResultJudgeName = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultJudgeName);
        textViewCategoryResultJudgeName.append(" "+judgeTeamScores_Items.get(position).getJudge_user_id_fk());

        TextView textViewCategoryResultScoreCategory = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultScoreCategory);
        textViewCategoryResultScoreCategory.append(" "+judgeTeamScores_Items.get(position).getScore_category_fk());

        TextView textViewCategoryResultCategoryScore = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultCategoryScore);
        textViewCategoryResultCategoryScore.append(" "+judgeTeamScores_Items.get(position).getAchieved_score_jts());


        return convertView;
    }
}
