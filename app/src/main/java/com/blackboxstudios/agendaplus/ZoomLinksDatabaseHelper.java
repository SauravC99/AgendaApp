package com.blackboxstudios.agendaplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZoomLinksDatabaseHelper extends SQLiteOpenHelper {

    // Table and column names
    public static final String ZOOMLINKSPAGE = "ZOOM_LINKS_PAGE_TABLE";
    public static final String COLUMN_CLASS_NAME = "CLASS_NAME";
    public static final String COLUMN_ZOOM_LINK = "ZOOM_LINK";
    public static final String BUTTON_NUMBER = "BUTTON_NUMBER";

    public ZoomLinksDatabaseHelper(@Nullable Context context) {
        super(context, "AgendaPlusZoomLinks.db", null, 1);
    }

    // Called the first time the database is accessed. Code to set up DB
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + ZOOMLINKSPAGE
                + " (" + BUTTON_NUMBER + " INTEGER PRIMARY KEY, " + COLUMN_CLASS_NAME
                + " TEXT, " + COLUMN_ZOOM_LINK + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    // This is called if the database version num changes, probably won't use this
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Use getWritableDatabase when the db is changed
    // Use getReadableDatabase when data is being read

    public boolean addSingleClass(ZoomClassModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BUTTON_NUMBER, model.getButtonNum());
        cv.put(COLUMN_CLASS_NAME, model.getClassName());
        cv.put(COLUMN_ZOOM_LINK, model.getLink());

        long insert = db.insert(ZOOMLINKSPAGE, null, cv);

        db.close();
        if(insert == -1)
            return false;
        else
            return true;
    }

    public boolean deleteSingleClass(ZoomClassModel model) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + ZOOMLINKSPAGE + " WHERE " + BUTTON_NUMBER + " = " + model.getButtonNum();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public ZoomClassModel getSingleClass(int buttonNum) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ZOOMLINKSPAGE + " WHERE " + BUTTON_NUMBER + " = " + buttonNum;

        Cursor cursor = db.rawQuery(query, null);
        ZoomClassModel model = new ZoomClassModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return model;
    }

    public List<ZoomClassModel> getEverything() {

        List<ZoomClassModel> ev = new ArrayList<>();

        // Get everything from database
        String query = "SELECT * FROM " + ZOOMLINKSPAGE;

        SQLiteDatabase db = this.getReadableDatabase();

        // Gets the result set from the SQL statement
        Cursor cursor = db.rawQuery(query, null);

        // Go to the first line
        if (cursor.moveToFirst()) {
            // Go through results and make objects and add to list
            do {
                int buttonNumber = cursor.getInt(0);
                String className = cursor.getString(1);
                String link = cursor.getString(2);

                ZoomClassModel newObj = new ZoomClassModel(buttonNumber, className, link);
                ev.add(newObj);
            } while (cursor.moveToNext()); // keep going until we reach the end of the DB
        }
        else {
            // don't add anything to the list
            //remove
        }

        // close things after we finish
        cursor.close();
        db.close();
        return ev;
    }
}
