package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInPage extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.blackboxstudios.agendaplus.MESSAGE";
    public static final String USERNAME_MESSAGE = "user";
    public static final String PASSWORD_MESSAGE = "pass";
    public static final String CAME_FROM = "c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
    }

    /** Called when the user taps the Sign In button */
    public void signIn(View view) {
        //Intent intent = new Intent(this, StartUpPage.class);
        Intent intent = new Intent(this, HomePage_Navigation.class);
        //ed edited - change to work onto homepage 11/26

        //Intent intent = new Intent(this, CalendarActivity.class);
        EditText userName = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        String messageUser = userName.getText().toString();
        String messagePass = password.getText().toString();
        //intent.putExtra(USERNAME_MESSAGE, messageUser);
        //intent.putExtra(PASSWORD_MESSAGE, messagePass);

        //This is so we know that this activity called the intent
        //intent.putExtra(CAME_FROM, "LogInPage");

        startActivity(intent);
    }

    /** Called when the user taps the Guest Sign In button */
    public void goToGuestLogIn(View view) {
        Intent intent = new Intent(this, GuestLogInPage.class);
        //For testing
       // Intent intent = new Intent(this, Calculator.class);
        //Intent intent = new Intent(this, GuestLogInPage.class);
        //Intent intent = new Intent(this, ZoomLinksPage.class);
        startActivity(intent);
    }


}