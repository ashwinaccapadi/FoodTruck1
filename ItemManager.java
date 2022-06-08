package com.example.foodtruck1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemManager extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME =
            "itemList";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MASTER_LIST = "master";
    // other constants for column names
    private static final String ID = "id";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";

    public ItemManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create full list table
    public void onCreate(SQLiteDatabase db)
    {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_MASTER_LIST +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                CATEGORY;
        sqlCreate += " text, " + DESCRIPTION;
        sqlCreate += " text, " + PRICE + " real )";
        db.execSQL(sqlCreate);
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(100, 'Container', 'Cup', '0.00' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(101, 'Container', 'Regular Cone', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(102, 'Container', 'Waffle Cone', '0.50' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(103, 'Container', 'Chocolate Covered Cone', '0.75' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(300, 'Flavor', 'Vanilla', '1.00' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(301, 'Flavor', 'Chocolate', '1.00' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(303, 'Flavor', 'Strawberry', '1.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(304, 'Flavor', 'Cookie Dough', '1.50' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(305, 'Flavor', 'Mint Chocolate Chip', '1.50' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(400, 'Topping', 'Strawberry Syrup', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(401, 'Topping', 'Chocolate Syrup', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(402, 'Topping', 'Caramel Syrup', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(403, 'Topping', 'Oreos', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(404, 'Topping', 'Gummi Bears', '0.10' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(405, 'Topping', 'M&Ms', '0.10' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(406, 'Topping', 'Reeses', '0.25' )");
        db.execSQL("insert into " + TABLE_MASTER_LIST+ " values(407, 'Topping', 'Brownie Bites', '0.50' )");

    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_MASTER_LIST );
        // Re-create table(s)
        onCreate( db );
    }

    public void insert( Item item )
    {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_MASTER_LIST;
        sqlInsert += " values( null, '" + item.getName( );
        sqlInsert += "', '" + item.getCategory( );
        sqlInsert += "', '" + item.getPrice( ) + "' )";
        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_MASTER_LIST;
        sqlDelete += " where " + ID + " = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String name, double price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_MASTER_LIST;
        sqlUpdate += " set " + DESCRIPTION + " = '" + name + "'";
        sqlUpdate += ", " + PRICE + " = '" + price + "'";
        sqlUpdate += " where id = " + id;
        db.execSQL(sqlUpdate);
        db.close();
    }

    public Item selectById( int id )
    {
        SQLiteDatabase db = this.getWritableDatabase( );
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_MASTER_LIST;
        sqlQuery += " where " + ID + " = " + id;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        // process the result of the query
        Item item = null;
        if( cursor.moveToFirst( ) )
            item = new Item(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getDouble( 3 ) );
        return item;

        // build a Item object, then return it

    }

    public ArrayList<Item> selectContainers ( )
    {
        SQLiteDatabase  containerDB = this.getWritableDatabase();
        // construct sqlQuery, a select query
        String sqlContainerQuery = "select * from " + TABLE_MASTER_LIST + " where "+  CATEGORY + " = " + " 'Container' " ;
        // call rawQuery to execute the select query
        Cursor containerCursor = containerDB.rawQuery(sqlContainerQuery, null);
        ArrayList<Item> containers = new ArrayList<Item>();
        while (containerCursor.moveToNext())
        {
            Item current = new Item(Integer.parseInt(
                    containerCursor.getString(0)),
                    containerCursor.getString(1),
                    containerCursor.getString(2),
                    containerCursor.getDouble(3));
            Log.w( "ItemManager", current.getCategory() );
            if(current.getCategory().equals("Container"));
            {
                containers.add(current);
                Log.w( "ItemManager", "container added!!!" );
            }
        }
        return containers;
    }

    public ArrayList<Item> selectFlavors ( )
    {
        SQLiteDatabase  flavorDB = this.getWritableDatabase();
        // construct sqlQuery, a select query
        String sqlContainerQuery = "select * from " + TABLE_MASTER_LIST + " where "+  CATEGORY + " = " + " 'Flavor' " ;
        // call rawQuery to execute the select query
        Cursor flavorCursor = flavorDB.rawQuery(sqlContainerQuery, null);
        ArrayList<Item> flavors = new ArrayList<Item>();
        while (flavorCursor.moveToNext())
        {
            Item current = new Item(Integer.parseInt(
                    flavorCursor.getString(0)),
                    flavorCursor.getString(1),
                    flavorCursor.getString(2),
                    flavorCursor.getDouble(3));
            Log.w( "ItemManager", current.getCategory() );
            if(current.getCategory().equals("Flavor"));
            {
                flavors.add(current);
                Log.w( "ItemManager", "flavor added!!!" );
            }
        }
        return flavors;
    }

    public ArrayList<Item> selectCupToppings ( )
    {
        SQLiteDatabase  toppingDB = this.getWritableDatabase();
        // construct sqlQuery, a select query
        String sqlContainerQuery = "select * from " + TABLE_MASTER_LIST + " where "+  CATEGORY + " = " + " 'Topping' " ;
        // call rawQuery to execute the select query
        Cursor toppingCursor = toppingDB.rawQuery(sqlContainerQuery, null);
        ArrayList<Item> toppings = new ArrayList<Item>();
        while (toppingCursor.moveToNext())
        {
            Item current = new Item(Integer.parseInt(
                    toppingCursor.getString(0)),
                    toppingCursor.getString(1),
                    toppingCursor.getString(2),
                    toppingCursor.getDouble(3));
            Log.w( "ItemManager", current.getCategory() );
            if(current.getCategory().equals("Topping"));
            {
                toppings.add(current);
                Log.w( "ItemManager", "topping added!!!" );
            }
        }
        return toppings;
    }


    public ArrayList<Item> selectAll( )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_MASTER_LIST;
        // call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Item> items = new ArrayList<Item>();
        while (cursor.moveToNext()) {
            Item currentItem = new Item(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3));
            items.add(currentItem);
        }
        db.close();
        return items;
    }
}
