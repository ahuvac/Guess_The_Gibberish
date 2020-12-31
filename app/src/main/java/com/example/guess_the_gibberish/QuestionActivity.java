package com.example.guess_the_gibberish;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private String[] gibArray, correctArray;
    private ArrayList<String> gibList, correctList;
    private TextView tView;
    private EditText eText;
    private Button next_button, submit_button;
    private int randomIndex;
    private int score;
    private Snackbar mySnackbar;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("INDEX", randomIndex);
        outState.putInt("SCORE", score);
        outState.putStringArrayList("GIB_LIST", gibList);
        outState.putStringArrayList("CORRECT_LIST", correctList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);
        setupViews();
        setupFAB();
        Random random = new Random();

        if (savedInstanceState == null) {
            gibList = new ArrayList<>(Arrays.asList(gibArray));
            correctList = new ArrayList<>(Arrays.asList(correctArray));
            randomIndex = random.nextInt(gibList.size());
        } else {
            randomIndex = savedInstanceState.getInt("INDEX");
            score = savedInstanceState.getInt("SCORE");
            gibList = savedInstanceState.getStringArrayList("GIB_LIST");
            correctList = savedInstanceState.getStringArrayList("CORRECT_LIST");
        }

        newGame(random);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if (checkGuess(randomIndex)) {
                    ++score;
                    result = "Correct!";
                    newGame(random);
                } else {
                    result = "Incorrect!";
                    eText.setText("");
                }
                mySnackbar = Snackbar.make(v, result + " \t\tScore: " + score, Snackbar.LENGTH_LONG);
                mySnackbar.show();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySnackbar = Snackbar.make(v, "Correct Phrase: " + correctList.get(randomIndex), Snackbar.LENGTH_LONG);
                mySnackbar.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame(random);
                    }
                }, 2000);

            }
        });
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.go_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupViews() {
        tView = (TextView) findViewById(R.id.question);
        eText = (EditText) findViewById(R.id.guess);
        next_button = (Button) findViewById(R.id.next_button);
        submit_button = (Button) findViewById(R.id.submit_button);
        correctArray = getResources().getStringArray(R.array.correct_phrases);
        gibArray = getResources().getStringArray(R.array.gib_phrases);
    }

    private void newGame(Random random) {
        eText.setText("");
        removeCurrentEntry();
        randomIndex = random.nextInt(gibList.size());
        newPhrase(randomIndex);
    }

    private void removeCurrentEntry() {
        correctList.remove(randomIndex);
        gibList.remove(randomIndex);
    }

    private void newPhrase(int randomIndex) {
        String randomPhrase = gibList.get(randomIndex);
        tView.setText(randomPhrase);
    }

    private boolean checkGuess(int randomIndex) {
        String guessInput = eText.getText().toString();
        String correctPhrase = correctList.get(randomIndex);
        return guessInput.equalsIgnoreCase(correctPhrase);
    }


}

