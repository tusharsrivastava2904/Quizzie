package com.example.quizzie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //object and variable declarations
    Button opt1, opt2, opt3, opt4;              //option buttons
    TextView score, ques;                       //Views

    private QuesDir listQues = new QuesDir();       //object of question directory class
    private String answer;                          //answer

    private int netScore = 0;                               //score of the individual
    private int listQuesLength = listQues.listQues.length;  //number of questions in the directory
    private int num;                                        //random number to select question
    ArrayList<Integer> visitedQues = new ArrayList<Integer>();  //list of questions that are attempted

    Random r;                                       //for random number generation

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //associating element variables with their corresponding id's
        opt1 = (Button) findViewById(R.id.opt1);
        opt2 = (Button) findViewById(R.id.opt2);
        opt3 = (Button) findViewById(R.id.opt3);
        opt4 = (Button) findViewById(R.id.opt4);

        score = (TextView) findViewById(R.id.scoreCard);
        ques = (TextView) findViewById(R.id.ques);

        score.setText("Score: " + netScore);            //displaying net score
        //displaying the first question
        updateQuestion(randGen());

        //OPTION 1
        opt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (opt1.getText() == answer){
                    opt1.setBackgroundColor(android.R.color.holo_green_light);
                    netScore++;
                    score.setText("Score: " + netScore);        //updating and displaying score for right answer

                    updateQuestion(randGen());                  //displaying next question
                } else {
                    opt1.setBackgroundColor(android.R.color.holo_red_light);
                    gameOver();                                 //ending the game if the answer is wrong
                }
            }
        });

        //OPTION 2
        opt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (opt2.getText() == answer){
                    opt2.setBackgroundColor(android.R.color.holo_green_light);
                    netScore++;
                    score.setText("Score: " + netScore);

                    updateQuestion(randGen());
                } else {
                    opt2.setBackgroundColor(android.R.color.holo_red_light);
                    gameOver();
                }
            }
        });

        //OPTION 3
        opt3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (opt3.getText() == answer){
                    opt3.setBackgroundColor(android.R.color.holo_green_light);
                    netScore++;
                    score.setText("Score: " + netScore);

                    updateQuestion(randGen());
                } else {
                    opt3.setBackgroundColor(android.R.color.holo_red_light);
                    gameOver();
                }
            }
        });

        //OPTION 4
        opt4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (opt4.getText() == answer){
                    opt4.setBackgroundColor(android.R.color.holo_green_light);
                    netScore++;
                    score.setText("Score: " + netScore);

                    updateQuestion(randGen());
                } else {
                    opt4.setBackgroundColor(android.R.color.holo_red_light);
                    gameOver();
                }
            }
        });

    }

    //Getting details about the question
    private void updateQuestion(int num) {
        visitedQues.add(num);                       //adding question to visited list

        ques.setText(listQues.getQues(num));        //displaying the question
        opt1.setText(listQues.getOpt1(num));        //displaying options
        opt2.setText(listQues.getOpt2(num));
        opt3.setText(listQues.getOpt3(num));
        opt4.setText(listQues.getOpt4(num));

        opt1.setBackgroundColor(getResources().getColor(R.color.purple_500));
        opt2.setBackgroundColor(getResources().getColor(R.color.purple_500));
        opt3.setBackgroundColor(getResources().getColor(R.color.purple_500));
        opt4.setBackgroundColor(getResources().getColor(R.color.purple_500));

        answer = listQues.getCorrectOpt(num);       //getting the correct option
    }

    //game over is called when the user either FINISHES the game or wrongly answers a question
    private void gameOver() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("GAME OVER!! Final Score is: "+ netScore + "/5");              //displaying the final score in alert
        alert.setCancelable(false);
        alert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {            //'retry' starts this activity again, resetting netScore
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                visitedQues.clear();                        //deleting visited question data
                netScore = 0;                               //resetting the score to 0
                score.setText("Score: " + netScore);        //diaplaying the updated score
                updateQuestion(randGen());                  //displaying a new question
            }
        });
        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {             //'Exit' signals the app to close
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }


    //random number generating function to ensure no question is repeated twice
    private int randGen(){
        if(netScore==5){                    //exiting the game if user answers all questions correctly
            gameOver();
        }
        else{                               //when user has questions left to answer
            r = new Random();
            num = r.nextInt(listQuesLength);
            if (visitedQues.contains(num)){         //using recursion to find a unique-random number
                randGen();
            }
            visitedQues.add(num);                   //adding the number to the visited question list
        }
        return num;
    }

}