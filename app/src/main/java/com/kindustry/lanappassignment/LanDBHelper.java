package com.kindustry.lanappassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LanDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lans.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_LANS = //a string variable for query
            "create table lans (_id integer primary key autoincrement, "
                    + "lanName text not null, description text not null,"
                    + "address text not null, city text not null, state text not null,"
                    + "zipCode text not null, locationCode text not null, locationPhone int not null,locationManager text not null, dateOfConfiguration text not null);";


    public LanDBHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creates table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LANS);
    }

    //method that upgrades the version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LanDBHelper.class.getName(),
                "Upgrading database from version "+ oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS lans");
        onCreate(db);
    }
}
