package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StartUpPage extends AppCompatActivity {

    //String example = "This is a test to insert a string into a textView\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String userName = intent.getStringExtra(LogInPage.USERNAME_MESSAGE);
        String pass = intent.getStringExtra(LogInPage.PASSWORD_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(userName);

        TextView textView2 = findViewById(R.id.textView1);
        textView2.setText(pass);

        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(example + message);
         */
    }
}