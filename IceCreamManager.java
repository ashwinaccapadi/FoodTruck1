package com.example.foodtruck1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class IceCreamManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME =
            "iceCreamList";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MASTER_LIST = "master";
    // other constants for column names
    private static final String ID = "id";
    private static final String CONTAINER = "container";
    private static final String FLAVOR = "flavor";
    private static final String TOPPING = "topping";
    private static final String PRICE = "price";

    public IceCreamManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_MASTER_LIST +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                CONTAINER;
        sqlCreate += " text, " + FLAVOR;
        sqlCreate += " text, " + TOPPING;
        sqlCreate += " text, " + PRICE + " real )";
        db.execSQL(sqlCreate);
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_MASTER_LIST );
        // Re-create table(s)
        onCreate( db );
    }

    public void insert( IceCream order )
    {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_MASTER_LIST;
        sqlInsert += " values("  + order.getID() + "', '" + order.getContainer();
        sqlInsert += "', '" + order.getFlavor( );
        sqlInsert += "', '" + order.getTopping( );
        sqlInsert += "', '" + order.getPrice( ) + "' )";
        db.execSQL( sqlInsert );
        db.close( );
    }


}
