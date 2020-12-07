package com.blackboxstudios.agendaplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button one = (Button)findViewById(R.id.calc);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button)findViewById(R.id.zoom);
        two.setOnClickListener(this);
        Button three = (Button)findViewById(R.id.calendar);
        three.setOnClickListener(this);
        Button four = (Button)findViewById(R.id.timer);
        four.setOnClickListener(this);
        Button five = (Button)findViewById(R.id.alarm);
        five.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.calc:
                intent = new Intent(this, CalculatorActivity.class);
                break;

            case R.id.zoom:
                intent = new Intent(this, ZoomLinksPage.class);
                break;

            case R.id.calendar:
                intent = new Intent(this, CalendarActivity.class);
                break;

            case R.id.timer:
                intent = new Intent(this, TomatoPage.class);
                break;

            case R.id.alarm:
                intent = new Intent(this, AlarmPage.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
