package com.example.foodtruck1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class PasswordManager extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME =
                "passwordList";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_PASSWORD_LIST = "password";
        // other constants for column names
        private static final String ID = "id";
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        public PasswordManager(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            // build sql create statement
            String sqlCreate = "create table " + TABLE_PASSWORD_LIST +
                    "( " + ID;
            sqlCreate += " integer primary key autoincrement, " +
                    USERNAME;
            sqlCreate += " text, " + PASSWORD + " real )";
            db.execSQL(sqlCreate);
            db.execSQL("insert into " + TABLE_PASSWORD_LIST + " values(1, 'Andy', 'password' )");
            db.execSQL("insert into " + TABLE_PASSWORD_LIST + " values(2, 'Ashwin', 'passw0rd' )");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion )
        {
            // Drop old table if it exists
            db.execSQL( "drop table if exists " + TABLE_PASSWORD_LIST );
            // Re-create table(s)
            onCreate( db );
        }

        public ArrayList<Employee> selectAll( )
        {
            SQLiteDatabase db = this.getWritableDatabase();
            // construct sqlQuery, a select query
            String sqlQuery = "select * from " + TABLE_PASSWORD_LIST;
            // call rawQuery to execute the select query
            Cursor cursor = db.rawQuery(sqlQuery, null);
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (cursor.moveToNext()) {
                Employee currentEmployee = new Employee(Integer.parseInt(
                        cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2));
                employees.add(currentEmployee);
            }
            db.close();
            return employees;
        }

    }
