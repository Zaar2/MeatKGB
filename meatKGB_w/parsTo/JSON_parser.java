package com.zaar2.meatKGB_w.parsTo;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class JSON_parser {
    private static boolean isJSONArray = false;
    private static boolean isJSONObject = false;

    protected static ArrayList<parsedClass> json_toParsedClass(String json_string, String typeOf_parsedClass, Context context) {
//        StringBuilder builder = new StringBuilder();

        json_string = normalizedStr(json_string);
        ArrayList<parsedClass> arraysRow_parsedClass = new ArrayList<>();
//        ArrayList<nameProductClass> arraysRow_nameProduct = null;

        //prepare variables
//        if (typeOf_parsedClass.equals(context.getResources().getString(R.string._query_source_produce))) {
//            arraysRow_parsedClass = new ArrayList<>();
//        } else if (typeOf_parsedClass.equals(context.getResources().getString(R.string._query_source_nameProduct))) {
//            arraysRow_nameProduct = new ArrayList<>();
//        }

        //parsing
        try {
            JSONArray data;
            JSONObject row;
            Gson g = new Gson();
            if (isJSONArray) {
                data = new JSONArray(json_string);
                for (int i = 0; i < data.length(); i++) {
                    row = data.getJSONObject(i);
                    parsedClass parsedClass = parsJSONObject(row, typeOf_parsedClass, g);
                    arraysRow_parsedClass.add(parsedClass);
                }
            } else if (isJSONObject) {
                row = new JSONObject(json_string);
                parsedClass parsedClass = parsJSONObject(row, typeOf_parsedClass, g);
                arraysRow_parsedClass.add(parsedClass);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysRow_parsedClass;
    }

    private static parsedClass parsJSONObject(JSONObject row, String typeOf_parsedClass, Gson g) {
        parsedClass parsedClass = g.fromJson(row.toString(), parsedClass.class);
        parsedClass.key_type = typeOf_parsedClass;
        return parsedClass;
    }

    private static String normalizedStr(String in) {
        int startIndex = in.indexOf("[");
        if (startIndex >= 0) {
            isJSONArray = true;
            return in.substring(startIndex);
        } else {
            startIndex = in.indexOf("{");
            if (startIndex >= 0) {
                isJSONObject = true;
                return in.substring(startIndex);
            } else return in;
        }
    }
}