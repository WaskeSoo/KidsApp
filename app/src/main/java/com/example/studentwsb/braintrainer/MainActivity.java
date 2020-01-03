package com.example.studentwsb.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button goButton;
TextView resultTextView;
TextView scoreTextView;
TextView sumTextView;
TextView timerTextView;
Button playAgainButton;
Button button0;
Button button1;
Button button2;
Button button3;
ConstraintLayout gameLayout;
GridLayout gridLayout;
ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAndswer;
    int score=0;
    int numberOfQuestions=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout=findViewById(R.id.gameConstraintLayout);
        gridLayout = findViewById(R.id.gridLayout);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);





    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAndswer).equals(view.getTag().toString())) {
            resultTextView.setText("Super!");
            score++;
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void newQuestion(){

        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAndswer = random.nextInt(4);
        answers.clear();

        for(int i =0;i<4;i++) {
            if (i == locationOfCorrectAndswer) {
                answers.add(a+b);
            }else{
                int wrongAnswer = random.nextInt(41);
                while(wrongAnswer==a+b) {
                    wrongAnswer= random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        newQuestion();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Koniec!");
                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }
}
