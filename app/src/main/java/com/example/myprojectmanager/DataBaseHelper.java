package com.example.myprojectmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "projectManager.db";
    private static final String TABLE_NAME = "tasks";
    private static final String COL1 = "date";
    private static final String COL2 = "duration";
    private static final String COL3 = "task";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " TEXT,  " + COL2 + " TEXT, " + COL3 + " TEXT)"; //date DATE, duration TIME, task TEXT
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData(String date, String duration, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, date);
        contentValues.put(COL2, duration);
        contentValues.put(COL3, task);

        String sql = "INSERT INTO " + TABLE_NAME + " (date, duration, task) VALUES ('" + date + "','" + duration + "','" + task + "')";

        Log.d(TAG, "addData: " + sql); //adding item to table

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     *
     * @return
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Returns date that matches the task passed in
     *
     * @param task
     * @return
     */
    public Cursor getDate(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL3 + " = '" + task + "'";
        Cursor date = db.rawQuery(query, null);
        return date;
    }

    /**
     * Returns duration that matches the task passed in
     *
     * @param task
     * @return
     */
    public Cursor getDuration(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME +
                " WHERE " + COL3 + " = '" + task + "'";
        Cursor duration = db.rawQuery(query, null);
        return duration;
    }

    /**
     * Returns task that matches the task passed in
     *
     * @return
     */
    public Cursor getTask() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL3 + " FROM " + TABLE_NAME;
        Cursor task = db.rawQuery(query, null);
        return task;
    }

    /**
     * Updates the task field
     *
     * @param newTask
     * @param oldTask
     * @param
     * @param
     */
    public void updateTask(String oldTask, String oldDuration, String oldDate, String newTask, String newDuration, String newDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL3 + "='" + newTask +"' WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
/*
        String queryTask = "UPDATE" + TABLE_NAME + "SET " + COL1 + "= '" + newDate + "', "+COL2+"= '"+newDuration+"', "+ COL3 + "= '" + newTask + "' WHERE " + COL1 + "= '" + oldDate + "' AND " + COL2 + "='" + oldDuration + "' AND " + COL3 + " = '" + oldTask + "'";
        Log.d(TAG, "updateTask: query: " + queryTask);
        Log.d(TAG, "updateTask: Setting name to " + newTask);
        db.execSQL(queryTask);*/
    }

    /**
     * Updates the date field
     *
     * @param oldDuration
     * @param oldTask
     * @param newDate
     * @param oldDate
     */
    public void updateDate(String oldTask, String oldDuration, String oldDate, String newDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL1 + "='" + newDate + "' WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
/*
        String queryDate = "UPDATE" + TABLE_NAME + "SET " + COL1 + "= '" + newDate + "' WHERE " + COL1 + "= '" + oldDate + "' AND " + COL2 + "='" + oldDuration + "' AND " + COL3 + " = '" + oldTask + "'";
        Log.d(TAG, "updateDate: query: " + queryDate);
        Log.d(TAG, "updateDate: Setting new date");
        db.execSQL(queryDate);*/
    }

    /**
     * Updates the duration field
     *
     * @param oldDate
     * @param oldTask
     * @param oldDuration
     * @param newDuration
     */
    public void updateDuration(String oldTask, String oldDuration, String oldDate, String newDuration) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL2 + "='" + newDuration + "' WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
/*
        String queryDuration = "UPDATE" +TABLE_NAME + "SET "+COL2+"= '"+newDuration+"' WHERE "+COL1 + "= '"+oldDate+"' AND "+COL2 +"='" +oldDuration+"' AND "+ COL3 + " = '" + oldTask + "'" ;
        Log.d(TAG, "updateDuration: query: " + queryDuration);
        Log.d(TAG, "updateDuration: Setting new duration");
        db.execSQL(queryDuration);*/
    }


    /**
     * Update Table
     */
    public void updateAll(String oldTask, String oldDuration, String oldDate,String newTask, String newDuration, String newDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL1 + "='" + newDate + "' WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL2 + "='" + newDuration + " 'WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL3 + "='" + newTask +" 'WHERE "+COL1+"='"+oldDate+"' AND "+COL2+"='"+oldDuration+"' AND "+COL3+"='"+oldTask+"'");
        /*ContentValues content = new ContentValues();
        content.put(COL1, newDate);
        content.put(COL2,newDuration);
        content.put(COL3, newTask);
        int result = db.update(TABLE_NAME, content, COL1 + "=" + oldDate + "' AND " + COL2 + "='" + oldDuration + "' AND " + COL3 + " = '" + oldTask, null);
        if(result ==0)
        {
            return false;
        }else
        {
            return true;
        }*/
    }




    /**
     * Delete from database
     * @param task
     */
    public void deleteTask(String task, String date, String duration){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE "+COL1+"='"+date+"' AND "+COL2+"='"+duration+"' AND "+COL3+"='"+task+"'");

    }
}
