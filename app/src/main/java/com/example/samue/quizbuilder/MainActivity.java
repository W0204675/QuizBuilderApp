package com.example.samue.quizbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView enterName;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterName = findViewById(R.id.enterName);
        btnNext = findViewById(R.id.btnNext);

        final Toast noName = Toast.makeText(getApplicationContext(),
                "Please enter your name!",
                Toast.LENGTH_SHORT);

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(enterName.getText().toString().matches(""))
                {
                    noName.show();
                }
                else if(!enterName.getText().toString().matches(""))
                {
                    Intent i = new Intent("secondScreen");
                    Bundle extras = new Bundle();
                    String name = enterName.getText().toString();
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    extras.putString("KEY", name);
                    i.putExtras(extras);
                    startActivityForResult(i, 1);
                }
            }
        });
    }
}
