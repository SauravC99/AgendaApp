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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, StartUpPage.class);

        EditText userName = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        String messageUser = userName.getText().toString();
        String messagePass = password.getText().toString();
        intent.putExtra(USERNAME_MESSAGE, messageUser);
        intent.putExtra(PASSWORD_MESSAGE, messagePass);
        startActivity(intent);
        /*
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
         */
    }

}