package com.blackboxstudios.agendaplus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ClassDatabaseHelper extends SQLiteOpenHelper {

    public static final String CLASS_TABLE = "CLASS_TABLE";
    public static final String COLUMN_CLASS_ID = "CLASS_ID";
    public static final String COLUMN_CLASS_DAYS = "CLASS_DAYS";
    public static final String COLUMN_CLASS_START_TIME = "CLASS_START_TIME";
    public static final String COLUMN_CLASS_END_TIME = "CLASS_END_TIME";
    public static final String COLUMN_CLASS_NAME = "CLASS_NAME";

    public ClassDatabaseHelper(@Nullable Context context) {
        super(context, "classesTable.db", null, 1);
    }

    // Creates the Database Table for the ClassModel objects.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CLASS_TABLE + " (" + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CLASS_DAYS + " TEXT, " + COLUMN_CLASS_START_TIME + " TEXT, " + COLUMN_CLASS_END_TIME + " TEXT, " + COLUMN_CLASS_NAME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Adds a new ClassModel object to the database.
    public boolean addOne(ClassModel addClassModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLASS_DAYS, addClassModel.getClassDays());
        cv.put(COLUMN_CLASS_START_TIME, addClassModel.getStartTime());
        cv.put(COLUMN_CLASS_END_TIME, addClassModel.getEndTime());
        cv.put(COLUMN_CLASS_NAME, addClassModel.getClassName());

        long insert = db.insert(CLASS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Returns all ClassModel objects in the database.
    public List<ClassModel> getAllClasses() {
        List<ClassModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CLASS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String days = cursor.getString(1);
                String start = cursor.getString(2);
                String end = cursor.getString(3);
                String name = cursor.getString(4);

                ClassModel newClass = new ClassModel(id, days, start, end, name);
                returnList.add(newClass);

            } while (cursor.moveToNext());
        }
        else {
            // do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // Deletes the ClassModel Object of a given description.
    /*
    public boolean deleteClass(ClassModel newClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CLASSES_TABLE + " WHERE " + COLUMN_CLASS_NAME + " = " + newClass.getClassName();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }
     */
    public boolean deleteClass(String class_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CLASS_TABLE + " WHERE " + COLUMN_CLASS_NAME + " = " + class_name;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }
}
