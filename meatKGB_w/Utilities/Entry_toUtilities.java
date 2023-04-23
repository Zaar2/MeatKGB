package com.zaar2.meatKGB_w.Utilities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Map;

public class Entry_toUtilities {

    /**
     * проверка корректности введенных данных
     *
     * @param bundleVal набор данных
     * @param typeQuery тип запроса (insert, delete, ...)
     * @return "0" если все ОК, либо описание ошибки.
     */
    public static String checkParameters(Bundle bundleVal, String typeQuery, Context context) {
        return Util_CheckAlert.checkParameters(bundleVal, typeQuery, context);
    }

    /**
     * проверка корректности поля - колличество
     * @param bundleVal набор данных
     * @param typeQuery тип запроса (insert, delete, ...)
     * @return true - если все ОК, либо false.
     */
    public static boolean checkParameter_count(Bundle bundleVal, String typeQuery, Context context) {
        return Util_CheckAlert.checkParameter_count(bundleVal, typeQuery, context);
    }

    /**
     * Заполняются названия полей в панели ввода данных.
     *
     * @param layoutPanel ссылка на панель ввода данных
     * @param dataOfView  массив строк с названиями полей
     */
    public static void initView_panel_labelView(LinearLayout layoutPanel, String[] dataOfView) {
        Util_PanelOfData.initView_panel_labelView(layoutPanel, dataOfView);
    }

    /**
     * Вставляет дату в указанный TextView.
     *
     * @param textView_dateProduced объект в котором необходима дата
     * @param str_date              необходимая дата (если значение 'current' -> значит нужно вставить текущую дату)
     * @param resources             ссылка на ресурсы
     */
    public static void initView_DateProduced(TextView textView_dateProduced, String str_date, Resources resources) {
        Util_PanelOfData.initView_DateProduced(textView_dateProduced, str_date, resources);
    }

    /**
     * Заполняет панель ввода данных при клике на listViewItem
     *
     * @param layoutPanel_dest ссылка на панель ввода данных
     * @param layout_source    ссылка на layout of listViewItem - содержащий textViews с данными для заполнения панели
     */
    public static void initValue_panel_fromItemListView(LinearLayout layoutPanel_dest, LinearLayout layout_source) {
        Util_PanelOfData.initValue_panel_fromItemListView(layoutPanel_dest, layout_source);
    }

    /**
     * Формирует данные для отправки на сервер
     *
     * @param layout_source ссылка на layout панели ввода данных - содержащий textViews с нужными данными
     * @param query_type    тип запроса (insert, delete, ...)
     */
    public static Bundle prepare_bundleVal_forPrepareMessage(
            LinearLayout layout_source,
            String query_type,
//            String clarificationRequest,
//            String destinationObject,
            Context context
    ) {
        return Util_PanelOfData.prepare_bundleVal_forPrepareMessage(
                layout_source,
                query_type,
//                clarificationRequest,
//                destinationObject,
                context
        );
    }

    public static void selectValue_forSpinner(Spinner spinner, int value_num, String value_str) {
        Util_Spinner.selectValue_forSpinner(spinner, value_num, value_str);
    }

    public static int selectIndex_forSpinner(Spinner spinner, int value_num, String value_str) {
        return Util_Spinner.selectIndex_forSpinner(spinner, value_num, value_str);
    }

    public static String[] initItemsList_Spinner(
            String typeShop,
            String typeList,
            String tableDB,
            Context context
    ) {
        return Util_Spinner.initItemsList_Spinner(typeShop, typeList, tableDB, context);
    }

    public static void initCount_default(EditText editText_count, String value) {
        Util_PanelOfData.initCount_default(editText_count, value);
    }

    public static void init_idDB_default(TextView textView_idDB) {
        Util_PanelOfData.init_idDB_default(textView_idDB);
    }

    /**
     * Заполняет шапку ListView, названия колонок.
     * Получает - layout for items of listView.
     * А также, набор наименований колонок
     *
     * @param linearLayout_labelView содержит вложенный linearLayout с набором TextViews
     * @param labelsArray            двухуровневый массив, состоит из парных значений,
     *                               <p>в каждой паре:
     *                               <p>[0]-название параметра для Views;</p>
     *                               <p>[1]-имя параметра/колонки в БД(здесь не используется);</p>
     *                               </p>
     */
    public static void initLabels_forViewsOf_linearLayout(LinearLayout linearLayout_labelView, String[][] labelsArray) {
        Util_Views.initLabels_forViewsOf_linearLayout(linearLayout_labelView, labelsArray);
    }

    /**
     * Преобразует массив из строковых ресурсов в массив String
     *
     * @param resID IDкод двухуровневого массива из ресурсов
     * @return возвращает String[][]
     */
    public static String[][] obtaining_twoLevelArray(int resID, Resources resources) {
        return Util_TextFormat.obtaining_twoLevelArray(resID, resources);
    }

    public static int finding_numberOf_decimalPlaces(String num) {
        return Util_TextFormat.finding_numberOf_decimalPlaces(num);
    }

    /**
     * если значение числа меньше 10, то формат возвращенного строкового представления '00'
     */
    public static String dateFormatIntToStr(int num) {
        return Util_TextFormat.dateFormatIntToStr(num);
    }

    public static String servName_to_label(String servName, Context context) {
        return Util_TextFormat.servName_to_label(servName, context);
    }

    public static String labelName_to_servName(String labelName, Context context) {
        return Util_TextFormat.labelName_to_servName(labelName, context);
    }

    public static void callDatePicker(TextView textView, Context context) {
        Util_Views.callDatePicker(textView, context);
    }

    public static void viewAlert(String message, Context context) {
        Util_CheckAlert.viewAlert(message, context);
    }

    /**
     * Вставлет в первые два TextViews заданную строку
     *
     * @param layout    в какой layout вставлять (ограничения: первые два объекта должны быть - TextView)
     * @param labelText что вставлять
     */
    public static void fillViewsLabel_first_or_Last_Date(LinearLayout layout, String labelText) {
        Util_Views.fillViewsLabel_first_or_Last_Date(layout, labelText);
    }

    /**
     * вставляет в textView дату в нужном для этого приложения формате
     *
     * @param textView куда вставить
     * @param value    кол-во дней, которые нужно добавить к текущей дате
     *                 (может быть положительным/увеличение или отрицательным/уменьшение)
     */
    public static void initDate_default_first_or_LastDate(TextView textView, int value) {
        Util_Views.initDate_default_first_or_LastDate(textView, value);
    }

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
    public static String[] determine_tableDB_byRequesterParams(
            String typeOfSide,
            String destinationObject,
            String clarificationQueryType,
            Resources resources
    ) {
        return Util_common.determine_tableDB_byRequesterParams(
                typeOfSide,
                destinationObject,
                clarificationQueryType,
                resources
        );
    }

    /**
     * @param view  вьюха которую нужно скрыть или показать
     * @param isRun показать/VISIBLE - true или скрыть/GONE - false.
     */
    public static void view_isVisible(View view, boolean isRun) {
        Util_Views.view_isVisible(view, isRun);
    }

    /**
     * сверка кол-ва параметров для подключения к базе на сервере.
     * сверяется кол-во парам. в локальной БД с кол-вом парам. в массиве строк из ресурсов
     * если не совпадает - создается AlertDialog с соответствующим сообщением
     *
     * @return true - если совпадает; false - если не совпадает
     */
    public static boolean check_countParamsOf_connParams(Context context) {
        return Util_CheckAlert.check_countParamsOf_connParams(context);
    }

    /**
     * поиск индекса по значению элемента в строковом массиве
     *
     * @return индекс первого встреченного элемента с заданным значением
     */
    public static int searchID_byValue_forStrArr(String value, String[] strArr) {
        return Util_common.searchID_byValue_forStrArr(value, strArr);
    }
}