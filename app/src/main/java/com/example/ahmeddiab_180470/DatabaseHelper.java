package com.example.ahmeddiab_180470;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "users_values";
    public static final String COLUMN1 = "ID";
    public static final String COLUMN2 = "FName";
    public static final String COLUMN3 = "LName";
    public static final String COLUMN4 = "PhoneNumber";
    public static final String COLUMN5 = "Email";


    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID TEXT PRIMARY KEY, " +
                " FName TEXT, LName TEXT, PhoneNumber TEXT, Email TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String id,String FName, String LName, String PhoneNumber, String Email) {
        long table;

        Cursor cur=getSpecifiedResultByID(id);

        if(cur.getCount()==1)
            return false;
        else {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN1, id);
            contentValues.put(COLUMN2, FName);
            contentValues.put(COLUMN3, LName);
            contentValues.put(COLUMN4, PhoneNumber);
            contentValues.put(COLUMN5, Email);

            table = db.insert(TABLE_NAME, null, contentValues);
        }
        if(table == -1 ) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getSpecifiedResultByID(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where ID=?  ",new String[]{String.valueOf(ID)});
        return data;
    }

    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }

    public int delete(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        int delete=0;

        long result= DatabaseUtils.queryNumEntries(db,TABLE_NAME,"id=?",new String[]{id});

        if(result>=1)
            delete=db.delete(TABLE_NAME,"id=?",new String[]{id});

        return delete;

    }

    public void update(String id, String mail){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN5,mail);

        db.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(id)});

    }

}