package com.blackboxstudios.agendaplus;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String EVENTS_TABLE = "EVENTS_TABLE";
    public static final String COLUMN_EVENT_ID = "EVENT_ID";
    public static final String COLUMN_EVENT_DATE = "EVENT_DATE";
    public static final String COLUMN_EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
    public static final String COLUMN_EVENT_TIME = "EVENT_TIME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "events.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + EVENTS_TABLE + " (" + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EVENT_DATE + " TEXT, " + COLUMN_EVENT_DESCRIPTION + " TEXT, " + COLUMN_EVENT_TIME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(EventModel eventModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EVENT_DATE, eventModel.getDate());
        cv.put(COLUMN_EVENT_DESCRIPTION, eventModel.getDescription());
        cv.put(COLUMN_EVENT_TIME, eventModel.getTime());

        long insert = db.insert(EVENTS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<EventModel> getAll() {
        List<EventModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + EVENTS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String description = cursor.getString(2);
                String time = cursor.getString(3);

                EventModel newEvent = new EventModel(id, date, description, time);
                returnList.add(newEvent);

            } while (cursor.moveToNext());
        }
        else {
            // do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteEvent(EventModel event) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + EVENTS_TABLE + " WHERE " + COLUMN_EVENT_DESCRIPTION + " = " + event.getDescription();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<String> getAllDateEvents(String selectedDate) {
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + EVENTS_TABLE + " WHERE " + COLUMN_EVENT_DATE + " ='" + selectedDate + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String description = cursor.getString(2);
                String time = cursor.getString(3);

                EventModel newEvent = new EventModel(id, date, description, time);
                String newEventString = newEvent.toString();
                returnList.add(newEventString);

            } while (cursor.moveToNext());
        }
        else {
            System.out.println("No results found");
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
