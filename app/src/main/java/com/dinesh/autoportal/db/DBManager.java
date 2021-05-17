package com.dinesh.autoportal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBManager extends SQLiteAssetHelper {

    private static final String DATABASE_NAME="android.db";
    private static final int DATABASE_VERSION=1;

    public DBManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

}
