# Quiz Application

A simple Android quiz application that allows users to test their knowledge through multiple-choice questions.

## Features

- User-friendly interface with clear question display
- Multiple-choice questions with 4 options
- Real-time feedback on selected answers
- Progress tracking through a progress bar
- Score display at the end of the quiz
- Option to start a new quiz or exit the application

## User Interface

The application consists of three main screens:

1. **Main Screen**
   - Welcome message
   - Input field for user name
   - Start button to begin the quiz

2. **Quiz Screen**
   - Progress bar showing quiz completion
   - Question display
   - Four option buttons with color-coded feedback:
     - White: Unselected option
     - Light Gray: Selected option
     - Green: Correct answer
     - Red: Incorrect answer
   - Submit button to confirm answer

3. **Result Screen**
   - Congratulations message with user's name
   - Final score display
   - Option to start a new quiz
   - Option to exit the application

## How to Use

1. Launch the application
2. Enter your name in the input field
3. Click "Start Quiz" to begin
4. Read each question carefully
5. Select your answer by clicking on one of the options
6. Click "Submit" to confirm your answer
7. View the feedback (correct/incorrect)
8. Continue to the next question
9. After completing all questions, view your final score
10. Choose to start a new quiz or exit the application

## Technical Details

- Built with Android Studio
- Written in Java
- Uses Android Material Design components
- Implements ViewModel for data management
- Follows Android best practices for UI/UX design

## Color Scheme

- Unselected options: White background with black text
- Selected option: Light gray background with black text
- Correct answer: Green background with black text
- Incorrect answer: Red background with black text

## Requirements

- Android 5.0 (Lollipop) or higher
- Minimum SDK version: 21
- Target SDK version: 33 