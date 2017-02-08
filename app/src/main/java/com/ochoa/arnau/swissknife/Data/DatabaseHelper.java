package com.ochoa.arnau.swissknife.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ochoa.arnau.swissknife.R;

/**
 * Created by arnau on 31/01/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //Declaracion global de la version de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion del nombre de la base de datos
    public static final String DATABASE_NAME = String.valueOf(R.string.app_name);

    //Declaracion del nombre de la tabla
    public static final String USERS_TABLE ="Users";


    //Declaracion del nombre de la tabla
    public static final String SCORES_TABLE ="Scores";

    //sentencia global de cracion de la base de datos
    public static final String USERS_TABLE_CREATE = "CREATE TABLE " + USERS_TABLE +
            " (name TEXT PRIMARY KEY UNIQUE, password TEXT, address TEXT, image TEXT);";

    //sentencia global de creacion de la base de datos
    public static final String SCORES_TABLE_CREATE = "CREATE TABLE " + SCORES_TABLE +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, level STRING, score INTEGER);";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);
        db.execSQL(SCORES_TABLE_CREATE);
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
                USERS_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    public Cursor checkIfExists(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"1"};
        String[] where = {name};
        Cursor c = db.query(
                USERS_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    public void addScore (ContentValues values) { //values -> username, level, score
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                SCORES_TABLE,
                null,
                values);
    }

    public Cursor getBestScoreByName (String username, String level){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"score"};
        String[] where = {username, level};
        Cursor c = db.query(
                SCORES_TABLE,  // The table to query
                columns,         // The columns to return
                "name=? AND level=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                "score ASC"             // The sort order
        );
        return c;
    }

    public Cursor getRankingByLevel (String level, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"name", "score"};
        String[] where = {level};
        Cursor c = db.query(
                tableName,  // The table to query
                columns,         // The columns to return
                "level=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                "score ASC"      // The sort order
        );
        return c;
    }

    public boolean clearRankingByLevel(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {level};
        return db.delete(
                SCORES_TABLE,
                "level=?",
                where) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
