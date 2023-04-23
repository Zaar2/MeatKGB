package com.zaar2.meatKGB_w.Utilities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zaar2.meatKGB_w.Const;
import com.zaar2.meatKGB_w.R;

import java.text.DateFormat;
import java.util.Calendar;

public class Util_PanelOfData {

    /**
     * Заполняются названия полей в панели ввода данных.
     * @param layoutPanel ссылка на панель ввода данных
     * @param dataOfView массив строк с названиями полей
     */
    protected static void initView_panel_labelView(LinearLayout layoutPanel, String[] dataOfView) {
        int textView = 0;
        for (int i = 1; i < dataOfView.length; i++) {
            ((TextView)
                    ((LinearLayout) layoutPanel.getChildAt(i)).getChildAt(textView)
            ).setText(dataOfView[i]);
        }
    }

    /**
     * Вставляет дату в указанный TextView.
     * @param textView_dateProduced объект в котором необходима дата
     * @param str_date необходимая дата (если значение 'current' -> значит нужно вставить текущую дату)
     * @param resources ссылка на ресурсы
     */
    protected static void initView_DateProduced(TextView textView_dateProduced, String str_date, Resources resources) {
        StringBuilder valueTextView = new StringBuilder();
        if (str_date.equals(resources.getString(R.string._current))) {
            valueTextView.append(DateFormat.getDateInstance(DateFormat.SHORT).format(Calendar.getInstance().getTime()));
        } else {
            valueTextView.append(str_date);
        }
        textView_dateProduced.setText(
                valueTextView
        );
    }

    /**
     * Заполняет панель ввода данных при клике на listViewItem
     * @param layoutPanel_dest ссылка на панель ввода данных
     * @param layout_source ссылка на layout of listViewItem - содержащий textViews с данными для заполнения панели
     */
    protected static void initValue_panel_fromItemListView(LinearLayout layoutPanel_dest, LinearLayout layout_source) {
        ((TextView) layoutPanel_dest.findViewById(R.id.textView_idDB)).setText(
                ((TextView) layout_source.findViewById(R.id.tv_id_listView)).getText()
        );
        ((TextView) layoutPanel_dest.findViewById(R.id.textView_dateProduced)).setText(
                ((TextView) layout_source.findViewById(R.id.tv_date_produced_listView)).getText()
        );
        Entry_toUtilities.selectValue_forSpinner(
                (Spinner) layoutPanel_dest.findViewById(R.id.spinner_product),
                -1,
                (String) ((TextView) layout_source.findViewById(R.id.tv_name_product_listView)).getText()
        );
        ((EditText) layoutPanel_dest.findViewById(R.id.editText_count)).setText(
                ((TextView) layout_source.findViewById(R.id.tv_count_listView)).getText()
        );
        Entry_toUtilities.selectValue_forSpinner(
                (Spinner) layoutPanel_dest.findViewById(R.id.spinner_hour),
                Integer.parseInt((String) ((TextView) layout_source.findViewById(R.id.tv_time_produced_listView)).getText()),
                ""
        );
    }

    /**
     * Формирует данные для отправки на сервер
     * @param layout_source ссылка на layout панели ввода данных - содержащий textViews с данными для заполнения панели
     */
    protected static Bundle prepare_bundleVal_forPrepareMessage(
            LinearLayout layout_source,
            String query_type,
//            String clarificationRequest,
//            String destinationObject,
            Context context
    ) {
        Bundle bundleVal = new Bundle();
        if (query_type.equals(context.getString(R.string.typeQuery_select))){
            return null;
        }else if (query_type.equals(context.getString(R.string.typeQuery_delete))){
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_id),
                    ((TextView) layout_source.findViewById(R.id.textView_idDB)).getText().toString()
            );
        }else if (query_type.equals(context.getString(R.string.typeQuery_insert))){
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_date),
                    ((TextView) layout_source.findViewById(R.id.textView_dateProduced)).getText().toString()
            );
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_name_user),
                    ((TextView)
                            ((LinearLayout) layout_source.findViewById(R.id.layout_worker))
                                    .getChildAt(1))
                            .getText().toString()
            );
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_product),
                    ((Spinner) layout_source.findViewById(R.id.spinner_product))
                            .getSelectedItem().toString()
            );
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_count),
                    ((EditText) layout_source.findViewById(R.id.editText_count))
                            .getText().toString()
            );
            bundleVal.putString(
                    context.getString(R.string.column_parameterDB_time_produce),
                    ((Spinner) layout_source.findViewById(R.id.spinner_hour))
                            .getSelectedItem().toString()
            );
        }
        return bundleVal;
    }

    protected static void initCount_default(EditText editText_count, String value) {
        if (value != null) {
            editText_count.setText(value);
        } else editText_count.setText("");
    }

    protected static void init_idDB_default(TextView textView_idDB){
        textView_idDB.setText("");
    }
}
