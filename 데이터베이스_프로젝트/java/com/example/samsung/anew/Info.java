package com.example.samsung.anew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Database를 open 시켜주고 각 class에 database를 extends 시켜주는 역할을 한다.
 */


public class Info extends ActionBarActivity {
    public static final String TAG = "Info";
    private static String DATABASE_NAME = "db";
    private static int DATABASE_VERSION = 1;
    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boolean isOpen = openDatabase();//Database를 오픈시킨다.
    }

    /**
     * database open
     **/
    public boolean openDatabase() {
        println("opening database [" + DATABASE_NAME + "].");

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    void println(String msg) {
        Log.d(TAG, msg);
    }

    /**
     * Database control API
     * Database control을 쉽게 도와주는 기능
     */
    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Database 생성
         */
        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }

        /**
         * Database open
         */
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");

        }

        /**
         * Database upgrade
         **/
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        }
    }
}