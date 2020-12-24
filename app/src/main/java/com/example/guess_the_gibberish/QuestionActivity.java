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
    private Button button;
    List<String> phrasesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);

        tView = (TextView) findViewById(R.id.question);
        eText = (EditText) findViewById(R.id.guess);
        button = (Button) findViewById(R.id.next_button);

        correctPhrases = getResources().getStringArray(R.array.correct_phrases);
        phrasesArray = getResources().getStringArray(R.array.gib_phrases);
        phrasesList = new ArrayList<>(Arrays.asList(phrasesArray));

        Random random = new Random();
        int randomIndex = random.nextInt(phrasesList.size());

        newPhrase(randomIndex);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomIndex = random.nextInt(phrasesList.size());
                newPhrase(randomIndex);

            }
        });
        //setQuestion();
    }

    private void newPhrase(int randomIndex) {
        String randomPhrase = phrasesList.get(randomIndex);
        tView.setText(randomPhrase);
        phrasesList.remove(randomIndex);

    }

    private boolean checkGuess(int randomIndex){
        String guessInput = eText.getText().toString();
        return guessInput.equalsIgnoreCase(correctPhrases[randomIndex]);
    }


    private void setQuestion(){
        String [] questions = {"Mis whimms hut", //my swim suit
                                "elf oh bits up",  //alphabet soup
                                "fee sha end chaps", //fish and chips
                                "fish hits span hears", //fidget spinners
                                "aight bae queue app high", // I bake you a pie
                                "haystack up hank aches", // a stack of pancakes
                                "ditch chews haze hum dan", //did you say something
                                "gift eat hey shawd",//give it a shot}
        };
    }
}

