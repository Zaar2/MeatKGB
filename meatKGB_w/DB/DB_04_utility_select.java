package com.zaar2.meatKGB_w.DB;

import static com.zaar2.meatKGB_w.DB.DB_02_utility_common.*;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zaar2.meatKGB_w.R;

import java.util.Map;
import java.util.Objects;

public class DB_04_utility_select {

    /**
     * @param nameTable      имя таблицы из которой нужно выбрать данные
     * @param columnsArr     список тегов по которым нужна информация
     * @param whereSelectors ограничения, в формате массива строк, каждая из которых - это пара: {key,value}
     * @param orderBy        сортировка, массив каждый эл-нт которого это пара: {teg, type} type - модификатор (ASC-возрастание, DESC-убывание)
     * @param distinct       дубликаты значений, true-убрать, false-оставить
     * @param database       SQLiteDatabase
     * @param resources      ссылка на ресурсы
     * @return результат в виде массива записей (String[ROWS][COLUMNS][VALUE])
     */
    protected static String[][][] select(
            String nameTable,
            String[][] whereSelectors,
            String[] columnsArr,
            String[][] orderBy,
            boolean distinct,
            SQLiteDatabase database,
            Resources resources
    ) {
        String[][][] output;
        String[] selectionArgs;
        String selection = null;
        String orderBy_this;
        if (orderBy == null) {
            orderBy_this = null;
        } else {
            orderBy_this = obtaining_orderBy_string(orderBy);
        }
        if (whereSelectors == null) {
            selectionArgs = null;
        } else {
            Map<String, String[]> selections = obtaining_selection_and_selectionArgs(whereSelectors, resources);
            selection = (Objects.requireNonNull(selections.get(resources.getString(R.string._selection))))[0];
            selectionArgs = selections.get(resources.getString(R.string._selectionArgs));
        }
        Cursor cursor = database.query(
                distinct,
                nameTable,
                columnsArr,
                selection,
                selectionArgs,
                null,
                null,
                orderBy_this,
                null
        );
        int count = cursor.getCount();
        if (count > 0) {
            output = new String[count][columnsArr.length][2];
            cursor.moveToFirst();
            for (int i = 0; i < count && !cursor.isAfterLast(); i++) {
                for (int j = 0; j < columnsArr.length; j++) {
                    int colIndex = cursor.getColumnIndex(columnsArr[j]);
                    output[i][j][0] = columnsArr[j];
                    output[i][j][1] = cursor.getString(colIndex);
                }
                cursor.moveToNext();
            }
        } else output = null;
        cursor.close();
        return output;
    }
}