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


    //in theory, create intent for Guest
    //allows guest to type name
    //moves over
    //starts intent activity
    public void getGuestName(View view) {
        Intent intentGuest = new Intent(this, StartUpPage.class);

        EditText guestName = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String messageUser = guestName.getText().toString();
        intentGuest.putExtra(GUESTNAME_MESSAGE, messageUser);
        startActivity(intentGuest);
    }
}