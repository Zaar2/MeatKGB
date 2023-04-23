package com.zaar2.meatKGB_w.Utilities;

import android.content.res.Resources;

import com.zaar2.meatKGB_w.R;

public class Util_common {

//    УСТАРЕВШИЙ
//    public static String obtaining_data_for_view(Context context) {
//        String[][][] infoFromDatabase = DB_0_EntryToDatabaseUtilities.select(
//                context.getString(R.string.tblServ_meatShop),
//                context.getResources().getStringArray(R.array.columns_table_produced_forSelect_forMainPanel),
//                null,
//                new String[][]{
//                        {context.getString(R.string.column_parameterDB_date), "DESC"},
//                        {context.getString(R.string.column_parameterDB_time_produce), "DESC"}
//                },
//                false,
//                context
//        );
//        return Entry_parserTo.parse_toString(
//                infoFromDatabase,
//                context.getString(R.string._query_source_produce),
//                context
//        );
//    }
//    /**
//     * УСТАРЕВШИЙ
//     * Изменяет формат представления даты
//     * @param incomeFormat  - доступный формат: "YYYY-mm-dd", "dd.mm.YYYY"
//     * @param outcomeFormat - доступный формат: "dd.mm.YYYY", "YYYY-mm-dd"
//     */
//    protected static String convertDateFormat(String income_DateString, String incomeFormat, String outcomeFormat) {
//        String result = "null";
//        StringBuilder builder = new StringBuilder();
//        int lastIndex_incomeVal = income_DateString.length() - 1;
//        if (incomeFormat.equals(outcomeFormat)) {
//            return income_DateString;
//        }
//        if (incomeFormat.equals("YYYY-mm-dd")) {
//            if (outcomeFormat.equals("dd.mm.YYYY")) {
//                String day = income_DateString.substring((4 + 1 + 2 + 1), lastIndex_incomeVal),
//                        month = income_DateString.substring((4 + 1), lastIndex_incomeVal - 2 - 1),
//                        year = income_DateString.substring((0), lastIndex_incomeVal - 2 - 1 - 2 - 1);
//                builder.append(day)
//                        .append(".")
//                        .append(month)
//                        .append(".")
//                        .append(year);
//                result = builder.toString();
//            }
//        }
//        if (incomeFormat.equals("dd.mm.YYYY")) {
//            if (outcomeFormat.equals("YYYY-mm-dd")) {
//                String day = income_DateString.substring((0), lastIndex_incomeVal - 4 - 1 - 2 - 1),
//                        month = income_DateString.substring((2 + 1), lastIndex_incomeVal - 4 - 1),
//                        year = income_DateString.substring((2 + 1 + 2 + 1), lastIndex_incomeVal);
//                builder.append(year)
//                        .append("-")
//                        .append(month)
//                        .append("-")
//                        .append(day);
//                result = builder.toString();
//            }
//        }
//        return result;
//    }

    /**
     * Определение имени целевой/-ых таблиц/-ы в БД.
     * <P>Использует двухуровневый строковый массив из ресурсов ('matching_requesterParams_to_destination_table').
     * В данном массиве: [0]-destinationObject; [1]-clarificationQueryType; все следующие элементы - это имена таблиц.</P>
     *
     * @param typeOfSide             указывает для какой БД нужна таблица (server/local)
     * @param destinationObject      запрашивающая сторона, источник запроса.
     *                               например - цех или раздел в настройках.
     *                               От этого зависит выбор таблицы в БД.
     *                               М.б.: * meatShop('@string/meat_shop'),
     *                               *      ColdShop('@string/cold_shop'),
     *                               *      Product list('@string/_update_name_product')
     * @param clarificationQueryType уточнение к типу запроса
     * @return возвращает массив с именами таблиц в БД, к которым следует обращаться, для получения запрошенных данных.
     * Возвращает 'null' если destinationObject нет в таблице соответствия.
     */
    protected static String[] determine_tableDB_byRequesterParams(
            String typeOfSide,
            String destinationObject,
            String clarificationQueryType,
            Resources resources
    ) {
        String[] destination_table = null;
        String[][] matching_requester_toDestTbl = null;
//        if (typeOfSide.equals(resources.getString(R.string.typeOfSide_server))) {
        matching_requester_toDestTbl = Entry_toUtilities.obtaining_twoLevelArray(R.array.matching_requesterParams_to_destination_table_toServDB, resources);
//        } else if (typeOfSide.equals(resources.getString(R.string.typeOfSide_local))) {
//            matching_requester_toDestTbl = obtaining_twoLevelArray(R.array.matching_requesterParams_to_destination_table_toLocalDB, resources);
//        } else {
//            return null;
//        }
        for (String[] item : matching_requester_toDestTbl) {
            if (item[0].equals(destinationObject)) {
                if (item[1].equals(clarificationQueryType)) {
                    destination_table = new String[item.length - 2];
                    for (int i = 0; i < destination_table.length; i++) {
                        destination_table[i] = item[i + 2];
                    }
                }
            }
        }
        return destination_table;
    }

    /**
     * поиск индекса по значению элемента в строковом массиве
     *
     * @return индекс первого встреченного элемента с заданным значением
     */
    protected static int searchID_byValue_forStrArr(String value, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (value.equals(strArr[i]))
                return i;
        }
        return -1;
    }
}