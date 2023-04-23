package com.zaar2.meatKGB_w.server;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.zaar2.meatKGB_w.R;

import java.util.concurrent.ExecutionException;

public class SendData {
    protected static String sendMessage(
            String query_type,
            String clarificationQueryType,
//            String firstDate,
//            String lastDate,
//            LinearLayout layout_panelOfOutgoingData,
            String destinationObject,
            Bundle bundleVal,
            Context context
    ) {

        String serverResponse = "NULL";
        SendData_Task sendDataTask;
        try {
            sendDataTask = new SendData_Task();
            String[] message = Prepare_message.init_message(
                    query_type,
                    clarificationQueryType,
//                    firstDate, lastDate,
                    destinationObject,
                    bundleVal,
//                    layout_panelOfOutgoingData,
                    context
            );
            if (message != null) {
                sendDataTask.execute(message);
                try {
                    serverResponse = sendDataTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("Ошибка", "ошибка при формировании сообщения к серверу!");
            }
        } catch (Exception exception) {
            Log.e(
                    "Ошибка: ",
                    String.format("%s%s", context.getString(R.string.Error_before_sendToServer), exception.getMessage())
            );
        }
        Log.e("Ответ сервера", serverResponse.substring(0, Math.min(serverResponse.length(), 100)));
        return serverResponse;
    }
}