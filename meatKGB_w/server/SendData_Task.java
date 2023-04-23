package com.zaar2.meatKGB_w.server;

import static com.zaar2.meatKGB_w.Const.PROTOСOL_HTTP;
import static com.zaar2.meatKGB_w.Const.server;
import static com.zaar2.meatKGB_w.Const.serverFile;

import android.os.AsyncTask;

import com.zaar2.meatKGB_w.Params_http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendData_Task extends AsyncTask<String,Integer,String> {

    String resultStr = null;
    String message = "Сообщения нет";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... messages) {
        StringBuilder builder = new StringBuilder();
        int myProgress = 0;
        for (int i = 0; i < messages.length; i++) {
            builder.append(messages[i]);
            builder.append(i == ((messages.length) - 1) ? "" : "&");
        }
        message = builder.toString();
        try {
            String file = Params_http.getINSTANCE().getPhpServerFile();
            if (file == null) file = serverFile;
            String myURL = PROTOСOL_HTTP + "://" + server + "/" + file;
//            String myURL = PROTOСOL_HTTP + "://" + Params_http.getINSTANCE().getServer() + "/" + Params_http.getINSTANCE().getPhpServerFile();
            byte[] data = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(myURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(1000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(message.getBytes().length));
                connection.setDoOutput(true);
                connection.setDoInput(true);

                data = message.getBytes(StandardCharsets.UTF_8);
                OutputStream outputStream = connection.getOutputStream();

                // передаем данные на сервер
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();
                data = null;
                connection.connect();
                int responseCode = connection.getResponseCode();

                // передаем ответ сервер
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                if (responseCode == 200) {     // Если все ОК (ответ 200)
                    inputStream = connection.getInputStream();

                    byte[] buffer = new byte[8192]; //размер буфера

                    // читаем ответ
                    int byteRead;

                    while ((byteRead = inputStream.read(buffer)) != -1) {
                        arrayOutputStream.write(buffer, 0, byteRead);
                    }

                    data = arrayOutputStream.toByteArray();
                    resultStr = new String(data, StandardCharsets.UTF_8);
                } else {
                    resultStr = " код ответа: " + responseCode;
                }
                connection.disconnect();

            } catch (MalformedURLException e) {
                resultStr = "MalformedURLException:" + e.getMessage();
            } catch (IOException e) {
                resultStr = "IOException:" + e.getMessage();
            } catch (Exception e) {
                resultStr = "Exception:" + e.getMessage();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultStr;
    }

//    public String getResultStr() {
//        return resultStr;
//    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
    }
}