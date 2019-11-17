package com.jonathan.proyectofinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarerEventsManager extends SQLiteOpenHelper {

    private String sql="create table eventsCarer(" +
            "eventId integer primary key autoincrement,"+
            "eventName varchar(50),"+
            "eventLocation varchar(60),"+
            "eventDate date,"+
            "eventStartHour time,"+
            "eventEndHour time,"+
            "eventDescription varchar(60)" +
            ")";

    public CarerEventsManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
