package com.blackboxstudios.agendaplus;

import android.view.View;
import android.widget.CalendarView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private RecyclerView recyclerView;
    private ImageButton btnAddNewEvent;
    private ArrayList<String> mEventStrings = new ArrayList<>();
    private String dateString;
    private long long_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_layout);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnAddNewEvent = (ImageButton) findViewById(R.id.btnAddNewEvent);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                mEventStrings.clear();
                dateString = (month+1) + "/" + day + "/" + year;
                getDateEvents(dateString);
            }
        });

        btnAddNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, CalendarAddEventActivity.class);
                intent.putExtra("date", dateString);
                startActivity(intent);
            }
        });
    }

    private void getAll() {
        CalendarDatabaseHelper dbHelper = new CalendarDatabaseHelper(CalendarActivity.this);
        List<CalendarEventModel> dateEvents = dbHelper.getAll();
        for (int i = 0; i < dateEvents.size(); i++) {
            String event = dateEvents.get(i).toString();
            mEventStrings.add(event);
        }
        initRecyclerView();
    }

    private void getDateEvents(String dateString) {
        CalendarDatabaseHelper dbHelper = new CalendarDatabaseHelper(CalendarActivity.this);
        List<String> dateEvents = dbHelper.getAllDateEvents(dateString);
        for (int i = 0; i < dateEvents.size(); i++) {
            mEventStrings.add(dateEvents.get(i));
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mEventStrings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
