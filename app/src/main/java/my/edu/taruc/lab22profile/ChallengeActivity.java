package my.edu.taruc.lab22profile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class ChallengeActivity extends AppCompatActivity {

    private static final int REQUEST_MAIN_MENU = 1;
    private static final int REQUEST_RESULT = 1;

    private LinearLayout player1layout;
    private LinearLayout player2layout;

    public  TextView textViewQuestions;
    public TextView textViewNums;
    public TextView textViewTimer;
    public TextView textViewAnswer1;
    public TextView textViewAnswer2;

    public ImageButton imageButtonTrue1;
    public ImageButton imageButtonFalse1;
    public ImageButton imageButtonTrue2;
    public ImageButton imageButtonFalse2;

    public int counter;

    public int countNum = 1;
    public int TotalCorrect1=0;
    public int TotalCorrect2=0;
    public boolean givenAns;
    public boolean haveanswer1 =false, haveanswer2 =false;

    public CountDownTimer counttimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_challenge);

        player1layout = (LinearLayout)findViewById(R.id.linearLayoutPlayer1);
        player2layout = (LinearLayout)findViewById(R.id.linearLayoutPlayer2);

        textViewNums = (TextView)findViewById(R.id.textViewNums);
        textViewQuestions = (TextView)findViewById(R.id.textViewQuestions);
        textViewTimer = (TextView)findViewById(R.id.textViewTimer);
        textViewNums.setText("Question " + countNum + "/12");
        CreateQuestions();
    }

    void CreateQuestions(){
        player1layout.setBackgroundColor(getResources().getColor(R.color.lightpink));
        player2layout.setBackgroundColor(getResources().getColor(R.color.lightpink));
        counter = 10;
        counttimer = new CountDownTimer(11000, 1000){
            public void onTick(long millisUntilFinished){
                textViewTimer.setText(String.valueOf("Time Left: "+ counter));
                counter--;
                haveAnswerCheck();
            }
            public void onFinish(){
                textViewTimer.setText("Times Up!!");
                haveanswer1=true;
                haveanswer2=true;
                haveAnswerCheck();

            }
        };
        counttimer.start();
        haveanswer1 =false;
        haveanswer2 =false;
        int min = 1;
        int min2 = 0;
        int max = 15;

        Random r = new Random();
        int num1 = r.nextInt(max - min + 1) + min;
        int num2 = r.nextInt(num1 - min2 + 1) + min;

        givenAns = getAnswers(num1,num2);
    }

    public void buttonAnss(View view){
        checkAnss(view);
        haveAnswerCheck();
    }

    public void checkAnss(View view){
        if(haveanswer1 ==false){
            if (view.getId() == R.id.imageButtonTrue1){
                if(givenAns == true) {
                    TotalCorrect1++;
                }
                haveanswer1 =true;
            }
            else if (view.getId() == R.id.imageButtonFalse1){
                if(givenAns == false) {
                    TotalCorrect1++;
                }
                haveanswer1 =true;
            }
        }
        if(haveanswer2 ==false){
            if (view.getId() == R.id.imageButtonTrue2){
                if(givenAns == true) {
                    TotalCorrect2++;
                }
                haveanswer2 = true;
            }
            else if (view.getId() == R.id.imageButtonFalse2) {
                if (givenAns == false) {
                    TotalCorrect2++;
                }
                haveanswer2 = true;
            }
        }
    }

    public boolean getAnswers(float num1,float num2){
        float ans = 0;
        float mix = randomAnss(1,4);
        float answer = randomAnss(0,1);

        if(mix==1){
            ans = num1 + num2;
            textViewQuestions.setText(num1 + "+" + num2 + " = ");
        }
        else if(mix==2){
            ans = num1 - num2;
            textViewQuestions.setText(num1 + "-" + num2 + " = ");
        }
        else if(mix==3){
            ans = num1 * num2;
            textViewQuestions.setText(num1 + "X" + num2 + " = ");
        }
        else if(mix==4){
            ans = num1 / num2;
            textViewQuestions.setText(num1 + "/" + num2 + " = ");
        }
        if(answer==0){
            givenAns=true;
        }
        else if(answer==1){
            ans = randomAnss((int)ans-10,(int)ans+10);
            givenAns=false;
        }
        textViewQuestions.setText(textViewQuestions.getText()+""+ans);
        //DecimalFormat decimalFormat = new DecimalFormat("#.##");
        //ans = Float.valueOf(decimalFormat.format(ans));
        return givenAns;
    }

    public float randomAnss(int min,int max){
        Random r = new Random();
        float randans = r.nextInt(max - min + 1) + min;
        return randans;
    }

    public void haveAnswerCheck(){
        if(haveanswer1 == true){
            player1layout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
        }
        if(haveanswer2 == true){
            player2layout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
        }
        if(countNum<12){
            if(haveanswer1 ==true&& haveanswer2 ==true) {
                counttimer.cancel();
                countNum++;
                textViewNums.setText("" + countNum + "/12");
                CreateQuestions();
            }
        }
        else if(countNum >= 12&& haveanswer1 ==true&& haveanswer2 ==true){
            counttimer.cancel();
            //Toast.makeText(this, "Player1 " + TotalCorrect1 + " " + "Player2 " + TotalCorrect2, Toast.LENGTH_SHORT).show();
            String i= Integer.toString(TotalCorrect1);
            String j= Integer.toString(TotalCorrect2);
            String type = "challenge";
            Bundle bundle = new Bundle();
            bundle.putString("result1", i);
            bundle.putString("result2", j);
            bundle.putString("layout", type);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_RESULT);
            finish();
        }
    }
}
