package my.edu.taruc.lab22profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    public  TextView textViewHistory1;
    public TextView textViewHistory2;
    public TextView textViewHistory3;

    private String history;
    private int score1;
    private int score2;
    private String comment1;
    private String comment2;
    private String comment3;

    Set<String> myset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_history);

        textViewHistory1 = (TextView)findViewById(R.id.textViewHistory1);
        textViewHistory2 = (TextView)findViewById(R.id.textViewHistory2);
        textViewHistory3 = (TextView)findViewById(R.id.textViewHistory3);

        SharedPreferences sharedPref = getSharedPreferences("ChallengeScore", Context.MODE_PRIVATE);

        if(sharedPref != null) {
            comment1 = sharedPref.getString("score1","");
            comment2 = sharedPref.getString("score2","");
            comment3 = sharedPref.getString("score3","");

            //myset = sharedPref.getStringSet("score4", myset);
            //textViewHistory2.setText(myset.toArray()[0].toString());
            //textViewHistory3.setText(myset.toArray()[1].toString());

            textViewHistory1.setText(comment1);
            textViewHistory2.setText(comment2);
            textViewHistory3.setText(comment3);
        }
    }
}
