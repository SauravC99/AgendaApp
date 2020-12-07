package com.blackboxstudios.agendaplus;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity {

    private static final String TAG = "ClassScheduleActivity";

    private RecyclerView recyclerView;
    private Button btnAddClass;
    private Button btnDeleteClass;
    private ArrayList<String> mClassStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule_layout);

        btnAddClass = (Button) findViewById(R.id.btnAddClass);
        recyclerView = (RecyclerView) findViewById(R.id.rvClassSchedule);
        btnDeleteClass = (Button) findViewById(R.id.btnDeleteClass);

        getAllClasses();

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassToScheduleActivity.class);
                startActivity(intent);
            }
        });
        btnDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassScheduleActivity.this, DeleteClassFromScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
     * Gets a list of all classes in the database and displays them in the RecyclerView.
     */
    private void getAllClasses() {
        ClassDatabaseHelper dbHelper = new ClassDatabaseHelper(ClassScheduleActivity.this);
        List<ClassModel> allClasses = dbHelper.getAllClasses();
        for (int i = 0; i < allClasses.size(); i++) {
            String newClass = allClasses.get(i).toString();
            mClassStrings.add(newClass);
        }
        initRecyclerView();
    }

    // Initializes the RecyclerView layout.
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvClassSchedule);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mClassStrings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}