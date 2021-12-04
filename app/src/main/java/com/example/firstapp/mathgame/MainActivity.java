package com.example.firstapp.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    // GLOBAL BUTTONS

    Button startButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;

    ConstraintLayout mainLayout;
    ConstraintLayout startLayout;

    TextView Question;
    TextView Timmer;
    TextView resultTextView;
    TextView ScoreTextView;

    ArrayList<Integer> answer =new ArrayList<Integer>();
    boolean gameState=true;

    int IncorrectAnswer;
    int LocationOfAnswer;
    int score=0;
    int NumberOfQuestion=0;

    Random rand = new Random();

    public void chooseAnswer(View view){

            if(gameState) {

                if (view.getTag().toString().equals(Integer.toString(LocationOfAnswer))) {
                    score++;
                    resultTextView.setText("Correct !");
                    QuestionGeneration(); // after each Correct answer


                } else {
                    score--;
                    resultTextView.setText("Wrong !");
                    QuestionGeneration();

                }
                NumberOfQuestion++;

                ScoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(NumberOfQuestion));
            }

    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        startLayout.setVisibility(View.VISIBLE);
        QuestionGeneration();
    }
    public void playAgain(View view){

        score=0;
        NumberOfQuestion=0;
        Timmer.setText("30s");
        resultTextView.setText("");
        ScoreTextView.setText("0/0");

        new CountDownTimer(32000,1000){


            @Override
            public void onTick(long l) {
                Timmer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish(){
                playAgain.setVisibility(View.VISIBLE);
                resultTextView.setText("");
                gameState=false;

                Timmer.setText("0s");

                resultTextView.setText("Your Score is :"+Integer.toString(score)+"/"+Integer.toString(NumberOfQuestion));

            }
        }.start(); // this is very important

        playAgain.setVisibility(View.INVISIBLE);
    }

    public void QuestionGeneration(){
        int a = rand.nextInt(50);
        int b = rand.nextInt(50);
        LocationOfAnswer = rand.nextInt(4);

        answer.clear(); // answer data clearing after each Question

        if(gameState) {

            Question.setText(Integer.toString(a) + "+" + Integer.toString(b));

            for (int i = 0; i < 4; i++) {
                if (i == LocationOfAnswer) {
                    answer.add(a + b);
                } else {

                    IncorrectAnswer = rand.nextInt(100);

                    //while loop is used in case if IncorrectAnswer get equal to random number there might be two answer
                    while (IncorrectAnswer == a + b) {
                        IncorrectAnswer = rand.nextInt(100);
                    }
                    answer.add(IncorrectAnswer); // adding random Answer to ArrayList

                }
            }

            button1 = (Button) findViewById(R.id.button1);
            button2 = (Button) findViewById(R.id.button2);
            button3 = (Button) findViewById(R.id.button3);
            button4 = (Button) findViewById(R.id.button4);

            button1.setText(Integer.toString(answer.get(0)));
            button2.setText(Integer.toString(answer.get(1)));
            button3.setText(Integer.toString(answer.get(2)));
            button4.setText(Integer.toString(answer.get(3)));


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question = (TextView) findViewById(R.id.Question);
        resultTextView =(TextView) findViewById(R.id.resultTextView);
        ScoreTextView =(TextView) findViewById(R.id.ScoreTextView);
        Timmer = (TextView) findViewById(R.id.Timmer);

        startButton = (Button) findViewById(R.id.startButton);
        playAgain =(Button) findViewById(R.id.playAgain);

        startLayout =(ConstraintLayout)findViewById(R.id.startLayout);
        //mainLayout = (ConstraintLayout)findViewById(R.id.mainLayout);
        //mainLayout.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        startButton.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));



    }
}
