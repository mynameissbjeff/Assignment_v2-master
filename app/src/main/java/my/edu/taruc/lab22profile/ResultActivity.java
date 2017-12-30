package my.edu.taruc.lab22profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public TextView textViewResult;
    public TextView textViewPlayer1Result;
    public TextView textViewPlayer2Result;
    public Button buttonOk;
    String result,player1,player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        result= bundle.getString("layout");
        if(result.equals("practice")){
            setContentView(R.layout.activity_result);
            textViewResult = (TextView)findViewById(R.id.textViewResult);
            getResult1();
        }
        else if(result.equals("challenge")){
            setContentView(R.layout.activity_result2);
            textViewPlayer1Result = (TextView)findViewById(R.id.textViewPlayer1Result);
            textViewPlayer2Result = (TextView)findViewById(R.id.textViewPlayer2Result);
            getResult2();
        }
        buttonOk = (Button)findViewById(R.id.buttonOk);
    }

    void getResult1(){
        Bundle bundle = getIntent().getExtras();
        result= bundle.getString("result");
        textViewResult.setText(result + " / 12");
    }

    void getResult2(){
        Bundle bundle = getIntent().getExtras();
        player1= bundle.getString("result1");
        player2= bundle.getString("result2");
        textViewPlayer1Result.setText(player1 + " / 12");
        textViewPlayer2Result.setText(player2 + " / 12");
    }

    public void OK(View view){
        finish();
    }
}
