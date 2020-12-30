package com.example.guess_the_gibberish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private String[] phrasesArray;
    private String[] correctPhrases;
    private TextView tView;
    private EditText eText;
    private Button next_button, check_button;
    List<String> phrasesList;
    int randomIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);

        tView = (TextView) findViewById(R.id.question);
        next_button = (Button) findViewById(R.id.next_button);
        check_button = (Button) findViewById(R.id.check_guess);

        correctPhrases = getResources().getStringArray(R.array.correct_phrases);
        phrasesArray = getResources().getStringArray(R.array.gib_phrases);
        phrasesList = new ArrayList<>(Arrays.asList(phrasesArray));

        Random random = new Random();
        randomIndex = random.nextInt(phrasesList.size());
        newPhrase(randomIndex);
        checkGuess(randomIndex);

        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess(randomIndex);
                Snackbar mySnackbar;
                if(checkGuess(randomIndex)){
                    mySnackbar = Snackbar.make(v, "Correct!", Snackbar.LENGTH_LONG);
                } else {
                    mySnackbar = Snackbar.make(v, "Incorrect!", Snackbar.LENGTH_LONG);
                }
                mySnackbar.show();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eText.setText("");
                randomIndex = random.nextInt(phrasesList.size());
                newPhrase(randomIndex);

            }
        });
    }

    private void newPhrase(int randomIndex) {
        String randomPhrase = phrasesList.get(randomIndex);
        tView.setText(randomPhrase);
        phrasesList.remove(randomIndex);

    }

    private boolean checkGuess(int randomIndex){
        eText = (EditText) findViewById(R.id.guess);
        String guessInput = eText.getText().toString();
        String correctPhrase = correctPhrases[randomIndex];
        return guessInput.equalsIgnoreCase(correctPhrase);
    }


}

