package com.zaar2.meatKGB_w.DB;

import android.content.Context;

import com.zaar2.meatKGB_w.R;

public class DB_01_value {

    protected static String NAME_TABLE_meatShop;
    protected static String NAME_TABLE_names_product;
    protected static String NAME_TABLE_dbConnParam;
    protected static String NAME_TABLE_usr_name;
    protected static String NAME_TABLE_coldShop;

    protected static String[][] ROWS_and_VALUES_TABLE_typeNds;
    protected static String[][] ROWS_and_VALUES_TABLE_operationType;

    protected static String ID = "_id";
    protected static String NAME = "Name";
    protected static String VALUE = "Value";

    protected static String produced_ID;
    protected static String produced_USER;
    protected static String produced_DATA_PRODUCED;
    protected static String produced_NAME_PRODUCT;
    protected static String produced_COUNT;
    protected static String produced_TIME_PRODUCED;

    protected static String product_ID;
    protected static String product_NAME_PRODUCT;
    protected static String product_NAME_SHOP;
    protected static String product_ME;
    protected static String product_ACCURACY;

    protected static String dbConn_param_ID;
    protected static String dbConn_param_NAME;
    protected static String dbConn_param_VALUE;

    protected static String usr_name_usr_id;
    protected static String usr_name_name_full;
    protected static String usr_name_name_short;
    protected static String usr_name_user_login;
    protected static String usr_name_appointment;
    protected static String usr_name_workshop;
    protected static String usr_name_pass;

    /*
    protected static String dbConn_param_SERVER;
    protected static String dbConn_param_PHPSERVERFILE;
    protected static String dbConn_param_USERNAME;
    protected static String dbConn_param_PASSWORD;
     */

    protected static void fillNameTables(Context context) {
        NAME_TABLE_meatShop = context.getResources().getString(R.string.tblServ_meatShop);
        NAME_TABLE_names_product = context.getResources().getString(R.string.tblServ_nameProduct);
        NAME_TABLE_dbConnParam = context.getResources().getString(R.string.strDB_NAME_table_dbConnParam);
        NAME_TABLE_coldShop = context.getString(R.string.tblServ_coldShop);
        NAME_TABLE_usr_name = context.getString(R.string.tblServ_usr_name);

    }

    protected static void fillNameColumns(Context context) {
        produced_ID = context.getResources().getString(R.string.column_parameterDB_id);
        produced_USER = context.getResources().getString(R.string.column_parameterDB_name_user);
        produced_DATA_PRODUCED = context.getResources().getString(R.string.column_parameterDB_date);
        produced_NAME_PRODUCT = context.getResources().getString(R.string.column_parameterDB_product);
        produced_COUNT = context.getResources().getString(R.string.column_parameterDB_count);
        produced_TIME_PRODUCED = context.getResources().getString(R.string.column_parameterDB_time_produce);

        product_ID = context.getResources().getString(R.string.column_parameterDB_id);
        product_NAME_PRODUCT = context.getResources().getString(R.string.column_parameterDB_product);
        product_NAME_SHOP = context.getResources().getString(R.string.column_parameterDB_workshop);
        product_ME = context.getString(R.string.column_parameterDB_me);
        product_ACCURACY = context.getString(R.string.column_parameterDB_accuracy);

        dbConn_param_ID = context.getResources().getString(R.string.column_parameterDB_id);
        dbConn_param_NAME = context.getResources().getString(R.string._name);
        dbConn_param_VALUE = context.getResources().getString(R.string._value);

        usr_name_usr_id = context.getResources().getString(R.string.column_parameterDB_user_id);
        usr_name_name_full = context.getResources().getString(R.string.column_parameterDB_name_full);
        usr_name_name_short = context.getResources().getString(R.string.column_parameterDB_name_short);
        usr_name_user_login = context.getResources().getString(R.string.column_parameterDB_user_login);
        usr_name_appointment = context.getResources().getString(R.string.column_parameterDB_appointment);
        usr_name_workshop = context.getResources().getString(R.string.column_parameterDB_workshop);
        usr_name_pass = context.getResources().getString(R.string.column_parameterDB_pass);

        /*
        dbConn_param_SERVER = context.getResources().getString(R.string.server);
        dbConn_param_PHPSERVERFILE = context.getResources().getString(R.string.phpServerFile);
        dbConn_param_USERNAME = context.getResources().getString(R.string.username);
        dbConn_param_PASSWORD = context.getResources().getString(R.string.password);
         */
    }
}