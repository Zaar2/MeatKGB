package com.zaar2.meatKGB_w.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zaar2.meatKGB_w.R;

import java.util.ArrayList;
import java.util.Objects;

public class DB_00_database extends SQLiteOpenHelper {
    private ArrayList<String> arrTblNames;

    public DB_00_database(@Nullable Context context, int version) {
        super(
                Objects.requireNonNull(context, "context - must not be null"),
                Objects.requireNonNull(context, "context.getResources() - must not be null").getResources().getString(R.string.name_database),
                null,
                version
        );
        DB_01_value.fillNameTables(context);
        DB_01_value.fillNameColumns(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=on;");
        createTable_produced(db, DB_01_value.NAME_TABLE_meatShop);
        createTable_namesProduct(db, DB_01_value.NAME_TABLE_names_product);
        createTable_dbConnParam(db, DB_01_value.NAME_TABLE_dbConnParam);
        createTable_usr_name(db, DB_01_value.NAME_TABLE_usr_name);
    }

    private void createTable_dbConnParam(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table if not exists " + nameTable + " ("
                        + DB_01_value.dbConn_param_ID + " INTEGER primary key, "
                        + DB_01_value.dbConn_param_NAME + " TEXT NOT NULL, "
                        + DB_01_value.dbConn_param_VALUE + " TEXT NOT NULL "
                        + ");"
        );
    }

    private void createTable_produced(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table if not exists " + nameTable + " ("
                        + DB_01_value.produced_ID + " INTEGER primary key, "
                        + DB_01_value.produced_USER + " TEXT NOT NULL, "
                        + DB_01_value.produced_DATA_PRODUCED + " INTEGER NOT NULL, "
                        + DB_01_value.produced_NAME_PRODUCT + " TEXT NOT NULL, "
                        + DB_01_value.produced_COUNT + " INTEGER NOT NULL, "
                        + DB_01_value.produced_TIME_PRODUCED + " INTEGER NOT NULL "
                        + ");"
        );
    }

    private void createTable_namesProduct(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table if not exists " + nameTable + " ("
                        + DB_01_value.produced_ID + " INTEGER primary key, "
                        + DB_01_value.product_NAME_PRODUCT + " TEXT NOT NULL, "
                        + DB_01_value.product_NAME_SHOP + " TEXT, "
                        + DB_01_value.product_ME + " TEXT, "
                        + DB_01_value.product_ACCURACY + " TEXT "
                        + ");"
        );
    }

    private void createTable_usr_name(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table if not exists " + nameTable + " ("
                        + DB_01_value.usr_name_usr_id + " INTEGER primary key, "
                        + DB_01_value.usr_name_name_full + " TEXT, "
                        + DB_01_value.usr_name_name_short + " TEXT, "
                        + DB_01_value.usr_name_user_login + " TEXT, "
                        + DB_01_value.usr_name_appointment + " TEXT, "
                        + DB_01_value.usr_name_workshop + " TEXT, "
                        + DB_01_value.usr_name_pass + " TEXT "
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DB_02_utility_common.dropTables(db);
        onCreate(db);
    }
}