package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StartUpPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_page);

        // Get the Intent that started this activity and and find where it came from
        Intent intent = getIntent();
        String cameFrom = intent.getStringExtra("c");

        //Using where it came from this will populate the TextViews accordingly
        if (cameFrom.equals("LogInPage")) {
            String userName = intent.getStringExtra(LogInPage.USERNAME_MESSAGE);
            String pass = intent.getStringExtra(LogInPage.PASSWORD_MESSAGE);

            // Capture the layout's TextView and set the string as its text
            TextView textView = findViewById(R.id.textView);
            textView.setText(userName);

            TextView textView2 = findViewById(R.id.textView1);
            textView2.setText(pass);
        }
        if (cameFrom.equals("GuestLogInPage")) {
            String guestName = intent.getStringExtra(GuestLogInPage.GUESTNAME_MESSAGE);

            // Capture the layout's TextView and set the string as its text
            TextView textView = findViewById(R.id.textView);
            textView.setText(guestName);

            TextView textView2 = findViewById(R.id.textView1);
            textView2.setText("");
        }

    }
}