package com.zaar2.meatKGB_w;

import android.content.Context;

import com.zaar2.meatKGB_w.DB.DB_0_EntryToDatabaseUtilities;

public class Params_http {

    //    private String server = null;
    private static Params_http INSTANCE = null;
    private String
            phpServerFile = null,
            USER_NAME = null,
            PASS = null,
            nameShort = null,
            appointment = null,
            workshop = null;


    private boolean isInit = false;

    private Params_http() {
    }

    public static synchronized Params_http getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Params_http();
        }
        return INSTANCE;
    }

//    public void init_or_update_variables(Context context) {
////        phpServerFile = getValueVariables(context.getResources().getString(R.string.phpServerFile), context);
////        server = getValueVariables(context.getResources().getString(R.string.server), context);
//        USER_NAME = getValueVariables(context.getResources().getString(R.string.username), context);
//        PASS = getValueVariables(context.getResources().getString(R.string.password), context);
//        if (
//                USER_NAME != null
//                        && PASS != null
////                        && phpServerFile != null
////                        && server != null
//        ) isInit = true;
//    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }


    public void setUSER_NAME(String value) {
        this.USER_NAME = value;
    }

    public void setPASS(String value) {
        this.PASS = value;
    }


    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getPASS() {
        return PASS;
    }

    public String getPhpServerFile() {
        return phpServerFile;
    }

    public void setPhpServerFile(String phpServerFile) {
        this.phpServerFile = phpServerFile;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    //    private String getValueVariables(String nameVariables, Context context) {
//        String[][][] value = DB_0_EntryToDatabaseUtilities.select(
//                context.getResources().getString(R.string.strDB_NAME_table_dbConnParam),
//                new String[]{context.getResources().getString(R.string._value)},
//                new String[][]{{context.getResources().getString(R.string._name), nameVariables}},
//                null,
//                true,
//                context
//        );
//        if (value != null) {
//            return value[0][0][1];
//        }
//        return null;
//    }
//    public String getServer() {
//        return server;
//    }
//    public void setPhpServerFile(Context context) {
//        phpServerFile = getValueVariables(context.getResources().getString(R.string.phpServerFile), context);
//        this.phpServerFile = phpServerFile;
//    }
//
//    public void setServer(Context context) {
//        server = getValueVariables(context.getResources().getString(R.string.server), context);
//        this.server = server;
//    }
//    public void setPASS(Context context) {
////        PASS = getValueVariables(context.getResources().getString(R.string.password), context);
////        this.PASS = PASS;
////    }
////
////    public void setUSER_NAME(Context context) {
////        USER_NAME = getValueVariables(context.getResources().getString(R.string.username), context);
////        this.USER_NAME = USER_NAME;
////    }
}