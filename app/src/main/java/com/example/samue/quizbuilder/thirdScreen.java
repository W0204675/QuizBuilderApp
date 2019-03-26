package com.example.samue.quizbuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thirdScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        Button againBtn = findViewById(R.id.againButton);
        Bundle secondScreen = getIntent().getExtras();
        String scoreFromTwo, quesCountFromTwo;

        if (secondScreen != null) //exist
        {
            TextView score = findViewById(R.id.scoreView);
            TextView ques = findViewById(R.id.quesView);
            scoreFromTwo = secondScreen.getString("score");
            quesCountFromTwo = secondScreen.getString("quesCount");
            score.setText("" + scoreFromTwo.toString());
            ques.setText("" + quesCountFromTwo.toString());
        }
        againBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
