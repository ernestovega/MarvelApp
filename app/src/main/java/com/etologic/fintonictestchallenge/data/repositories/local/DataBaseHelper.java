package com.etologic.fintonictestchallenge.data.repositories.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //region Constants

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "heroes.db";

    //endregion

    //region Fields

    private static DataBaseHelper instance;

    //endregion

    //region Constructors

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context.getApplicationContext());
        return instance;
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HeroesDBTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(HeroesDBTable.DROP_TABLE);
    }

    //endregion
}
