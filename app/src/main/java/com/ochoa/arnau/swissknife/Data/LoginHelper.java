package com.ochoa.arnau.swissknife.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnau on 31/01/2017.
 */

public class LoginHelper extends SQLiteOpenHelper{

    //Declaracion global de la version de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion del nombre de la base de datos
    public static final String DATABASE_NAME = "login";

    //Declaracion del nombre de la tabla
    public static final String LOGIN_TABLE ="Login";

    //sentencia global de cracion de la base de datos
    public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " + LOGIN_TABLE + " (name TEXT PRIMARY KEY UNIQUE, password TEXT);";

    public LoginHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOGIN_TABLE_CREATE);
    }

    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    public Cursor getPasswordByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"password"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
