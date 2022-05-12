package com.example.fishgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private Button StartGameAgain;
    private TextView DisplayScore;
    private String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score= getIntent().getExtras().get("score").toString();

        StartGameAgain=(Button) findViewById(R.id.button);
        DisplayScore=(TextView) findViewById(R.id.display);

        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(GameOver.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

        DisplayScore.setText("Score:"+score);
    }
}