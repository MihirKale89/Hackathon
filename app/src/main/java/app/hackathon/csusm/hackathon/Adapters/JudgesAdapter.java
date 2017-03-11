package app.hackathon.csusm.hackathon.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import app.hackathon.csusm.hackathon.Activities.EditJudgeActivity;
import app.hackathon.csusm.hackathon.Activities.MenuActivity;
import app.hackathon.csusm.hackathon.Classes.Judge;
import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.R;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Mihir on 02-Mar-15.
 */
public class JudgesAdapter extends ArrayAdapter<Judge> {
    private LayoutInflater inflater;
    ArrayList<Judge> judge_Items = new ArrayList<Judge>();
    private int viewResourceId;
    private Context Mycontext;

    public JudgesAdapter(Context context,
                        int viewResourceId, ArrayList<Judge> judge_Items) {
        super(context, viewResourceId, judge_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.judge_Items = judge_Items;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return judge_Items.size();
    }

    @Override
    public Judge getItem(int position) {
        return judge_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textViewJudgeName);
        textView.setText(judge_Items.get(position).getName());
        final int pos = position;
        Mycontext = getContext();
        Button editJudge = (Button) convertView.findViewById(R.id.buttonEditJudge);
        View.OnClickListener editJudgeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = (Integer) v.getTag();
                Intent intent = new Intent(Mycontext, EditJudgeActivity.class);
                intent.putExtra("judge", (Serializable) judge_Items.get(pos));
                // intent.putExtra("judge", score_Items.get(pos).getName());
                // intent.putExtra("judge_id", score_Items.get(pos).getId());
                // startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mycontext.startActivity(intent);
            }
        };
        editJudge.setOnClickListener(editJudgeListener);

        return convertView;
    }





}
