package com.sc4ever.bornali.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper{
    //table names
    private static final String TABLE_CARDS = "cards";
    private static final String TABLE_CAT = "cats";
    //cats attributes
    private static final String KEY_ROWID = "_id";
    private static final String KEY_CAT = "cat";
    //cards attributes
    private static final String KEY_URI = "uri";
    private static final String KEY_DESC = "descr";
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_CATS = "create table " + TABLE_CAT + "(" + KEY_ROWID
            + " integer primary key autoincrement," + KEY_CAT + " text not null" + ");";
    private static final String CREATE_TABLE_CARDS = "create table " + TABLE_CARDS + "(" +
            KEY_ROWID + " integer," + KEY_URI + " text not null,"
            + KEY_DESC + " text not null," + "foreign key " + "(" + KEY_ROWID + ")" + " references "
            + TABLE_CAT + "(" + KEY_ROWID + ")" + ");";

    public DBAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_CATS);
            sqLiteDatabase.execSQL(CREATE_TABLE_CARDS);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CAT);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CARDS);
        onCreate(sqLiteDatabase);
    }
}
