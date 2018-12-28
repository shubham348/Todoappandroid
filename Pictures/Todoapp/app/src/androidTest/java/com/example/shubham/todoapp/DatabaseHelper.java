package com.example.shubham.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * database variable
     */
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_item = "item";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_NAME = "todo_table";
    private int id;
    private String item;
    private String timestamp;
    /**
     * constructor  when called creates a database with given name
     */
    public DatabaseHelper( Context context) {
        super(context,  DATABASE_NAME, null,1);
    }



    /**
     * creating database
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE "  + TABLE_NAME +"( id INTEGER PRIMARY KEY AUTOINCREMENT, item Text ,timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    /**
     * inserting data in database
     */

    public boolean insertdata(String item ,Context context){
        SQLiteDatabase db;
        db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_item,item);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result !=-1){
            Toast.makeText(context,"row added",Toast.LENGTH_LONG);
        }
        /**
         * returns -1 when data not inserted
         *
         *
         * String text2=userlist.getItemAtPosition(2).toString();
         *  arrlist.add(cursor.getString(cursor.getColumnIndex(String.valueOf(2))));
         */

        if(result == -1)
            return false;
        else
            return true;
    }



      public Cursor viewdata() {
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor;
          cursor = db.rawQuery("select * from " + TABLE_NAME, null);
          return cursor;
      }



}
