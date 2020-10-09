package com.blackboxstudios.agendaplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Locale;

public class TomatoPage extends AppCompatActivity {

    enum TimerState {
        Stop,
        Pause,
        Running
    }
    private static final long START_TIME_IN_MILLIS = 1500000;
    private CountDownTimer timer;
    private TextView countdown;
    private TimerState timerState = TimerState.Stop;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private int roundCount = 0;
    FloatingActionButton play, pause, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tomato_toolbar);
        setSupportActionBar(toolbar);

        countdown = (TextView)findViewById(R.id.text_view_countdown);
        play=(FloatingActionButton)findViewById(R.id.fab_play);
        pause=(FloatingActionButton) findViewById(R.id.fab_pause);
        stop=(FloatingActionButton) findViewById(R.id.fab_stop);
        pause.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                timerState = TimerState.Running;
            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                timerState = TimerState.Pause;
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                timerState = TimerState.Stop;
            }
        });
        updateCountdownText();
    }

    private void startTimer() {

        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                stop.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.INVISIBLE);
                timeLeftInMillis = 0;
                updateCountdownText();
                roundCount++;
                if (roundCount == 4) {
                    Toast.makeText(getApplicationContext(), "Take a 25 minute break.", Toast.LENGTH_SHORT).show();
                    roundCount = 0;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Take a 5 minute break.", Toast.LENGTH_SHORT).show();
                }
                //TODO: prompt what time to start your next round. i.e. "Take a break and come back at 8:10am"
            }
        }.start();
        play.setVisibility((View.INVISIBLE));
        stop.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.VISIBLE);;
    }

    private void pauseTimer() {
        timer.cancel();
        pause.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
    }

    private void stopTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountdownText();
        stop.setVisibility(View.INVISIBLE);

        play.setVisibility(View.VISIBLE);
        if(timeLeftInMillis == START_TIME_IN_MILLIS) {
            pause.setVisibility(View.INVISIBLE);
        }
        else {
            pause.setVisibility(View.VISIBLE);
        }
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        countdown.setText(timeLeftFormatted);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: remove background timer, hide notifications
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timerState == TimerState.Running) {
            timer.cancel();
            //TODO: start background timer, show notifications
        }
        else if(timerState == TimerState.Pause) {
            //TODO: show notifications
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tomato_menu_timer, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.help:
                Intent help = new Intent(this, HelpActivity.class);
                startActivity(help);
                break;
            case R.id.action_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            default:
        }
        //TODO: create settings page, help page
        return super.onOptionsItemSelected(item);
    }
    */
}