package com.example.task31c;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private QuizViewModel viewModel;
    private TextView questionTextView;
    private Button[] optionButtons;
    private Button submitButton;
    private ProgressBar progressBar;
    private int selectedAnswerIndex = -1;
    private boolean isAnswerShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_quiz);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        Log.d(TAG, "ViewModel initialized");
        
        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        optionButtons = new Button[] {
            findViewById(R.id.option1Button),
            findViewById(R.id.option2Button),
            findViewById(R.id.option3Button),
            findViewById(R.id.option4Button)
        };
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);
        Log.d(TAG, "Views initialized");

        // Setup button listeners
        setupOptionButtons();
        setupSubmitButton();
        Log.d(TAG, "Button listeners setup");

        // Update UI
        updateQuestion();
        updateProgress();
        Log.d(TAG, "Initial UI update complete");
    }

    private void setupOptionButtons() {
        for (int i = 0; i < optionButtons.length; i++) {
            final int index = i;
            Button button = optionButtons[i];
            Log.d(TAG, "Setting up button " + (i + 1) + " with text: " + button.getText());
            
            button.setOnClickListener(v -> {
                if (isAnswerShown) return; // Don't allow selection after answer is shown
                
                Log.d(TAG, "Option " + (index + 1) + " clicked");
                // Reset all buttons first
                for (Button b : optionButtons) {
                    b.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                }
                // Set the selected button's color
                button.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                selectedAnswerIndex = index;
                Log.d(TAG, "Selected answer index: " + selectedAnswerIndex);
            });
        }
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            Log.d(TAG, "Submit button clicked, selected answer: " + selectedAnswerIndex);
            if (selectedAnswerIndex == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            isAnswerShown = true;
            boolean isCorrect = viewModel.checkAnswer(selectedAnswerIndex);
            Log.d(TAG, "Answer is " + (isCorrect ? "correct" : "incorrect"));
            showAnswerFeedback(isCorrect);
            
            if (viewModel.moveToNextQuestion()) {
                Log.d(TAG, "Moving to next question");
                submitButton.postDelayed(() -> {
                    resetForNextQuestion();
                }, 1000);
            } else {
                Log.d(TAG, "Quiz completed, showing results");
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("SCORE", viewModel.getScore());
                intent.putExtra("TOTAL_QUESTIONS", viewModel.getTotalQuestions());
                intent.putExtra("USER_NAME", getIntent().getStringExtra("USER_NAME"));
                startActivity(intent);
                finish();
            }
        });
    }

    private void resetForNextQuestion() {
        // Reset all buttons to white and enable them
        for (Button button : optionButtons) {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            button.setEnabled(true);
        }
        selectedAnswerIndex = -1;
        isAnswerShown = false;
        updateQuestion();
        updateProgress();
        Log.d(TAG, "Reset for next question");
    }

    private void updateQuestion() {
        Question question = viewModel.getCurrentQuestion();
        questionTextView.setText(question.getQuestionText());
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(question.getOptions().get(i));
            // Reset button state for new question
            optionButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            optionButtons[i].setEnabled(true);
            Log.d(TAG, "Setting option " + (i + 1) + " text to: " + question.getOptions().get(i));
        }
        Log.d(TAG, "Question updated: " + question.getQuestionText());
    }

    private void updateProgress() {
        int progress = viewModel.getProgress();
        progressBar.setProgress(progress);
        Log.d(TAG, "Progress updated: " + progress + "%");
    }

    private void showAnswerFeedback(boolean isCorrect) {
        // Get the correct answer index
        int correctIndex = viewModel.getCurrentQuestion().getCorrectAnswerIndex();
        Log.d(TAG, "Correct answer index: " + correctIndex + 
              ", Selected answer index: " + selectedAnswerIndex);
        
        // Set the correct answer to green
        optionButtons[correctIndex].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        Log.d(TAG, "Set button " + correctIndex + " to GREEN");
        
        // If the answer was incorrect, set the selected answer to red
        if (!isCorrect) {
            optionButtons[selectedAnswerIndex].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            Log.d(TAG, "Set button " + selectedAnswerIndex + " to RED");
        }
        
        // Disable all buttons after showing feedback
        for (Button button : optionButtons) {
            button.setEnabled(false);
        }
        
        Log.d(TAG, "Answer feedback shown - correct index: " + correctIndex + 
              ", selected index: " + selectedAnswerIndex + 
              ", is correct: " + isCorrect);
    }
} 