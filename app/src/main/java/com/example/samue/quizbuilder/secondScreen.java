package com.example.samue.quizbuilder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import android.widget.Toast;

public class secondScreen extends AppCompatActivity {
    String string;
    String[] strings;
    TextView tvUserName, tvQuestion;
    Button btnNext2;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    Map<String,String> hashMap;
    ArrayList<String> questions, answers, answerKey;

    int quesName = 0;
    int score = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Bundle extras = getIntent().getExtras();
        String fromBundle;

        if (extras != null)
        {
            tvUserName = findViewById(R.id.tvUserName);
            fromBundle = extras.getString("KEY");
            this.tvUserName.setText("Welcome, " + fromBundle + "");
        }

        InputStream iS = this.getResources().openRawResource(R.raw.test);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        btnNext2 = findViewById(R.id.buttonNext2);
        hashMap = new HashMap<String, String>();
        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 =  findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        final Toast correctToast = Toast.makeText(getApplicationContext(),
                "Correct!",
                Toast.LENGTH_LONG);

        final Toast incorrectToast = Toast.makeText(getApplicationContext(),
                "Wrong!",
                Toast.LENGTH_LONG);

        BufferedReader bReader = new BufferedReader(new InputStreamReader(iS));

        if (iS != null)
        {
            try
            {
                while ((string =bReader.readLine())!= null)
                {
                    if(string.contains(":"))
                    {
                        this.strings = string.split(":");
                        questions.add(strings[0]);
                        answers.add(strings[1]);
                        hashMap.put(strings[0], strings[1]);
                    }
                }
                iS.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.e("IOException", String.valueOf(e));
            }
        }

        answerKey = new ArrayList<String>(4);
        answerKey.clear();
        Collections.shuffle(questions);
        tvQuestion.setText(questions.get(quesName));
        answerKey.add(hashMap.get(questions.get(quesName)));
        answers.remove(hashMap.get(questions.get(quesName)));
        Collections.shuffle(answers);
        Collections.shuffle(answers);
        answerKey.add(answers.get(0));
        answerKey.add(answers.get(1));
        answerKey.add(answers.get(2));
        Collections.shuffle(answerKey);
        radioButton1.setText(answerKey.get(0));
        radioButton2.setText(answerKey.get(1));
        radioButton3.setText(answerKey.get(2));
        radioButton4.setText(answerKey.get(3));
        count += 1;
        btnNext2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (count == 10)
                {
                    String scoreString = Integer.toString(score);
                    String quesCount = Integer.toString(questions.size());
                    Intent i2 = new Intent("thirdScreen");
                    Bundle secondScreen = new Bundle();
                    secondScreen.putString("score", scoreString);
                    secondScreen.putString("quesCount", quesCount);
                    i2.putExtras(secondScreen);
                    startActivityForResult(i2,1);
                }
                else
                {
                    answerKey.clear();
                    quesName += 1;
                    Collections.shuffle(questions);
                    tvQuestion.setText(questions.get(quesName));
                    answerKey.add(hashMap.get(questions.get(quesName)));
                    answers.remove(hashMap.get(questions.get(quesName)));
                    Collections.shuffle(answers);
                    answerKey.add(answers.get(0));
                    answerKey.add(answers.get(1));
                    answerKey.add(answers.get(2));
                    Collections.shuffle(answerKey);
                    radioButton1.setEnabled(true);
                    radioButton2.setEnabled(true);
                    radioButton3.setEnabled(true);
                    radioButton4.setEnabled(true);
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton1.setText(answerKey.get(0));
                    radioButton2.setText(answerKey.get(1));
                    radioButton3.setText(answerKey.get(2));
                    radioButton4.setText(answerKey.get(3));
                    radioButton1.setTextColor(Color.BLACK);
                    radioButton2.setTextColor(Color.BLACK);
                    radioButton3.setTextColor(Color.BLACK);
                    radioButton4.setTextColor(Color.BLACK);
                    count += 1;
                }
            }
        });

        radioButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (radioButton1.getText() == hashMap.get(questions.get(quesName)))
                {
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    correctToast.show();
                    score = score + 1;
                }
                else
                {
                    incorrectToast.show();
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                }
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (radioButton2.getText() == hashMap.get(questions.get(quesName)))
                {
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    correctToast.show();
                    score = score + 1;
                }
                else
                {
                    incorrectToast.show();
                    radioButton2.setEnabled(false);
                    radioButton1.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                }
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (radioButton3.getText() == hashMap.get(questions.get(quesName)))
                {
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    correctToast.show();
                    score = score + 1;
                }
                else
                {
                    incorrectToast.show();
                    radioButton3.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton1.setEnabled(false);
                    radioButton4.setEnabled(false);
                }
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (radioButton4.getText() == hashMap.get(questions.get(quesName)))
                {
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    correctToast.show();
                    score = score + 1;
                }
                else
                {
                    incorrectToast.show();
                    radioButton4.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton1.setEnabled(false);
                }
            }
        });
    }
}