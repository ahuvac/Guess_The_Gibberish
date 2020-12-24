package com.example.guess_the_gibberish;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);
        setQuestion();
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

