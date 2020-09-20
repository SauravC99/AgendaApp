package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GuestLogInPage extends AppCompatActivity {

    public static final String GUESTNAME_MESSAGE = "guestname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_log_in_page);
    }


    //create intent for Start Up Page
    //grab data put in by user
    //sends it to next page
    public void getGuestName(View view) {
        Intent intent = new Intent(this, StartUpPage.class);
        EditText guestName = (EditText) findViewById(R.id.guestName);
        String messageGuest = guestName.getText().toString();
        intent.putExtra(GUESTNAME_MESSAGE, messageGuest);
        startActivity(intent);
    }
}