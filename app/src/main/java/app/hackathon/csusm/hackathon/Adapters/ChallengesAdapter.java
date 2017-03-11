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

import app.hackathon.csusm.hackathon.Activities.EditChallengeActivity;
import app.hackathon.csusm.hackathon.Activities.EditTeamActivity;
import app.hackathon.csusm.hackathon.Classes.Challenge;
import app.hackathon.csusm.hackathon.R;

/**
 * Created by Mihir on 02-Mar-15.
 */
public class ChallengesAdapter extends ArrayAdapter<Challenge> {
    private LayoutInflater inflater;
    ArrayList<Challenge> challenge_Items = new ArrayList<Challenge>();
    private int viewResourceId;
    private Context Mycontext;

    public ChallengesAdapter(Context context,
                             int viewResourceId, ArrayList<Challenge> challenge_Items) {
        super(context, viewResourceId, challenge_Items);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.challenge_Items = challenge_Items;
        this.viewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return challenge_Items.size();
    }

    @Override
    public Challenge getItem(int position) {
        return challenge_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        convertView = inflater.inflate(viewResourceId, null);


        TextView textView = (TextView)
                convertView.findViewById(R.id.textViewChallengeName);
        textView.setText(challenge_Items.get(position).getChallenge_name());

        final int pos = position;
        Mycontext = getContext();
        Button editChallenge = (Button) convertView.findViewById(R.id.buttonEditChallenge);
        View.OnClickListener editChallengeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = (Integer) v.getTag();
                Intent intent = new Intent(Mycontext, EditChallengeActivity.class);
                intent.putExtra("challenge", (Serializable) challenge_Items.get(pos));
                // intent.putExtra("challenge", challenge_Items.get(pos).getChallenge_name());
                // startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mycontext.startActivity(intent);
            }
        };
        editChallenge.setOnClickListener(editChallengeListener);

        return convertView;
    }
}
