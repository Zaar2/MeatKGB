package com.zaar2.meatKGB_w.ListView;

import android.content.Context;

import com.zaar2.meatKGB_w.DB.DB_0_EntryToDatabaseUtilities;
import com.zaar2.meatKGB_w.R;
import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;
import com.zaar2.meatKGB_w.Utilities.Util_common;

import java.util.ArrayList;

public class ListView_utilities {
    /**
     * @param typeListView     тип ListView, для которого необходимо сформировать данные
     * @param intermediateDate уже полученные предварительные данные в формате - String[TextView/columns/tags][text/VALUE],
     *                         может быть null
     * @return возвращает null если не удалось определить тип списка
     */
    public static ArrayList<myItemListView> initItemsList_listView(
            String typeListView,
            String tableDB,
            String[][] whereSelectors,
            String[][] intermediateDate,
            Context context
    ) {
        ArrayList<myItemListView> list = new ArrayList<>();
        String[] columns;
        String[][][] itemsList_fromDB = new String[1][1][1];
        columns = definingColumnsList(typeListView, context);
        if (columns != null) {
//            if (
//                    typeListView.equals(context.getResources().getString(R.string._activity_main))
//                            || typeListView.equals(context.getResources().getString(R.string.typeListView_records_forProductList))
//            ) {
            itemsList_fromDB = listItemsFormation_fromBD(typeListView, columns, tableDB, whereSelectors, context);
//            }
        } else return null;
        if (itemsList_fromDB != null) {
            for (String[][] strDB : itemsList_fromDB) {
                list.add(new myItemListView(strDB));
            }
        }else return null;
        if (list.size() == 0) return null;
        else return list;
    }



    /**
     * определяем тип списка
     *
     * @return список тегов
     */
    private static String[] definingColumnsList(String typeListView, Context context) {
        if (typeListView.equals(context.getResources().getString(R.string.typeListView_records_forShop_forPeriod))) {
            return context.getResources().getStringArray(R.array.columns_table_produced_forSelect_forRecordsShops);
        }
//        if (typeListView.equals(context.getString(R.string.typeListView_report_forShop_forPeriod))) {
//            return context.getResources().getStringArray(R.array.columns_table_report_forShop_forPeriod);
//        }
        if (typeListView.equals(context.getString(R.string.typeListView_records_forProductList))) {
            return context.getResources().getStringArray(R.array.columns_table_nameProduct);
        }
        Entry_toUtilities.viewAlert("ListView_utilities.definingListType()58стр\nнеизвестный тип listView, не определен список тегов/колонок", context);
        return null;
    }

    /**
     * получаем подготовленный список элементов для ListView, данные выбираем из БД
     *
     * @param columns список тегов для выборки из БД
     */
    private static String[][][] listItemsFormation_fromBD(
            String typeListView,
            String[] columns,
            String tableDB,
            String[][] whereSelectors,
            Context context
    ) {
        String[][] orderBy;
        if (typeListView.equals(context.getResources().getString(R.string.typeListView_records_forShop_forPeriod))) {
            orderBy = new String[][]{
                    {context.getString(R.string.column_parameterDB_date), "DESC"},
                    {context.getString(R.string.column_parameterDB_time_produce), "DESC"}
            };
        } else if (typeListView.equals(context.getString(R.string.typeListView_report_forShop_forPeriod))) {
            orderBy = new String[][]{
                    {context.getString(R.string.column_parameterDB_product), "ASC"}
            };
        } else if (typeListView.equals(context.getString(R.string.typeListView_records_forProductList))) {
            orderBy = new String[][]{
                    {context.getString(R.string.column_parameterDB_product), "ASC"}
            };
        } else return null;
        return DB_0_EntryToDatabaseUtilities.select(
                tableDB,
                columns,
                whereSelectors,
                orderBy,
                false,
                context
        );
    }

//    /**
//     * получаем подготовленный список элементов для ListView, в случае если данные предварительно уже получены
//     *
//     * @param intermediateDate массив пар значений, в формате - String[TextView/columns/tags][text/VALUE]
//     */
//    private static String[][][] listItemsFormation(String[][] intermediateDate) {
//        String[][][] itemsList = new String[intermediateDate.length][2][2]; //String[ITEMS][TextView/columns/tags][text/VALUE]
//        String[] nameColumns = {"name", "value"};
//        for (int i = 0; i < intermediateDate.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                itemsList[i][j][0] = nameColumns[j];
//                itemsList[i][j][1] = intermediateDate[i][j];
//            }
//        }
//        return itemsList;
//    }

//    /**
//     * предварительное получение данных
//     *
//     * @param tags список тегов
//     * @return массив, пар значений в формате - String[TextView/columns/tags][text/VALUE]; или null
//     */
//    private static String[][] obtainingDatabase_forInformationDB(String[] tags, Context context) {
//        ArrayList<String[]> output = new ArrayList<>();
//        String numEntry = "";
//        for (String tag : tags) {
//            if (tag.equals(context.getResources().getString(R.string.countOfCheques))) {
//                numEntry = String.valueOf(
//                        DB_0_EntryToDatabaseUtilities.countRows_forTable(
//                                context.getResources().getString(R.string.strDB_NAME_table_cheques),
//                                context
//                        )
//                );
//            }
//            if (tag.equals(context.getResources().getString(R.string.countOfItems))) {
//                numEntry = String.valueOf(
//                        DB_0_EntryToDatabaseUtilities.countRowsForSelected(
//                                new String[]{context.getResources().getString(R.string.tagCheque_nameItem)},
//                                null,
//                                true,
//                                context
//                        )
//                );
//            }
//            if (tag.equals(context.getResources().getString(R.string.countEntries_purchaseOfItems))) {
//                numEntry = String.valueOf(
//                        DB_0_EntryToDatabaseUtilities.countRows_forTable(
//                                context.getResources().getString(R.string.strDB_NAME_table_purchase_product),
//                                context
//                        ));
//            }
//            if (!numEntry.equals("")) {
//                output.add(new String[]{
//                        tag,
//                        numEntry
//                });
//                numEntry = "";
//            }
//        }
//        if (output.size() == 0) return null;
//        else return output.toArray(new String[0][0]);
//    }
}