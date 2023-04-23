package com.zaar2.meatKGB_w.Utilities;

import android.content.Context;
import android.widget.Spinner;

import com.zaar2.meatKGB_w.ListView.ListView_utilities;
import com.zaar2.meatKGB_w.ListView.myItemListView;
import com.zaar2.meatKGB_w.R;
import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;

import java.util.ArrayList;

public class Util_Spinner {

    protected static String[] initItemsList_Spinner(
            String typeShop,
            String typeList,
            String tableDB,
            Context context
    ) {
        ArrayList<myItemListView> intermediateListItems
                = ListView_utilities.initItemsList_listView(typeList, tableDB, null, null, context);
        ArrayList<String> list = new ArrayList<>();
        if (intermediateListItems != null) {
            for (myItemListView item : intermediateListItems) {
                int index;
                index = item.find_idName(context.getString(R.string.column_parameterDB_workshop));
                String workshop = item.getValue_byID(index);
                if (workshop.equals(typeShop)) {
                    index = item.find_idName(context.getString(R.string.column_parameterDB_product));
                    list.add(item.getValue_byID(index));
                }
            }
        } else return null;
        if (list.size() == 0) return null;
        else return list.toArray(new String[0]);
    }

    protected static int selectIndex_forSpinner(Spinner spinner, int value_num, String value_str) {
        String val_str;
        int result = -1;
        if (value_num >= 0) {
            val_str = Entry_toUtilities.dateFormatIntToStr(value_num);
        } else {
            val_str = value_str;
        }
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(val_str)) {
                result = i;
                break;
            }
        }
        return result;
    }

    protected static void selectValue_forSpinner(Spinner spinner, int value_num, String value_str) {
        String val_str;
        if (value_num >= 0) {
            val_str = Entry_toUtilities.dateFormatIntToStr(value_num);
        } else {
            val_str = value_str;
        }
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(val_str)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}