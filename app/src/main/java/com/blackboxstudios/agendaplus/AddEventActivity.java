package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private static final String TAG = "AddEventActivity";

    private EditText eventDesc;
    private EditText eventTime;
    private Button btnSubmitEvent;
    private TextView tvDate;
    /*
    public long stringToLong(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date = sdf.parse(dateString);
            long long_date = date.getTime();
            return long_date;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_layout);
        eventDesc = (EditText) findViewById(R.id.addEventDesc);
        eventTime = (EditText) findViewById(R.id.addEventTime);
        btnSubmitEvent = (Button) findViewById(R.id.btnSubmitEvent);
        tvDate = (TextView) findViewById(R.id.tvDate);

        String date = getIntent().getStringExtra("date");

        tvDate.setText(date);

        btnSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventModel eventModel;
                try {
                    // String dateString = tvDate.getText().toString();
                    //long dateLong = stringToLong(dateString);
                    eventModel = new EventModel(-1, tvDate.getText().toString(), eventDesc.getText().toString(), eventTime.getText().toString());
                    Toast.makeText(AddEventActivity.this, eventModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(AddEventActivity.this, "Error adding to the database.", Toast.LENGTH_SHORT).show();
                    eventModel = new EventModel(-1, "Date Error", "Description Error", "Time Error");
                }
                DatabaseHelper dbHelper = new DatabaseHelper(AddEventActivity.this);
                boolean test = dbHelper.addOne(eventModel);
                Toast.makeText(AddEventActivity.this, "Success: " + test, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddEventActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}

