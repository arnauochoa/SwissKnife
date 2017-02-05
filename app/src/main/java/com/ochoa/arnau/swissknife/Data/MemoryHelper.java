package com.ochoa.arnau.swissknife.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ochoa.arnau.swissknife.R;

/**
 * Created by arnau on 05/02/2017.
 */

public class MemoryHelper extends SQLiteOpenHelper{
    //Declaracion global de la version de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion del nombre de la base de datos
    public static final String DATABASE_NAME = String.valueOf(R.string.app_name);

    //Declaracion del nombre de la tabla
    public static final String SCORES_TABLE ="Scores";

    //sentencia global de cracion de la base de datos
    public static final String SCORES_TABLE_CREATE = "CREATE TABLE " + SCORES_TABLE +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, level INTEGER, score INTEGER);";

    public MemoryHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCORES_TABLE_CREATE);
    }

    public void addScore (ContentValues values) { //values -> username, level, score
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                SCORES_TABLE,
                null,
                values);
    }

    public Cursor getBestScoreByName (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"MAX(score)"};
        String[] where = {username};
        Cursor c = db.query(
                SCORES_TABLE,  // The table to query
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
