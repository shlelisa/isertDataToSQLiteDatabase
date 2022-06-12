package com.example.sqlitedatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBopenHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";

    public DBopenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        return res;
//    }

    public ArrayList<StudentModel> getAllData(){
        ArrayList<StudentModel> arrayList= new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String name= cursor.getString(1);
            int age= cursor.getInt(2);

            StudentModel studentModel=new StudentModel(id,age,name);


           arrayList.add(studentModel);

        }
        return arrayList;
    }

}
