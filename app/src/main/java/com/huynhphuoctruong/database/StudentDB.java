package com.huynhphuoctruong.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentDB extends SQLiteOpenHelper {
    public final static String DB_NAME = "Student.sqlite";
    public final static int DB_VERSION = 1;
    public final static String TB_NAME = "Student";
    public final static String COL_ID = "StudentId";
    public final static String COL_NAME = "StudentName";
    public final static String COL_CLASS = "StudentClass";

    public StudentDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" + COL_ID + " VARCHAR(200) PRIMARY KEY, " + COL_NAME + " NVARCHAR(200), " + COL_CLASS + " NVARCHAR(200))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public void execQuery(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public int getAmount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void generateDB(){
        int count = this.getAmount();
        if(count <= 0){
            this.execQuery("INSERT INTO " + TB_NAME + " VALUES('6051071134', 'Huynh Phuoc Truong', 'Cong nghe thong tin')");
            this.execQuery("INSERT INTO " + TB_NAME + " VALUES('6051071135', 'Phan Van Tuyen', 'Cong nghe thong tin')");
            this.execQuery("INSERT INTO " + TB_NAME + " VALUES('6051071109', 'Chau Thanh Phat', 'Cong nghe thong tin')");
        }
    }
}
