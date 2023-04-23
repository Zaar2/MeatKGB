package com.zaar2.meatKGB_w.DB;

import static com.zaar2.meatKGB_w.DB.DB_02_utility_common.obtaining_selection_and_selectionArgs;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.zaar2.meatKGB_w.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class DB_03_utility_insertUpdate {

    /**
     * @param database               ссылка на БД
     * @param formalizedString_input строковый массив, где каждый элемент, это "строка в таблице" (массив пар: {tag/column,value})
     * @param nameTable              целевая таблица
     * @return кол-во вставленных строк
     */
    protected static int insertRows_onceTable(SQLiteDatabase database, ArrayList<String[][]> formalizedString_input, String nameTable) {
        int countInserted = 0;
        ContentValues values = new ContentValues();
        for (String[][] row : formalizedString_input) {
            for (String[] pair : row) {
                values.put(pair[0], pair[1]);
            }
            countInserted += database.insert(nameTable, null, values) >= 0 ? 1 : 0;
        }
        return countInserted;
    }

//    /**
//     * НЕДОДЕЛАННАЯ
//     * @param database               ссылка на БД
//     * @param whereSelectors ограничения, в формате массива строк, каждая из которых - это пара: {tag/column,value}
//     * @param formalizedString_input строковый массив, где каждый элемент, это "строка в таблице" (массив пар: {tag/column,value})
//     * @param nameTable              целевая таблица
//     * @return колличество затронутых строк
//     */
//    protected static int update_onceTable(
//            SQLiteDatabase database,
//            ArrayList<String[][]> formalizedString_input,
//            String[][] whereSelectors,
//            String nameTable,
//            Resources resources
//    ) {
//        int countUpdated = 0;
//        ContentValues values = new ContentValues();
//        Map<String, String[]> selections = obtaining_selection_and_selectionArgs(whereSelectors, resources);
//        String selection = (Objects.requireNonNull(selections.get(resources.getString(R.string._selection))))[0];
//        String[] selectionArgs = selections.get(resources.getString(R.string._selectionArgs));
//        for (String[][] row : formalizedString_input) {
//            for (String[] pair : row) {
//                values.put(pair[0], pair[1]);
//            }
//            countUpdated += database.update(
//                    nameTable,
//                    values,
//                    selection,
//                    selectionArgs
//            );
//        }
//        return countUpdated;
//    }

    /**
     * @param whereSelectors ограничения, в формате массива строк, каждая из которых - это пара: {tag/column,value}
     * @param values         новые значения, в формате массива строк, каждая из которых - пара: {tag/column,value}
     * @return колличество затронутых строк
     */
    protected static int update_onceRows(
            SQLiteDatabase database,
            String nameTable,
            String[][] whereSelectors,
            String[][] values,
            Resources resources
    ) {
        ContentValues contentValues = new ContentValues();
        for (String[] pair : values) {
            contentValues.put(pair[0], pair[1]);
        }
        Map<String, String[]> selections = obtaining_selection_and_selectionArgs(whereSelectors, resources);
        String selection = (Objects.requireNonNull(selections.get(resources.getString(R.string._selection))))[0];
        String[] selectionArgs = selections.get(resources.getString(R.string._selectionArgs));
        return database.update(
                nameTable,
                contentValues,
                selection,
                selectionArgs
        );
    }


}