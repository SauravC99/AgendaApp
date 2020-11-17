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

    
}
