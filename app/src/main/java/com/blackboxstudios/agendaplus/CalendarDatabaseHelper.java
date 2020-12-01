package com.blackboxstudios.agendaplus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class CalendarDatabaseHelper extends SQLiteOpenHelper {

    public static final String EVENTS_TABLE = "EVENTS_TABLE";
    public static final String COLUMN_EVENT_ID = "EVENT_ID";
    public static final String COLUMN_EVENT_DATE = "EVENT_DATE";
    public static final String COLUMN_EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
    public static final String COLUMN_EVENT_TIME = "EVENT_TIME";

    public CalendarDatabaseHelper(@Nullable Context context) {
        super(context, "events.db", null, 1);
    }

    // Creates the Database Table for the CalendarEventModel objects.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + EVENTS_TABLE + " (" + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EVENT_DATE + " TEXT, " + COLUMN_EVENT_DESCRIPTION + " TEXT, " + COLUMN_EVENT_TIME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Adds a new CalendarEventModel object to the database.
    public boolean addOne(CalendarEventModel calendarEventModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EVENT_DATE, calendarEventModel.getDate());
        cv.put(COLUMN_EVENT_DESCRIPTION, calendarEventModel.getDescription());
        cv.put(COLUMN_EVENT_TIME, calendarEventModel.getTime());

        long insert = db.insert(EVENTS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Returns all CalendarEventModel objects in the database.
    public List<CalendarEventModel> getAll() {
        List<CalendarEventModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + EVENTS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String description = cursor.getString(2);
                String time = cursor.getString(3);

                CalendarEventModel newEvent = new CalendarEventModel(id, date, description, time);
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

    // Deletes the CalendarEventModel Object of a given description.
    public boolean deleteEvent(CalendarEventModel event) {
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

    // Returns the CalendarEventModel objects of the date selected by the user in the CalendarActivity class.
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

                CalendarEventModel newEvent = new CalendarEventModel(id, date, description, time);
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
