package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(String.format("Congratulations %s!\nYour score: %d/%d", 
            userName, score, totalQuestions));

        Button newQuizButton = findViewById(R.id.newQuizButton);
        newQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER_NAME", userName);
            startActivity(intent);
            finish();
        });

        Button finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(v -> finishAffinity());
    }
} 