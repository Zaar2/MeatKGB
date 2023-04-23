package com.zaar2.meatKGB_w.parsTo;

import android.content.Context;
import android.content.res.Resources;

import com.zaar2.meatKGB_w.R;

import java.util.ArrayList;

public class FormalizedString_parser {

    /**
     * формирует строку подходящую к методам класса DB_utilities
     *
     * @param array_parsedClass массив из объектов класса parsedClass
     * @param context           ссылка на контекст
     * @return массив строк, где каждый элемент - это пара: {tag/column,value}
     */
    protected static ArrayList<String[][]> parsTo_formalizedString(
            ArrayList<parsedClass> array_parsedClass,
            Context context) {
        ArrayList<String[][]> output = new ArrayList<>();

//        if (nameTable.equals(context.getString(R.string.tblServ_nameProduct))) {
//            arrayColumns = context.getResources().getStringArray(R.array.columns_table_nameProduct);
//        } else if (nameTable.equals(context.getString(R.string.tblServ_meatShop))) {
//            arrayColumns = context.getResources().getStringArray(R.array.columns_table_shops);
//        } else return null;
        for (parsedClass parsedClass : array_parsedClass) {
            String[] arrayColumns = determineColumn_byTypeOf_parsedClass(parsedClass.key_type, context.getResources());
            if (arrayColumns != null) {
                String[][] row = new String[arrayColumns.length][2];
                for (int i = 0; i < row.length; i++) {
                    row[i][0] = arrayColumns[i];
                    row[i][1] = parsedClass.getValue_onKey(arrayColumns[i], context.getResources());
                }
                output.add(row);
            } else if (parsedClass.key_type.equals(context.getString(R.string.typeOf_parsedClass_identification_user))) {
                return output;
            }
        }
        return output;
    }

    protected static String parse_toString(String[][][] inputStrings, String query_source, Context context) {
        StringBuilder builder = new StringBuilder();
        if (query_source.equals(context.getResources().getString(R.string.typeOf_parsedClass_recordsShop))) {
            builder.append("id // дата произв. // час // наименование // кол-во \n");
        } else if (query_source.equals(context.getResources().getString(R.string.typeOf_parsedClass_productList))) {
            builder.append("id // Наименование\n");
        } else return "0";
        if (query_source.equals(context.getResources().getString(R.string.typeOf_parsedClass_recordsShop))) {
            for (String[][] string : inputStrings) {
                for (String[] pare : string) {
                    builder.append(pare[1])
                            .append(" // ");
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static String[] determineColumn_byTypeOf_parsedClass(String key_type, Resources resources) {
        if (key_type.equals(resources.getString(R.string.typeOf_parsedClass_recordsShop))) {
            return resources.getStringArray(R.array.columns_table_shops);
        } else if (key_type.equals(resources.getString(R.string.typeOf_parsedClass_productList))) {
            return resources.getStringArray(R.array.columns_table_nameProduct);
        } else if (key_type.equals(resources.getString(R.string.typeOf_parsedClass_reportShop))) {
            return resources.getStringArray(R.array.columns_table_report_forShop_forPeriod);
        } else if (key_type.equals(resources.getString(R.string.typeOf_parsedClass_identification_user))) {
            return resources.getStringArray(R.array.columns_identificationUser);
        }
        return null;
    }
}