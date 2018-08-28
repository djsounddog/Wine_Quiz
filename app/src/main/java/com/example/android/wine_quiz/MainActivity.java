package com.example.android.wine_quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int questionNumber = 0;
    final private int[] questionResponse = new int[11];
    final static private int questionAnswers[] = {2, 3, 3, 2, 3, 4, 1, 3, 2, 2};
    private boolean quizCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Updates the displayed question given the current value of questionNumber
     */
    @SuppressLint("StringFormatInvalid")
    private void displayQuestion() {
        TextView displayQuestion = findViewById(R.id.question);
        TextView displayAnswer1 = findViewById(R.id.answer1);
        TextView displayAnswer2 = findViewById(R.id.answer2);
        TextView displayAnswer3 = findViewById(R.id.answer3);
        TextView displayAnswer4 = findViewById(R.id.answer4);
        Button nextButton = findViewById(R.id.next);
        Button backButton = findViewById(R.id.back);
        switch (questionNumber) {
            case 0: //Set text and radio buttons to question 1
                displayQuestion.setText(R.string.question1);
                displayAnswer1.setText(R.string.answer1_1);
                displayAnswer2.setText(R.string.answer2_1);
                displayAnswer3.setText(R.string.answer3_1);
                displayAnswer4.setText(R.string.answer4_1);
                backButton.setVisibility(View.INVISIBLE); //Disable back button for when reviewing
                break;
            case 1: //Set text and radio buttons to question 2
                displayQuestion.setText(R.string.question2);
                displayAnswer1.setText(R.string.answer1_2);
                displayAnswer2.setText(R.string.answer2_2);
                displayAnswer3.setText(R.string.answer3_2);
                displayAnswer4.setText(R.string.answer4_2);
                backButton.setVisibility(View.VISIBLE); //Enable back button to access question 1
                break;
            case 2: //and so on...
                displayQuestion.setText(R.string.question3);
                displayAnswer1.setText(R.string.answer1_3);
                displayAnswer2.setText(R.string.answer2_3);
                displayAnswer3.setText(R.string.answer3_3);
                displayAnswer4.setText(R.string.answer4_3);
                break;
            case 3:
                displayQuestion.setText(R.string.question4);
                displayAnswer1.setText(R.string.answer1_4);
                displayAnswer2.setText(R.string.answer2_4);
                displayAnswer3.setText(R.string.answer3_4);
                displayAnswer4.setText(R.string.answer4_4);
                break;
            case 4:
                displayQuestion.setText(R.string.question5);
                displayAnswer1.setText(R.string.answer1_5);
                displayAnswer2.setText(R.string.answer2_5);
                displayAnswer3.setText(R.string.answer3_5);
                displayAnswer4.setText(R.string.answer4_5);
                break;
            case 5:
                displayQuestion.setText(R.string.question6);
                displayAnswer1.setText(R.string.answer1_6);
                displayAnswer2.setText(R.string.answer2_6);
                displayAnswer3.setText(R.string.answer3_6);
                displayAnswer4.setText(R.string.answer4_6);
                break;
            case 6:
                displayQuestion.setText(R.string.question7);
                displayAnswer1.setText(R.string.answer1_7);
                displayAnswer2.setText(R.string.answer2_7);
                displayAnswer3.setText(R.string.answer3_7);
                displayAnswer4.setText(R.string.answer4_7);
                break;
            case 7:
                displayQuestion.setText(R.string.question8);
                displayAnswer1.setText(R.string.answer1_8);
                displayAnswer2.setText(R.string.answer2_8);
                displayAnswer3.setText(R.string.answer3_8);
                displayAnswer4.setText(R.string.answer4_8);
                break;
            case 8:
                displayQuestion.setText(R.string.question9);
                displayAnswer1.setText(R.string.answer1_9);
                displayAnswer2.setText(R.string.answer2_9);
                displayAnswer3.setText(R.string.answer3_9);
                displayAnswer4.setText(R.string.answer4_9);
                nextButton.setText(R.string.nextButton);
                break;
            case 9:
                nextButton.setVisibility(View.VISIBLE);
                backButton.setText(R.string.backButton);
                if (quizCompleted) {
                    nextButton.setText(R.string.exitButton);
                } else {
                    nextButton.setText(R.string.submitButton);
                }
                displayQuestion.setText(R.string.question10);
                displayAnswer1.setText(R.string.answer1_10);
                displayAnswer2.setText(R.string.answer2_10);
                displayAnswer3.setText(R.string.answer3_10);
                displayAnswer4.setText(R.string.answer4_10);
                break;
            case 10: //Results page hides questions & answers and displays toast of results
                //Allows options to go back and review questions or exit the app
                int score = markTest();     //Calculates final score
                backButton.setText(R.string.backToResults);
                nextButton.setText(R.string.exitButton);
                String resultMessageText;
                if (score < 5) {
                    //If fail mark sets final score & fail message
                    int pass = (questionNumber + 1) / 2;
                    resultMessageText = getString(R.string.failMessage) + "\n" +
                            getString(R.string.failScoreMessage, score, pass);
                } else {
                    //If pass mark displays pass message & final score
                    resultMessageText = getString(R.string.passMessage) + "\n" +
                            getString(R.string.passScoreMessage, score);
                } //Message then displayed as toast & quiz marked as complete to allow revision

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = layout.findViewById(R.id.toast_text);
                text.setText(resultMessageText);

                Toast result = new Toast(getApplicationContext());
                result.setGravity(Gravity.CENTER, 0, 0);
                result.setDuration(Toast.LENGTH_LONG);
                result.setView(layout);
                result.show();

                quizCompleted = true;   //deactivates questions and allows revision
                questionNumber = 9;
                setUserResponse();
                displayQuestion();

        }
    }

    private void getUserResponse() {
        // Check which radio button was selected and record result in questionResponse
        RadioButton response1 = findViewById(R.id.answer1);
        RadioButton response2 = findViewById(R.id.answer2);
        RadioButton response3 = findViewById(R.id.answer3);
        RadioButton response4 = findViewById(R.id.answer4);

        if (response1.isChecked()) {
            questionResponse[questionNumber] = 1;
        } else if (response2.isChecked()) {
            questionResponse[questionNumber] = 2;
        } else if (response3.isChecked()) {
            questionResponse[questionNumber] = 3;
        } else if (response4.isChecked()) {
            questionResponse[questionNumber] = 4;
        }

    }

    private int markTest() {
        int score = 0;
        for (int i = 0; i <= 9; i++) {
            if (questionResponse[i] == questionAnswers[i]) {
                score += 1;
            }
        }
        return score;
    }

    @SuppressLint("CutPasteId")
    private void setUserResponse() {
        TextView displayAnswer = null;
        TextView displayResponse = null;
        RadioButton selectedResponse;
        if (quizCompleted) {
            switch (questionAnswers[questionNumber]) {
                case 1:
                    displayAnswer = findViewById(R.id.answer1);
                    break;
                case 2:
                    displayAnswer = findViewById(R.id.answer2);
                    break;
                case 3:
                    displayAnswer = findViewById(R.id.answer3);
                    break;
                case 4:
                    displayAnswer = findViewById(R.id.answer4);
                    break;
            }
        }
        switch (questionResponse[questionNumber]) {
            case 0:
                displayResponse = displayAnswer;
                RadioButton response1 = findViewById(R.id.answer1);
                RadioButton response2 = findViewById(R.id.answer2);
                RadioButton response3 = findViewById(R.id.answer3);
                RadioButton response4 = findViewById(R.id.answer4);
                response1.setChecked(false);
                response2.setChecked(false);
                response3.setChecked(false);
                response4.setChecked(false);
                break;
            case 1:
                selectedResponse = findViewById(R.id.answer1);
                displayResponse = findViewById(R.id.answer1);
                selectedResponse.setChecked(true);
                break;
            case 2:
                selectedResponse = findViewById(R.id.answer2);
                displayResponse = findViewById(R.id.answer2);
                selectedResponse.setChecked(true);
                break;
            case 3:
                selectedResponse = findViewById(R.id.answer3);
                displayResponse = findViewById(R.id.answer3);
                selectedResponse.setChecked(true);
                break;
            case 4:
                selectedResponse = findViewById(R.id.answer4);
                displayResponse = findViewById(R.id.answer4);
                selectedResponse.setChecked(true);
                break;
        }
        if (quizCompleted) {
            setAnswerColor(displayResponse, displayAnswer);
        }
    }

    private void setAnswerColor(TextView displayResponse, TextView displayAnswer) {
        TextView displayAnswer1 = findViewById(R.id.answer1);
        TextView displayAnswer2 = findViewById(R.id.answer2);
        TextView displayAnswer3 = findViewById(R.id.answer3);
        TextView displayAnswer4 = findViewById(R.id.answer4);
        displayAnswer1.setTextColor(this.getResources().getColor(R.color.primaryTextColor));
        displayAnswer2.setTextColor(this.getResources().getColor(R.color.primaryTextColor));
        displayAnswer3.setTextColor(this.getResources().getColor(R.color.primaryTextColor));
        displayAnswer4.setTextColor(this.getResources().getColor(R.color.primaryTextColor));
        if (questionResponse[questionNumber] == questionAnswers[questionNumber]) {
            displayResponse.setTextColor(this.getResources().getColor(R.color.secondaryLightColor));
        } else {
            displayResponse.setTextColor(this.getResources().getColor(R.color.primaryColor));
            displayAnswer.setTextColor(this.getResources().getColor(R.color.secondaryLightColor));
        }
    }

    public void goNext(View v) {
        if (quizCompleted) {
            questionNumber += 1;
            if (questionNumber <= 9) {
                setUserResponse();
            } else {
                finish();
                System.exit(0);
            }
            displayQuestion();
        } else {
            getUserResponse();
            RadioGroup responseGroup = findViewById(R.id.response_group);
            responseGroup.clearCheck();
            questionNumber += 1;
            if (questionResponse[questionNumber] != 0) {
                setUserResponse();
            }
            displayQuestion();
        }
    }


    public void goBack(View v) {
        if (questionNumber > 0) {
            if (quizCompleted) {
                questionNumber -= 1;
                setUserResponse();
                displayQuestion();
            } else {
                getUserResponse();
                RadioGroup responseGroup = findViewById(R.id.response_group);
                responseGroup.clearCheck();
                questionNumber -= 1;
                if (questionResponse[questionNumber] != 0) {
                    setUserResponse();
                }
                displayQuestion();
            }
        }
    }
}
