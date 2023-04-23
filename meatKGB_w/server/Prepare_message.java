package com.zaar2.meatKGB_w.server;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zaar2.meatKGB_w.R;
import com.zaar2.meatKGB_w.Params_http;
import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;
import com.zaar2.meatKGB_w.Utilities.Util_common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Prepare_message {
    protected static String[] init_message(
            String query_type,
            String clarificationQueryType,
//            String firstDate, String lastDate,
            String destinationObject,
            Bundle bundleVal,
//            LinearLayout layout_panelOfOutgoingData,
            Context context
    ) {
        String[] result_message;

        String[] destination_table;
//        Map<String, String> labelToParameter = null;
//        Map<String, String> parameters = null;
//        String key = context.getString(R.string.tagsServ_table);
//        String[] keys = new String[1];
//        int num_Parameters = 0;
        String responseCheck = "0";
        int num_additionalObject;
        if (!query_type.equals(context.getString(R.string.typeQuery_identification_user))) {
            num_additionalObject = 7; //username, pass, query_type, type_app, clarificationQueryType, destination_table
        } else
            num_additionalObject = 3; //query_type, type_app, clarificationQueryType

        /*      prepare variables       */
        destination_table = Entry_toUtilities.determine_tableDB_byRequesterParams(
                context.getString(R.string.typeOfSide_server),
                destinationObject,
                clarificationQueryType,
                context.getResources()
        );
        if (destination_table == null)
            return new String[]{"Prepare_message.init_message():destination_table is null\n" +
                    "Utilities.determine_tableDB_byRequesterParams(...) return null"};
        if (query_type.equals(context.getString(R.string.typeQuery_insert))) {
            responseCheck = Entry_toUtilities.checkParameters(bundleVal, query_type, context);
        }
        if (responseCheck.equals("0")) {
            result_message = formingMessage(
//                    num_Parameters,
                    num_additionalObject,
                    query_type,
                    clarificationQueryType,
                    destination_table,
                    bundleVal,
//                    keys,
//                    labelToParameter,
//                    parameters,
                    context
            );
        } else {
            result_message = null;
            Entry_toUtilities.viewAlert(responseCheck, context);
        }
//        labelToParameter = initAssociatedData(
//                query_type,
//                destination_table[0],
//                key,
//                context
//        );
//        parameters = initMessageParameters(
//                query_type,
//                destination_table[0],
//                bundleVal,
//                key,
//                firstDate,
//                lastDate,
//                layout_panelOfOutgoingData,
//                context
//        );
//        if (labelToParameter == null)
//            return new String[]{"Prepare_message.init_message():labelToParameter is null"};
//        if (parameters == null)
//            return new String[]{"Prepare_message.init_message():parameters is null"};
//        num_Parameters = parameters.size();


        //insert
//        if (query_type.equals(context.getResources().getString(R.string.typeQuery_insert))) {
//            responseCheck = Entry_toUtilities.checkParameters(parameters, context);
//            keys = context.getResources().getStringArray(R.array.labels_mainActivity);
//        } else
//            //select
//            if (query_type.equals(context.getResources().getString(R.string.typeQuery_select_namesProduct))
//                    || query_type.equals(context.getResources().getString(R.string.typeQuery_select))) {
//            } else
//                //delete
//                if (query_type.equals(context.getResources().getString(R.string.typeQuery_delete))) {
//                    if (
//                            destination_table[0].equals(context.getString(R.string.tblServ_meatShop))
//                                    || destination_table[0].equals(context.getString(R.string.tblServ_coldShop))
//                    ) {
//                        keys = new String[]{context.getString(R.string.column_parameterDB_id)};
//                    } else if (destination_table[0].equals(context.getString(R.string.tblServ_nameProduct))) {
//                        keys = new String[]{context.getString(R.string.column_parameterDB_product)};
//                    }
//                } else return null;

        //forming message
//        if (responseCheck.equals("0") && keys != null) {
//        result_message = formingMessage(
//                    num_Parameters,
//                num_additionalObject,
//                query_type,
//                clarificationQueryType,
//                destination_table,
//                bundleVal,
//                    keys,
//                    labelToParameter,
//                    parameters,
//                context
//        );
//        } else {
//            Entry_toUtilities.viewAlert(
//                    "Prepare_message.init_message()\n" +
//                            "responseCheck: "+responseCheck+"\n" +
//                            "keys (Список тегов для тела запроса к серверу) IS NULL\n",
//                    context
//            );
//            return null;
//        }
        return result_message;
    }

//    private static Map<String, String> initAssociatedData(
//            String typeQuery,
//            String tableServ,
//            String key,
//            Context context
//    ) {
//        Map<String, String> labelToParameter = new HashMap<>();
//        if (typeQuery.equals(context.getString(R.string.typeQuery_insert))) {
//            if (
//                    tableServ.equals(context.getString(R.string.tblServ_meatShop))
//                            || tableServ.equals(context.getString(R.string.tblServ_coldShop))
//            ) {
//                labelToParameter.put(
//                        context.getResources().getString(R.string.label_date),
//                        context.getResources().getString(R.string.column_parameterDB_date)
//                );
//                labelToParameter.put(
//                        context.getResources().getString(R.string.label_count),
//                        context.getResources().getString(R.string.column_parameterDB_count)
//                );
//                labelToParameter.put(
//                        context.getResources().getString(R.string.label_name_user),
//                        context.getResources().getString(R.string.column_parameterDB_name_user)
//                );
//                labelToParameter.put(
//                        context.getResources().getString(R.string.label_product),
//                        context.getResources().getString(R.string.column_parameterDB_product)
//                );
//                labelToParameter.put(
//                        context.getResources().getString(R.string.label_time_produce),
//                        context.getResources().getString(R.string.column_parameterDB_time_produce)
//                );
//            }
//        } else if (typeQuery.equals(context.getString(R.string.typeQuery_select))) {
//        } else if (typeQuery.equals(context.getString(R.string.typeQuery_delete))) {
//            if (tableServ.equals(context.getString(R.string.tblServ_meatShop))
//                    || tableServ.equals(context.getString(R.string.tblServ_coldShop))
//            ) {
//                labelToParameter.put(context.getString(R.string.column_parameterDB_id), context.getString(R.string.column_parameterDB_id));
//            } else if (tableServ.equals(context.getString(R.string.tblServ_nameProduct))) {
//                labelToParameter.put(context.getString(R.string.column_parameterDB_product), context.getString(R.string.column_parameterDB_product));
//            } else return null;
//        } else return null;
//        return labelToParameter;
//    }

//    private static Map<String, String> initMessageParameters(
//            String typeQuery,
//            String tableServ,
//            Bundle bundleVal,
//            String key,
//            String firstDate,
//            String lastDate,
//            LinearLayout layout_panelOfOutgoingData,
//            Context context
//    ) {
//        Map<String, String> parameters = new HashMap<>();
//        if (typeQuery.equals(context.getString(R.string.typeQuery_insert))) {
//            parameters.put(
//                    context.getResources().getString(R.string.label_date),
//                    ((TextView) layout_panelOfOutgoingData.findViewById(R.id.textView_dateProduced)).getText().toString()
//            );
//            parameters.put(
//                    context.getResources().getString(R.string.label_name_user),
//                    ((TextView)
//                            ((LinearLayout) layout_panelOfOutgoingData.findViewById(R.id.layout_worker))
//                                    .getChildAt(1))
//                            .getText().toString()
//            );
//            parameters.put(
//                    context.getResources().getString(R.string.label_product),
//                    ((Spinner) layout_panelOfOutgoingData.findViewById(R.id.spinner_product))
//                            .getSelectedItem().toString()
//            );
//            parameters.put(
//                    context.getResources().getString(R.string.label_count),
//                    ((EditText) layout_panelOfOutgoingData.findViewById(R.id.editText_count))
//                            .getText().toString()
//            );
//            parameters.put(
//                    context.getResources().getString(R.string.label_time_produce),
//                    ((Spinner) layout_panelOfOutgoingData.findViewById(R.id.spinner_hour))
//                            .getSelectedItem().toString()
//            );
//        } else if (typeQuery.equals(context.getString(R.string.typeQuery_select))) {
//        } else if (typeQuery.equals(context.getString(R.string.typeQuery_delete))) {
//            parameters.put(
//                    "id",
//                    ((TextView) layout_panelOfOutgoingData.findViewById(R.id.textView_idDB)).getText().toString()
//            );
//        } else return null;
//        return parameters;
//    }

    /**
     * формирование сообщения для сервера.
     * //     * @param num_Parameters число основных параметров в сообщении
     *
     * @param num_additionalObject   число параметров в заголовке сообщения
     * @param query_type             тип запроса
     * @param clarificationQueryType уточнение типа запроса
     * @param destination_table      из какой таблицы БД запрашиваются данные
     *                               //     * @param keys список тэгов
     *                               //     * @param labelToParameter ассоциированный массив, в котором
     *                               //     *                         имя ключа (из списка {@code keys}) соответствует
     *                               //     *                         НАЗВАНИЮ параметра из сообщения для сервера
     *                               //     * @param parameters ассоциированный массив, в котором
     *                               имя ключа (из списка {@code keys}) соответствует
     *                               ЗНАЧЕНИЮ параметра из сообщения для сервера
     * @return сформированное сообщение для сервера
     */
    private static String[] formingMessage(
//            int num_Parameters,
            int num_additionalObject,
            String query_type,
            String clarificationQueryType,
            String[] destination_table,
//            String[] keys,
//            Map<String, String> labelToParameter,
//            Map<String, String> parameters,
            Bundle bundleVal,
            Context context
    ) {
        Set<String> keysBundle = new HashSet<>();
        if (bundleVal != null) {
            keysBundle = bundleVal.keySet();
        }
//        else keysBundle = new HashSet<>();
        String[] result_message = new String[keysBundle.size() + num_additionalObject];
        //title message

        result_message[0] = context.getResources().getString(R.string.tagsServ_query_type) + "=" + query_type;
        result_message[1] = context.getResources().getString(R.string.tagsServ_type_app) + "=" + context.getString(R.string.type_app_worker);
        result_message[2] = context.getResources().getString(R.string.tagsServ_clarificationRequest) + "=" + clarificationQueryType;
        result_message[3] = context.getString(R.string.column_parameterDB_workshop) + "=" + Params_http.getINSTANCE().getWorkshop();
        if (!query_type.equals(context.getString(R.string.typeQuery_identification_user))) {
            result_message[4] = context.getString(R.string.tagsServ_table) + "=" + destination_table[0];
            result_message[5] = context.getResources().getString(R.string.tagsServ_username) + "=" + Params_http.getINSTANCE().getUSER_NAME();
            result_message[6] = context.getResources().getString(R.string.tagsServ_pass) + "=" + Params_http.getINSTANCE().getPASS();
        }
        //body message
        if (keysBundle.size() > 0) {
            int i = 0;
            for (String key : keysBundle) {
//                if (!(bundleVal.get(key).toString()).equals("")) {
                result_message[i + num_additionalObject] =
                        key + "=" + bundleVal.get(key).toString();
//                }
                i++;
            }
        }

//        if (num_Parameters>0) {
//            for (int i = 0; i < keys.length; i++) {
//                result_message[i + num_additionalObject] = labelToParameter.get(keys[i]) //key
//                        + "="
//                        + parameters.get(keys[i])   //value
//                ;
//            }
//        }
        return result_message;
    }
}