package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CalendarAddEventActivity extends AppCompatActivity {

    private static final String TAG = "AddEventActivity";

    private EditText eventDesc;
    private EditText eventTime;
    private Button btnSubmitEvent;
    private TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add_event_layout);
        eventDesc = (EditText) findViewById(R.id.addEventDesc);
        eventTime = (EditText) findViewById(R.id.addEventTime);
        btnSubmitEvent = (Button) findViewById(R.id.btnSubmitEvent);
        tvDate = (TextView) findViewById(R.id.tvDate);

        String date = getIntent().getStringExtra("date");

        tvDate.setText(date);

        /*
        * Creates a new CalendarEventModel object when the save button is clicked.
        * Displays a toast that notifies whether the object was saved to the DB successfully.
        */
        btnSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarEventModel calendarEventModel;
                try {
                    calendarEventModel = new CalendarEventModel(-1, tvDate.getText().toString(), eventDesc.getText().toString(), eventTime.getText().toString());
                    Toast.makeText(CalendarAddEventActivity.this, calendarEventModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(CalendarAddEventActivity.this, "Error adding to the database.", Toast.LENGTH_SHORT).show();
                    calendarEventModel = new CalendarEventModel(-1, "Date Error", "Description Error", "Time Error");
                }
                CalendarDatabaseHelper dbHelper = new CalendarDatabaseHelper(CalendarAddEventActivity.this);
                boolean test = dbHelper.addOne(calendarEventModel);
                Toast.makeText(CalendarAddEventActivity.this, "Success: " + test, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CalendarAddEventActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}

