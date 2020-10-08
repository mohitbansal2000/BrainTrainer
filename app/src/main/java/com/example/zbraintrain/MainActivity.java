package com.example.zbraintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    AudioManager amg;
    MediaPlayer mpyes;
    MediaPlayer mpno;
    ConstraintLayout layout1;
    ConstraintLayout layout2;
    Button start;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textviewtimer;
    TextView textviewquestion;
    TextView textviewcheck;
    TextView textviewscore;

    int correctanswerlocation;
    int flag=0;
    int flag2=1;
    int score=0;
    int question=0;


    ArrayList<Integer> answer =new ArrayList<Integer>();

    public void startpress(View view){
        if (flag==0)
        {
            flag++;
            nextquestion();
            time();
            start.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
        }
        else
        {
            start.setVisibility(View.INVISIBLE);
            score=0;
            question=0;
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            textviewtimer.setText("15s");
            textviewscore.setText("Score");
            textviewcheck.setText(" ");
            textviewtimer.setText(Integer.toString(score)+"/"+Integer.toString(question));
            nextquestion();
            time();
        }

    }
    public void time(){
        new CountDownTimer(15010+10,1000)
        {

            @Override
            public void onTick(long l) {
                textviewtimer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                textviewscore.setText("Finished!");
                start.setText("Play Again");
                start.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);

                flag2=0;
            }
        }.start();

    }
    public void choose(View view){
        question++;
        if(Integer.toString(correctanswerlocation).equals(view.getTag().toString()))
        {   score++;
            textviewcheck.setTextColor(0xff00f000);
            textviewcheck.setText("Correct");
            mpyes.start();
        }
        else
        {
            textviewcheck.setTextColor(0xfff00000);
            //textviewcheck.setBackgroundColor(0xfff00000);
            textviewcheck.setText("Incorrect");
            mpno.start();
        }
        nextquestion();
        textviewscore.setText(Integer.toString(score)+"/"+Integer.toString(question));
    }


    public void nextquestion(){
        Random rand1 = new Random();
        int a=rand1.nextInt(51);
        ++a;
        int b=rand1.nextInt(50);
        ++b;
        int ans=a+b;
        textviewquestion.setText(Integer.toString(a)+"+"+Integer.toString(b));
        correctanswerlocation=rand1.nextInt(4);
        answer.clear();
        for (int i=0;i<4;i++)
        {
            if(i==correctanswerlocation)
            {answer.add(ans);}
            else
            {
                int wrongans =rand1.nextInt(100);
                while(wrongans==ans)
                {wrongans=rand1.nextInt(100);}
                answer.add(wrongans);
            }
        }
        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        textviewtimer = findViewById(R.id.textviewtimer);
        textviewquestion = findViewById(R.id.textviewquestion);
        textviewcheck = findViewById(R.id.textviewcheck);
        textviewscore = findViewById(R.id.textviewscore);
        start = findViewById(R.id.start);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        amg = (AudioManager) getSystemService(AUDIO_SERVICE);
        mpyes = MediaPlayer.create(this, R.raw.yessoundyes);
        mpno = MediaPlayer.create(this, R.raw.nosoundno);

    }
}
