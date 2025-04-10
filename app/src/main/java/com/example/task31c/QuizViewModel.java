package com.example.task31c;

import androidx.lifecycle.ViewModel;
import java.util.Arrays;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private final List<Question> questions = Arrays.asList(
        new Question(
            "What is the capital of France?",
            Arrays.asList("London", "Berlin", "Paris", "Madrid"),
            2  // Paris is at index 2
        ),
        new Question(
            "Which planet is known as the Red Planet?",
            Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"),
            1  // Mars is at index 1
        ),
        new Question(
            "What is the largest mammal in the world?",
            Arrays.asList("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
            1  // Blue Whale is at index 1
        )
    );

    private int currentQuestionIndex = 0;
    private int score = 0;

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public int getProgress() {
        return (int) ((currentQuestionIndex / (float) questions.size()) * 100);
    }

    public boolean checkAnswer(int selectedIndex) {
        boolean isCorrect = selectedIndex == questions.get(currentQuestionIndex).getCorrectAnswerIndex();
        if (isCorrect) {
            score++;
        }
        return isCorrect;
    }

    public boolean moveToNextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }
} 