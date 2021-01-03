package com.example.guess_the_gibberish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_end_game);
        setUpItems();
    }

    private void setUpItems() {
        Button btn = (Button) findViewById(R.id.go_home);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        TextView tView = (TextView) findViewById(R.id.end);
    }
    private void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
