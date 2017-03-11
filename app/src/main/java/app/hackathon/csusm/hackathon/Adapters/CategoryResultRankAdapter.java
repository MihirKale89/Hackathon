package app.hackathon.csusm.hackathon.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.hackathon.csusm.hackathon.Classes.CategoryResult;
import app.hackathon.csusm.hackathon.Classes.JudgeTeamScore;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 23-Apr-15.
 */
public class CategoryResultRankAdapter extends ArrayAdapter<CategoryResult> {
    private LayoutInflater inflater;
    ArrayList<CategoryResult> categoryResult_Items = new ArrayList<CategoryResult>();
    private int viewResourceId;
    private Context Mycontext;

    public CategoryResultRankAdapter(Context context,
                                      int viewResourceId, ArrayList<CategoryResult> categoryResult_Items) {
        super(context, viewResourceId, categoryResult_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoryResult_Items = categoryResult_Items;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return categoryResult_Items.size();
    }

    @Override
    public CategoryResult getItem(int position) {
        return categoryResult_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);

        LinearLayout categoryRankLinearLayout = (LinearLayout)
                convertView.findViewById(R.id.categoryRankListLayout);
        if(position%2 != 0){
            categoryRankLinearLayout.setBackgroundColor(Color.GRAY);
        }

        TextView textViewCategoryResultTeamName = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultTeamNameRank);
        textViewCategoryResultTeamName.append(" " + categoryResult_Items.get(position).getTeamName());

        TextView textViewCategoryResultScore = (TextView)
                convertView.findViewById(R.id.textViewCategoryResultCategoryScoreRank);
        textViewCategoryResultScore.append(" " + categoryResult_Items.get(position).getTotal_score());


        return convertView;
    }
}