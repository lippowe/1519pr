package com.example.a0103pr1519.data_base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DataBaseHelper extends SQLiteOpenHelper{
    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseConstant.DATA_BASE_NAME, null, DataBaseConstant.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_SERVICES);
        sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseConstant.DELETE_TABLE_USERS);
        sqLiteDatabase.execSQL(DataBaseConstant.DELETE_TABLE_SERVICES);
        sqLiteDatabase.execSQL(DataBaseConstant.DETELE_TABLE_ORDERS);
        onCreate(sqLiteDatabase);
    }
}
