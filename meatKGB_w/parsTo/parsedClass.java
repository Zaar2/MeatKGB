package com.zaar2.meatKGB_w.parsTo;

import android.content.Context;
import android.content.res.Resources;

import com.zaar2.meatKGB_w.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class parsedClass {
    public String key_type = "null";
    public long id = 0; //shops
    public String date_produced = "null"; //shops
    public int time_produced = 0; //shops
    public String worker = "null"; //shops
    public String product_name = "null"; //shops + productNames
    public float Count = 0; //shops
    private Date date_produced_DATE = null;

    public static final String strKEY = "key";

    public long usr_id = 0; // usrNames
    public String name_full = "null"; // usrNames
    public String name_short = "null"; // usrNames
    public String user_login = "null"; // usrNames
    public String appointment = "null"; // usrNames
    public String workshop = "null"; // usrNames + productNames
    public String usrPass = "null"; // usrNames

    public String me = "null"; //productNames
    public String accuracy = "null"; //productNames

    public String username = "null"; //dbConnParam
    public String pass = "null"; //dbConnParam

    public String result = "null";
    public String serverFile = "null";

    private final SimpleDateFormat dateFormat_income = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat dateFormat_outcome = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

//    protected static String parsedClass_toString(ArrayList<parsedClass> parsedClassArrayList, String query_source, Context context) {
//        StringBuilder builder = new StringBuilder();
//        if (query_source.equals(context.getResources().getString(R.string._query_source_produce))) {
//            builder.append("id // дата произв. // час // наименование // кол-во \n");
//        } else if (query_source.equals(context.getResources().getString(R.string._query_source_nameProduct))) {
//            builder.append("id // Наименование\n");
//        } else return "0";
//
//        //forming outgoing string
//        if (parsedClassArrayList != null) {
//            if (query_source.equals(context.getResources().getString(R.string._query_source_produce))) {
//                for (parsedClass parsedClass : parsedClassArrayList) {
//                    builder.append(parsedClass.id)
//                            .append(" // ")
//                            .append(parsedClass.date_produced)
//                            .append(" // ")
//                            .append(parsedClass.time_produced)
//                            .append(" // ")
//                            .append(parsedClass.product_name)
//                            .append(" // ")
//                            .append(parsedClass.Count)
//                            .append("\n");
//                }
//            } else if (query_source.equals(context.getResources().getString(R.string._query_source_nameProduct))) {
//                for (parsedClass parsedClass : parsedClassArrayList) {
//                    builder.append(parsedClass.id)
//                            .append(" // ")
//                            .append(parsedClass.product_name)
//                            .append("\n");
//                }
//            } else return "0";
//        }
//        return builder.toString();
//    }

    public String getValue_onKey(String key, Resources resources) {
        if (key.equals(resources.getString(R.string.column_parameterDB_id))) {
            return String.valueOf(id);
        } else if (key.equals(resources.getString(R.string.column_parameterDB_date))) {
            return date_produced;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_time_produce))) {
            return String.valueOf(time_produced);
        } else if (key.equals(resources.getString(R.string.column_parameterDB_name_user))) {
            return worker;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_product))) {
            return product_name;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_count))) {
            return String.valueOf(Count);
        } else if (key.equals(strKEY)) {
            return key;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_user_id))) {
            return String.valueOf(usr_id);
        } else if (key.equals(resources.getString(R.string.column_parameterDB_name_full))) {
            return name_full;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_name_short))) {
            return name_short;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_user_login))) {
            return user_login;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_appointment))) {
            return appointment;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_workshop))) {
            return workshop;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_pass))) {
            return usrPass;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_me))) {
            return me;
        } else if (key.equals(resources.getString(R.string.column_parameterDB_accuracy))) {
            return accuracy;
        } else if (key.equals(resources.getString(R.string.tagsServ_username))) {
            return username;
        } else if (key.equals(resources.getString(R.string.tagsServ_pass))) {
            return pass;
        } else if (key.equals(resources.getString(R.string.tagsServ_result))) {
            return result;
        } else if (key.equals(resources.getString(R.string.tagsServ_serverFile))) {
            return serverFile;
        }else
            return null;
    }

    /**
     * заполняется переменная date_produced_DATE:Date
     */
    private void fill_classDate() {
        try {
            if (!date_produced.equals("null")) {
                date_produced_DATE = dateFormat_income.parse(date_produced);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Конвертирование входящей даты формата:"гггг-ММ-дд", в нужный формат:"дд.ММ.гггг"</p>
     * <p>заполняется переменная date_produced_DATE:Date</p>
     * <P>значение переменной date_produced:String изменяет свой формат</P>
     */
    public void convertDate() {
        fill_classDate();
        if (date_produced_DATE != null)
            date_produced = dateFormat_outcome.format(date_produced_DATE);
    }

    public Date getDate_produced_DATE() {
        return date_produced_DATE;
    }

    public void setDate_produced_DATE(Date date_produced_DATE) {
        this.date_produced_DATE = date_produced_DATE;
    }
}