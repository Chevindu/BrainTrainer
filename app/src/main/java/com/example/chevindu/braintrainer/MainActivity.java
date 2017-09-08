package com.example.chevindu.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButt;
    Button resetButt;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int cellCorrectAns;
    int marks = 0;
    int questNum = 0;
    TextView resultTextView;
    TextView marksTextView;
    TextView timerTextView;
    RelativeLayout gameReLay;

    Button Butt0;
    Button Butt1;
    Button Butt2;
    Button Butt3;
    TextView sum;

    public void hide (View view) {
        startButt.setVisibility(view.INVISIBLE);
        gameReLay.setVisibility(view.VISIBLE);

        resetGame(findViewById(R.id.resetButton));

    }



    public void questGen() {
        Random rand = new Random();

        int a = rand.nextInt(51), b = rand.nextInt(51);
        int NotAns = rand.nextInt(51);

        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        cellCorrectAns = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == cellCorrectAns) {
                answers.add(a + b);
            }
            else {
                while (NotAns == a + b) {
                    NotAns = rand.nextInt(51);
                }
                answers.add(NotAns);
            }
            NotAns = rand.nextInt(51);
        }

        Butt0.setText(Integer.toString(answers.get(0)));
        Butt1.setText(Integer.toString(answers.get(1)));
        Butt2.setText(Integer.toString(answers.get(2)));
        Butt3.setText(Integer.toString(answers.get(3)));
    }

    public void answerTapped(View view) {
        if (view.getTag().toString().equals(Integer.toString(cellCorrectAns))) {
            marks++;
            resultTextView.setText("Correct!");
        }
        else {
            resultTextView.setText("Incorrect!");
        }

        questNum++;
        marksTextView.setText(Integer.toString(marks) + "/" + Integer.toString(questNum));
        questGen();

    }

    public void resetGame(final View view) {
        final int marks = 0;
        final int questNum = 0;
        timerTextView.setText("30s");
        marksTextView.setText("0/0");
        resultTextView.setText("");
        resetButt.setVisibility(View.INVISIBLE);
        Butt0.setClickable(true);
        Butt1.setClickable(true);
        Butt2.setClickable(true);
        Butt3.setClickable(true);

        questGen();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your score: " + marksTextView.getText());
                timerTextView.setText("---");
                resetButt.setVisibility(View.VISIBLE);

                Butt0.setClickable(false);
                Butt1.setClickable(false);
                Butt2.setClickable(false);
                Butt3.setClickable(false);

            }
        }.start();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButt = (Button) findViewById(R.id.startButton);
        sum = (TextView) findViewById(R.id.sumTextView);

        Butt0 = (Button) findViewById(R.id.firstButt);
        Butt1 = (Button) findViewById(R.id.secButt);
        Butt2 = (Button) findViewById(R.id.thirdButt);
        Butt3 = (Button) findViewById(R.id.fourthButt);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        marksTextView = (TextView) findViewById(R.id.marksTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        resetButt = (Button) findViewById(R.id.resetButton);
        gameReLay = (RelativeLayout) findViewById(R.id.gameRelativeLayout);



    }
}
