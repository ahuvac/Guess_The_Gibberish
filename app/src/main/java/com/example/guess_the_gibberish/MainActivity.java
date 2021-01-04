package com.example.guess_the_gibberish;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import lib.Utils;

import static lib.Utils.showInfoDialog;

public class MainActivity extends AppCompatActivity {
    Button button;
    private String mKEY_NIGHT_MODE;
    private boolean mUseNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        mKEY_NIGHT_MODE = getString(R.string.night_mode_key);
        Utils.setNightModeOnOffFromPreferenceValue(getApplicationContext(), mKEY_NIGHT_MODE);
        mUseNightMode = Utils.isNightModePrefOn(this, mKEY_NIGHT_MODE);
        setUpButtons();
        setupToolbar();
        setupFAB();
    }

    private void setUpButtons() {
        button = (Button) findViewById(R.id.play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.night_mode).setChecked(mUseNightMode);
        return super.onPrepareOptionsMenu((menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            showAbout();
            return true;
        }
        else if (id == R.id.start_game) {
            openNewActivity();
            return true;
        }
        else if (id == R.id.night_mode) {
            Log.d("Night" , " Night Mode: " + mUseNightMode);
            handleNightModeClick(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleNightModeClick(MenuItem item) {
        item.setChecked(!item.isChecked());
        mUseNightMode = item.isChecked();
        Log.d("Night" , " Night Mode: " + mUseNightMode);
        Utils.setNightModeOnOrOff(mUseNightMode);
    }

    private void showAbout() {
        showInfoDialog(MainActivity.this, getString(R.string.app_name), getString(R.string.info_about));
    }


    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo();
            }
        });
    }

    private void showInfo() {
        showInfoDialog(MainActivity.this, getString(R.string.info_title), getString(R.string.info_description));
    }
}
