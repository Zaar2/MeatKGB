package com.zaar2.meatKGB_w.parsTo;

import android.content.Context;

import java.util.ArrayList;

public class Entry_parserTo {

    public static ArrayList<parsedClass> parse_toParsedClass(String json_string, String typeOf_parsedClass, Context context) {
        return JSON_parser.json_toParsedClass(json_string, typeOf_parsedClass, context);
    }

//    public static String parse_toString(ArrayList<parsedClass> parsedClassArrayList, String query_source, Context context) {
//        return parsedClass.parsedClass_toString(parsedClassArrayList, query_source, context);
//    }

    public static String parse_toString(String[][][] inputString, String query_source, Context context) {
        return FormalizedString_parser.parse_toString(inputString, query_source, context);
    }

    public static ArrayList<String[][]> parseTo_formalizedString(ArrayList<parsedClass> array_parsedClass, Context context) {
        return FormalizedString_parser.parsTo_formalizedString(array_parsedClass, context);
    }
}
