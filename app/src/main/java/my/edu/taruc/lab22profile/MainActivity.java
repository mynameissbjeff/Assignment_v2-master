package my.edu.taruc.lab22profile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PROFILE_UPDATE = 1;
    private static final int REQUEST_SELECT_MODE = 1;
    private static final int REQUEST_CHALLENGE = 1;
    public static final String PROFILE_NAME = "my.edu.taruc.lab22profile.name";
    public static final String PROFILE_EMAIL = "my.edu.taruc.lab22profile.email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        //link UI to program
        //textViewName = (TextView)findViewById(R.id.textViewName);
        //textViewEmail = (TextView)findViewById(R.id.textViewEmail);
    }

    public void SelectMode(View view){
        Intent intent = new Intent(this,ModeActivity.class);
        startActivityForResult(intent, REQUEST_SELECT_MODE);
    }

    public void Chanllenge(View view){
        Intent intent = new Intent(this,ChallengeActivity.class);
        startActivityForResult(intent, REQUEST_CHALLENGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //requestCode = the unique code which identify an intent
        //resultCode = the result returned by an intent; either OK or not
        //data = the actual data returned by an intent
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PROFILE_UPDATE&&resultCode==RESULT_OK){
            //String name,email;
            //name = data.getStringExtra(PROFILE_NAME);
            //email = data.getStringExtra(PROFILE_EMAIL);
            //textViewName.setText(getString(R.string.name)+ name);
            //textViewEmail.setText(getString(R.string.email)+ email);

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main Activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main Activity", "onPause");
    }

}
