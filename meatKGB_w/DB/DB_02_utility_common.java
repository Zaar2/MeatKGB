package com.zaar2.meatKGB_w.DB;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.zaar2.meatKGB_w.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DB_02_utility_common {

    protected static boolean tableExists(SQLiteDatabase db, String tableName) {
        if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) " +
                        "FROM sqlite_master " +
                        "WHERE type = ? AND name = ?",
                new String[]{"table", tableName});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    protected static Map<String, String[]> obtaining_selection_and_selectionArgs(String[][] whereSelectors, Resources resources) {
        Map<String, String[]> outputArrStr = new HashMap<>();
        String[] selection = new String[1];
        StringBuilder sel = new StringBuilder();
        String[] selectionArgs;
        if (whereSelectors == null) {
            selectionArgs = null;
        } else {
            selectionArgs = new String[whereSelectors.length];
            sel.append(whereSelectors[0][0]).append(" =?");
            selectionArgs[0] = whereSelectors[0][1];
            if (whereSelectors.length > 1) {
                for (int i = 1; i < whereSelectors.length; i++) {
                    sel.append(" AND ").append(whereSelectors[i][0]).append(" =?");
                    selectionArgs[i] = whereSelectors[i][1];
                }
            }
            selection[0] = sel.toString();
        }
        outputArrStr.put(resources.getString(R.string._selection), selection);
        outputArrStr.put(resources.getString(R.string._selectionArgs), selectionArgs);
        return outputArrStr;
    }

    protected static String obtaining_orderBy_string(String[][] orderBy_incoming) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < orderBy_incoming.length; i++) {
            if (i > 0) builder.append(", ");
            builder.append(orderBy_incoming[i][0])
                    .append(" ")
                    .append(orderBy_incoming[i][1]);
        }
        return builder.toString();
    }

    protected static long getNumEntries(SQLiteDatabase database, String nameTable) {
        return DatabaseUtils.queryNumEntries(database, nameTable);
    }

    /**
     * Удаляет выбранные записи из указанной таблицы
     */
    protected static int deletingRows(SQLiteDatabase database, String nameTable, @NonNull String[][] whereSelectors, Resources resources) {
        if (whereSelectors != null) {
            Map<String, String[]> selections = obtaining_selection_and_selectionArgs(whereSelectors, resources);
            String selection = (Objects.requireNonNull(selections.get(resources.getString(R.string._selection))))[0];
            String[] selectionArgs = selections.get(resources.getString(R.string._selectionArgs));
            return database.delete(nameTable, selection, selectionArgs);
        } else return -1;
    }

    /**
     * Удаляет все записи из указанной таблицы
     */
    protected static int deletingRows(SQLiteDatabase database, String nameTable) {
        return database.delete(nameTable, null, null);
    }

    protected static void dropTables(SQLiteDatabase database) {
        dropTable(database, DB_01_value.NAME_TABLE_meatShop);
    }

    protected static void dropTable(SQLiteDatabase database, String nameTable) {
        database.execSQL(
                "drop table if exists " + nameTable
        );
    }
}