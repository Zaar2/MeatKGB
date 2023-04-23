package com.zaar2.meatKGB_w.Utilities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import com.zaar2.meatKGB_w.DB.DB_0_EntryToDatabaseUtilities;
import com.zaar2.meatKGB_w.Params_http;
import com.zaar2.meatKGB_w.R;

import java.util.Map;

public class Util_CheckAlert {

    protected static void viewAlert(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Внимание!");
        builder.setMessage(message);
        builder.show();
    }

    /**
     * сверка кол-ва параметров для подключения к базе на сервере.
     * сверяется кол-во парам. в локальной БД с кол-вом парам. в массиве строк из ресурсов
     * если не совпадает - создается AlertDialog с соответствующим сообщением
     *
     * @return true - если совпадает; false - если не совпадает
     */
    protected static boolean check_countParamsOf_connParams(Context context) {
//        long countIn_db = DB_0_EntryToDatabaseUtilities.countRows_forTable(context.getString(R.string.strDB_NAME_table_dbConnParam), context);
//        int countIn_mustBe = context.getResources().getStringArray(R.array.labels_settingActivity_dbConnection).length;
        if (Params_http.getINSTANCE().isInit()) {
            return true;
        } else {
            String err_tag = "Ошибка";
            String err_msg = "не инициализированны параметры подключения к серверу!";
            Entry_toUtilities.viewAlert(err_tag + " : " + err_msg, context);
            return false;
        }
    }

    /**
     * проверка корректности введенных данных
     *
     * @param bundleVal набор данных
     * @param typeQuery тип запроса (insert, delete, ...)
     * @return "0" если все ОК, либо описание ошибки.
     */
    protected static String checkParameters(Bundle bundleVal, String typeQuery, Context context) {
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
//        count_param
        if (!Entry_toUtilities.checkParameter_count(bundleVal, typeQuery, context)) {
            flag = false;
            builder.append("Недопустимое значение для поля - Колличество.\n");
        }
        if (flag) return "0";
        else return builder.toString();
    }

    /**
     * проверка корректности поля - КОЛИЧЕСТВО
     *
     * @param bundleVal набор данных
     * @param typeQuery тип запроса (insert, delete, ...)
     * @return true - если все ОК, либо false.
     */
    protected static boolean checkParameter_count(Bundle bundleVal, String typeQuery, Context context) {
        if (typeQuery.equals(context.getString(R.string.typeQuery_insert))) {
            String accuracy = null;
            String count = bundleVal.getString(context.getString(R.string.column_parameterDB_count));
            String nameProduct = bundleVal.getString(context.getString(R.string.column_parameterDB_product));
            if (count != null && nameProduct != null&&!count.equals("")) {
                count = count.trim();
                String table = context.getString(R.string.tblServ_nameProduct);
                String[] column = new String[]{
                        context.getString(R.string.column_parameterDB_accuracy)
                };
                String[][] where = new String[][]{
                        {context.getString(R.string.column_parameterDB_product), nameProduct}
                };
                String[][][] responseDB = DB_0_EntryToDatabaseUtilities.select(
                        table,
                        column,
                        where,
                        null,
                        true,
                        context
                );
                accuracy = (
                        responseDB
                )[0][0][1];
                int accuracyDB = Integer.parseInt(accuracy);
                if (Double.parseDouble(count) <= 0) return false;
                int accuracyCurrently = Entry_toUtilities.finding_numberOf_decimalPlaces(count);
                return accuracyDB >= accuracyCurrently;
            } else return false;
        } else return true;
    }
}