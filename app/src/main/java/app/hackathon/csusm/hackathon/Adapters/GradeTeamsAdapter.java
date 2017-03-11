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

import app.hackathon.csusm.hackathon.Classes.Team;
import app.hackathon.csusm.hackathon.JudgesActivities.GradeTeamActivity;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 31-Mar-15.
 */
public class GradeTeamsAdapter extends ArrayAdapter<Team>{
    private LayoutInflater inflater;
    ArrayList<Team> team_Items = new ArrayList<Team>();
    private int viewResourceId;
    private Context Mycontext;

    public GradeTeamsAdapter(Context context,
                        int viewResourceId, ArrayList<Team> team_Items) {
        super(context, viewResourceId, team_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.team_Items = team_Items;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return team_Items.size();
    }

    @Override
    public Team getItem(int position) {
        return team_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);


        TextView textViewTeamName = (TextView)
                convertView.findViewById(R.id.textViewGradeTeamName);
        textViewTeamName.append(""+team_Items.get(position).getTeamname());

        TextView textViewChallengeName = (TextView)
                convertView.findViewById(R.id.textViewGradeChallengeName_FK);
        textViewChallengeName.append(""+team_Items.get(position).getChallenge_name());

        final int pos = position;
        Mycontext = getContext();
        Button gradeTeam = (Button) convertView.findViewById(R.id.buttonGradeTeam);
        View.OnClickListener gradeTeamListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = (Integer) v.getTag();
                Intent intent = new Intent(Mycontext, GradeTeamActivity.class);
                intent.putExtra("gradeTeam", (Serializable) team_Items.get(pos));
                //intent.putExtra("team", team_Items.get(pos).getTeamname());
                // startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mycontext.startActivity(intent);
            }
        };
        gradeTeam.setOnClickListener(gradeTeamListener);
        gradeTeam.append(""+team_Items.get(position).getTeamname());

        return convertView;
    }

}
