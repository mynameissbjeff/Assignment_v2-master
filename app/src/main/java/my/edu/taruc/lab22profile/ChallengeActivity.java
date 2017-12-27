package my.edu.taruc.lab22profile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;



public class ChallengeActivity extends AppCompatActivity {

    private static final int REQUEST_MAIN_MENU = 1;

    public  TextView textViewQuestions;
    public TextView textViewNums;

    public ImageButton imageButtonTrue1;
    public ImageButton imageButtonFalse1;
    public ImageButton imageButtonTrue2;
    public ImageButton imageButtonFalse2;

    public int countNum = 1;
    public int TotalCorrect1=0;
    public int TotalCorrect2=0;
    public boolean givenAns;
    public boolean aldans1=false,aldans2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_challenge);

        textViewNums = (TextView)findViewById(R.id.textViewNums);
        textViewQuestions = (TextView)findViewById(R.id.textViewQuestions);
        textViewQuestions.setText("lol");
        textViewNums.setText("" + countNum + "/12");
        CreateQuestions();
    }

    void CreateQuestions(){
        aldans1=false;
        aldans2=false;
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
        if(countNum<12){
            if(aldans1==true&&aldans2==true) {
                countNum++;
                textViewNums.setText("" + countNum + "/12");
                CreateQuestions();
            }
        }
        else if(countNum >= 12&&aldans1==true&&aldans2==true){
            Toast.makeText(this, "Player1 " + TotalCorrect1 + " " + "Player2 " + TotalCorrect2, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void checkAnss(View view){
        if(aldans1==false){
            if (view.getId() == R.id.imageButtonTrue1){
                if(givenAns == true) {
                    TotalCorrect1++;
                }
                aldans1=true;
            }
            else if (view.getId() == R.id.imageButtonFalse1){
                if(givenAns == false) {
                    TotalCorrect1++;
                }
                aldans1=true;
            }
        }
        if(aldans2==false){
            if (view.getId() == R.id.imageButtonTrue2){
                if(givenAns == true) {
                    TotalCorrect2++;
                }
                aldans2 = true;
            }
            else if (view.getId() == R.id.imageButtonFalse2) {
                if (givenAns == false) {
                    TotalCorrect2++;
                }
                aldans2 = true;
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
}
