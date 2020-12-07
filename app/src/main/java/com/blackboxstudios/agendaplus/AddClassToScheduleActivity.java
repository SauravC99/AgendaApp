package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AddClassToScheduleActivity extends AppCompatActivity {

    private static final String TAG = "AddClassToScheduleActivity";

    private EditText etClassStartTime;
    private EditText etClassEndTime;
    private EditText etClassName;
    private Button btnSaveClass;
    private TextView tvClassInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add_class_layout);
        etClassStartTime = (EditText) findViewById(R.id.et_class_start_time);
        etClassEndTime = (EditText) findViewById(R.id.et_class_end_time);
        etClassName = (EditText) findViewById(R.id.et_class_name);
        btnSaveClass = (Button) findViewById(R.id.btnSubmitClass);
        tvClassInfo = (TextView) findViewById(R.id.tvAddClass);

        /*
         * Creates a new ClassModel object when the save button is clicked.
         * Displays a toast that notifies whether the object was saved to the DB successfully.
         */
        btnSaveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassModel newClass;
                try {
                    newClass = new ClassModel(-1, etClassStartTime.getText().toString(), etClassEndTime.getText().toString(), etClassName.getText().toString());
                    Toast.makeText(AddClassToScheduleActivity.this, newClass.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(AddClassToScheduleActivity.this, "Error adding to the database.", Toast.LENGTH_SHORT).show();
                    newClass = new ClassModel(-1, "Start Time Error", "End Time Error", "Class Name Error");
                }
                ClassDatabaseHelper dbHelper = new ClassDatabaseHelper(AddClassToScheduleActivity.this);
                boolean test = dbHelper.addOne(newClass);
                Toast.makeText(AddClassToScheduleActivity.this, "Success: " + test, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddClassToScheduleActivity.this, ClassScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}

