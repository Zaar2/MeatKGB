package com.zaar2.meatKGB_w.server;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Entry_toServerUtilities {
    /**
     * <p>1.Обмен сообщениями с сервером.</p>
     * <p>2.Обновление данных в локальной БД.</p>
     * <p>При получении исключения, создание AlertDialog</p>
     * <p></p><p></p>
     * <p>Метод перегружен, для выборки из определенной таблицы, без условий по дате, </p>
     * <p>Посылает на сервер строку в виде еще одного параметра сообщения</p>
     *
     * @param query_type ->insert, delete, select, update, select_namesProduct
     *                   //     * @param string     строка для отправки на сервер, для вставки в таблицу (название нового продукта)
     */
    public static String exchange_messages_with_server(
//            LinearLayout layout_panelOfOutgoingData,
            String query_type,
            String clarificationRequest,
            String destinationObject,
            Bundle bundleVal,
//            String firstDate,
//            String lastDate,
            Context context
    ) {
        return Exchange_messages.exchange_messages_with_server(
//                layout_panelOfOutgoingData,
                query_type,
                clarificationRequest,
                destinationObject,
                bundleVal,
//                firstDate,
//                lastDate,
                context
        );
    }

    public static String send(
            String query_type,
            String clarificationQueryType,
            String destinationObject,
//            LinearLayout layout_panelOfOutgoingData,
            Bundle bundleVal,
//            String firstDate,
//            String lastDate,
            Context context
    ) {
        return SendData.sendMessage(
                query_type,
                clarificationQueryType,
//                firstDate,
//                lastDate,
//                layout_panelOfOutgoingData,
                destinationObject,
                bundleVal,
                context
        );
    }
}