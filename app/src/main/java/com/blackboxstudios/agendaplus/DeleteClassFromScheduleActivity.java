package com.blackboxstudios.agendaplus;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DeleteClassFromScheduleActivity extends AppCompatActivity {

    private static final String TAG = "DeleteClassFromScheduleActivity";

    private EditText etDeleteClassName;
    private Button btnSubmitDelete;
    private TextView tvDeleteClass;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class_from_schedule_layout);
        etDeleteClassName = (EditText) findViewById(R.id.et_delete_class_name);
        btnSubmitDelete = (Button) findViewById(R.id.btnSubmitDelete);
        tvDeleteClass = (TextView) findViewById(R.id.tvDeleteClass);

        className = etDeleteClassName.getText().toString();

        /*
         * Deletes the class with the specified name from the database, if it is found.
         * Displays a toast that notifies whether the object was saved to the DB successfully.
         */
        btnSubmitDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDatabaseHelper dbHelper = new ClassDatabaseHelper(DeleteClassFromScheduleActivity.this);
                boolean test = dbHelper.deleteClass(className);
                Toast.makeText(DeleteClassFromScheduleActivity.this, "Success: " + test, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeleteClassFromScheduleActivity.this, ClassScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}