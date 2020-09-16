package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GuestLogInPage extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.blackboxstudios.agendaplus.MESSAGE"; //what is this for?
    public static final String GUESTNAME_MESSAGE = "guestname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_log_in_page);
    }

    public void getGuestName(View view) {
        Intent intent = new Intent(this, StartUpPage.class);

        EditText userName = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        String messageUser = userName.getText().toString();
        String messagePass = password.getText().toString();
        intent.putExtra(USERNAME_MESSAGE, messageUser);
        startActivity(intent);
    }
}