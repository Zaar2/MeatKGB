package com.zaar2.meatKGB_w.DB;

import static com.zaar2.meatKGB_w.Const.ACTUALITY_DB_VERSION;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.zaar2.meatKGB_w.parsTo.Entry_parserTo;
import com.zaar2.meatKGB_w.parsTo.parsedClass;

import java.util.ArrayList;

public class DB_0_EntryToDatabaseUtilities {

    /**
     * Метод вставки данных в одну таблицу.
     *
     * @param formalizedString_input строковый массив, где каждый элемент, это "строка в таблице" (массив пар: {tag/column,value})
     * @param context                ссылка на контекст
     * @return false-если кол-во заполненных колонок не совпадает с кол-вом элементов во входящей строке,
     * true - если совпадает
     */
    public static boolean insertRows(ArrayList<String[][]> formalizedString_input, String nameTable, Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        int countInserted = DB_03_utility_insertUpdate.insertRows_onceTable(database, formalizedString_input, nameTable);
        database.close();
        return countInserted == formalizedString_input.size();
    }

    /**
     * @param whereSelectors ограничения, в формате массива строк, каждая из которых - это пара: {tag/column,value}
     * @param values         новые значения, в формате массива строк, каждая из которых - пара: {tag/column,value}
     * @return колличество затронутых строк
     */
    public static int updateRow(String[][] whereSelectors, String[][] values, String nameTable, Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        int result = DB_03_utility_insertUpdate.update_onceRows(
                database,
                nameTable,
                whereSelectors,
                values,
                context.getResources()
        );
        database.close();
        return result;
    }

    /**
     * @param nameTable      имя таблицы из которой извлекаются данные
     * @param columns        список тегов/колонок по которым нужна информация
     * @param whereSelectors ограничения, в формате массива строк, каждая из которых - это пара: {tag/column,value}
     * @param orderBy        сортировка, массив каждый эл-нт которого это пара: {teg/column, type} type - модификатор (ASC-возрастание, DESC-убывание)
     * @param distinct       дубликаты значений, true-убрать, false-оставить
     * @return String[ROWS][COLUMNS][VALUE] или null если значений по заданным условиям не найдено
     */
    public static String[][][] select(
            String nameTable,
            String[] columns,
            String[][] whereSelectors,
            String[][] orderBy,
            boolean distinct,
            Context context
    ) {
        String[][][] output = new String[1][1][1];
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getReadableDatabase();
        output = DB_04_utility_select.select(nameTable, whereSelectors, columns, orderBy, distinct, database, context.getResources());
        database.close();
        return output;
    }

    public static boolean replaceRecord(ArrayList<parsedClass> array_parsedClass, String nameTable, Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        ArrayList<String[][]> formalizedString_input = Entry_parserTo.parseTo_formalizedString(array_parsedClass, context);
        int deletedRows = DB_02_utility_common.deletingRows(database, nameTable);
        int insertedRows = DB_03_utility_insertUpdate.insertRows_onceTable(
                database,
                formalizedString_input,
                nameTable
        );
        database.close();
        return true;
    }

    public static long countRows_forTable(String nameTable, Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getReadableDatabase();
        long output = DB_02_utility_common.getNumEntries(database, nameTable);
        database.close();
        return output;
    }

    public static int clearingDB(Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        int countDeleted = DB_02_utility_common.deletingRows(database, DB_01_value.NAME_TABLE_names_product);
        countDeleted += DB_02_utility_common.deletingRows(database, DB_01_value.NAME_TABLE_meatShop);
        database.close();
        return countDeleted;
    }

    /**
     * Удаляет выбранные записи из указанной таблицы
     */
    public static int deletingRows(String nameTable, @NonNull String[][] whereSelectors, Context context){
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        int countDeleted = DB_02_utility_common.deletingRows(database, nameTable, whereSelectors,context.getResources());
        database.close();
        return countDeleted;
    }

    /**
     * Удаляет все записи из указанной таблицы
     */
    public static int deletingRows(String nameTable, Context context) {
        SQLiteDatabase database = new DB_00_database(context, ACTUALITY_DB_VERSION).getWritableDatabase();
        int countDeleted = DB_02_utility_common.deletingRows(database, nameTable);
        database.close();
        return countDeleted;
    }
}
