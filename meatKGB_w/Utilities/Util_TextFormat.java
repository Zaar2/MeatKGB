package com.zaar2.meatKGB_w.Utilities;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.zaar2.meatKGB_w.R;

public class Util_TextFormat {

    /**
     * Преобразует массив из строковых ресурсов в массив String
     *
     * @param resID IDкод двухуровневого массива из ресурсов
     * @return возвращает String[][]
     */
    protected static String[][] obtaining_twoLevelArray(int resID, Resources resources) {
        TypedArray typedArray = resources.obtainTypedArray(resID);
        int n = typedArray.length();
        String[][] result = new String[n][];
        for (int i = 0; i < n; i++) {
            int id = typedArray.getResourceId(i, 0);
            assert id > 0;
            result[i] = resources.getStringArray(id);
        }
        typedArray.recycle();
        return result;
    }

    /**
     * если значение числа меньше 10, то формат возвращенного строкового представления '00'
     */
    protected static String dateFormatIntToStr(int num) {
        if (num < 10 && num >= 0) {
            return "0" + num;
        } else if (num < 0 && num > -10) {
            return "-0" + (num * (-1));
        } else
            return String.valueOf(num);
    }

    protected static String servName_to_label(String servName, Context context) {
        if (servName.equals(context.getString(R.string.tblServ_meatShop))) {
            return context.getString(R.string.label_meat_shop);
        } else if (servName.equals(context.getString(R.string.tblServ_coldShop))) {
            return context.getString(R.string.label_cold_shop);
        } else if (servName.equals(context.getString(R.string.servNameShop_admin))) {
            return context.getString(R.string.label_admin_shop);
        }
        return "";
    }

    protected static String labelName_to_servName(String labelName, Context context) {
        if (labelName.equals(context.getString(R.string.label_meat_shop))) {
            return context.getString(R.string.tblServ_meatShop);
        } else if (labelName.equals(context.getString(R.string.label_cold_shop))) {
            return context.getString(R.string.tblServ_coldShop);
        } else if (labelName.equals(context.getString(R.string.label_admin_shop))) {
            return context.getString(R.string.servNameShop_admin);
        }
        return "";
    }

    protected static int finding_numberOf_decimalPlaces(String num) {
        double numDl = Double.parseDouble(num);
        String resStr = String.valueOf(separationOfRemain(numDl));
        if (Integer.parseInt(resStr) <= 0) return 0;
        int result = resStr.length();
        return result;
    }

    private static int separationOfRemain(double someNumber) {
        return Integer.parseInt(String.valueOf(someNumber).substring(
                getCountsOfDigits((int) someNumber) + 1));
    }

    private static int getCountsOfDigits(long number) {
        return (number == 0) ? 1 : (int) Math
                .ceil(Math.log10(Math.abs(number) + 0.5));
    }
}
