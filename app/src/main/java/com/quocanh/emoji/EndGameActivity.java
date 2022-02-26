package com.quocanh.emoji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        textView = findViewById(R.id.scoreTextView);
        textView.append(" "+score);

        button = findViewById(R.id.restartButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}