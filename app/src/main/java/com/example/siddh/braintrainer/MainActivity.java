package com.example.siddh.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView headingBrain;
    TextView headingTrainer;
    TextView instructions;
    TextView pointsTextView;
    TextView sumTextView;
    Button gridButton1;
    Button gridButton2;
    Button gridButton3;
    Button gridButton4;
    TextView timerTextView;
    TextView result1TextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locOfCorrectAns;
    int score = 0;
    int noOfQues = 0;

    public void playAgain(View view) {

        score = 0;
        noOfQues = 0;

        timerTextView.setText("60s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        result1TextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(60100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                if(score == noOfQues) {
                    resultTextView.setText("PERFECT SCORE");
                    result1TextView.setText("So you aren't a Dim-Wit XD");
                } else {
                    resultTextView.setText("Your Score : " + Integer.toString(score));
                    result1TextView.setText("Try harder, Dim-Wit");

                }
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locOfCorrectAns = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for(int i = 0; i<4; ++i) {

            if(i == locOfCorrectAns) {

                answers.add(a+b);

            } else {

                incorrectAnswer = rand.nextInt(101);
                while(incorrectAnswer == a+b) {

                    incorrectAnswer = rand.nextInt(101);

                }

                answers.add(incorrectAnswer);

            }
        }

        gridButton1.setText(Integer.toString(answers.get(0)));
        gridButton2.setText(Integer.toString(answers.get(1)));
        gridButton3.setText(Integer.toString(answers.get(2)));
        gridButton4.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locOfCorrectAns))) {

            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }

        noOfQues++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQues));
        generateQuestion();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        headingBrain.setVisibility(View.INVISIBLE);
        headingTrainer.setVisibility(View.INVISIBLE);
        instructions.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start);
        headingBrain = (TextView) findViewById(R.id.headingBrain);
        headingTrainer = (TextView) findViewById(R.id.headingTrainer);
        instructions = (TextView)findViewById(R.id.instructions);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        gridButton1 = (Button) findViewById(R.id.gridButton1);
        gridButton2 = (Button) findViewById(R.id.gridButton2);
        gridButton3 = (Button) findViewById(R.id.gridButton3);
        gridButton4 = (Button) findViewById(R.id.gridButton4);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        result1TextView = (TextView) findViewById(R.id.result1TextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        playAgain(findViewById(R.id.playAgainButton));

    }
}
