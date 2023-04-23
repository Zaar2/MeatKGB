package com.zaar2.meatKGB_w.server;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;
import com.zaar2.meatKGB_w.Utilities.Util_common;

public class Exchange_messages {

    /**
     * <p>1.Обмен сообщениями с сервером.</p>
     * <p>2.Обновление данных в локальной БД.</p>
     * <p>3.Сброс полей для заполнения их пользователем, новыми данными</p>
     * <p>При получении исключения, создание AlertDialog</p>
     * <p></p><p></p>
     * <p>Метод перегружен, для выборки из определенной таблицы, c условиями по дате, </p>
     *
     * @param query_type        ->insert, delete, select, update, select_namesProduct
     * @param destinationObject ->запрашивающая сторона, источник запроса.
     *                          например - цех или раздел в настройках.
     *                          От этого зависит выбор таблицы в БД.
     *                          М.б.: * meatShop('@string/meat_shop'),
     *                          *                  ColdShop('@string/cold_shop'),
     *                          *                  Product list('@string/_update_name_product')
     *                          //     * @param firstDate         ->первая дата диапазона
     *                          //     * @param lastDate          ->последняя дата диапазона
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
        String resultProcessing = "err";
        try {
            String responseServer = Entry_toServerUtilities.send(
                    query_type,
                    clarificationRequest,
                    destinationObject,
//                    layout_panelOfOutgoingData,
//                    findViewById(R.id.layout_panelOfOutgoingData),
                    bundleVal,
//                    "null","null",
                    context
            );
            if (
                    !responseServer.equals("NULL")
                            && !responseServer.equals("null")
            ) {
                resultProcessing = Response_processing.response_processing(
                        responseServer,
                        clarificationRequest,
                        destinationObject,
                        context
                );
            } else
                return null;
        } catch (Exception exception) {
            String msg = "class:Exchange_messages.exchange_messages_with_server()\n " +
                    "Отправка сообщения не удалась!\n" +
                    "exception: " + exception.getMessage();
//            Entry_toUtilities.viewAlert(msg,context);
            Log.e("Ошибка: ", msg);
        }
        return resultProcessing;
    }
}