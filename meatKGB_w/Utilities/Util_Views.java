package com.zaar2.meatKGB_w.Utilities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaar2.meatKGB_w.R;

import java.util.Calendar;

public class Util_Views {

    /**
     * Заполняет шапку ListView, названия колонок.
     * Получает - layout for items of listView.
     * А также, набор наименований колонок
     * @param linearLayout_labelView содержит вложенный linearLayout с набором TextViews
     * @param labelsArray двухуровневый массив, состоит из парных значений,
     *                    <p>в каждой паре:
     *                    <p>[0]-название параметра для Views;</p>
     *                    <p>[1]-имя параметра/колонки в БД;</p>
     *                    </p>
     */
    protected static void initLabels_forViewsOf_linearLayout(LinearLayout linearLayout_labelView, String[][] labelsArray) {
        LinearLayout layout_textViews = linearLayout_labelView.findViewById(R.id.layout_textViews_ofItemListView);
        int n = layout_textViews.getChildCount();
        for (int i = 0; i < n; i++) {
            ((TextView) layout_textViews.getChildAt(i)).setText(labelsArray[i][0]);
        }
    }

    protected static void callDatePicker(TextView textView, Context context) {
        final Calendar calendar = Calendar.getInstance();
        int
                year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH),
                day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                R.style.my_dialog_theme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = Entry_toUtilities.dateFormatIntToStr(dayOfMonth)
                                + "." + Entry_toUtilities.dateFormatIntToStr((month + 1))
                                + "." + year;
                        textView.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    /**
     * @param view вьюха которую нужно скрыть или показать
     * @param isRun показать/VISIBLE - true или скрыть/GONE - false.
     */
    protected static void view_isVisible(View view, boolean isRun) {
        if (isRun) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * вставляет в textView дату в нужном для этого приложения формате
     *
     * @param textView куда вставить
     * @param value    кол-во дней, которые нужно добавить к текущей дате
     *                 (может быть положительным/увеличение или отрицательным/уменьшение)
     */
    protected static void initDate_default_first_or_LastDate(TextView textView, int value) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, value);
        int
                year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH) + 1,
                day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = Util_TextFormat.dateFormatIntToStr(day) + "." + Util_TextFormat.dateFormatIntToStr(month) + "." + year;
        textView.setText(date);
    }

    /**
     * Вставлет в первые два TextViews заданную строку
     * @param layout в какой layout вставлять (ограничения: первые два объекта должны быть - TextView)
     * @param labelText что вставлять
     */
    protected static void fillViewsLabel_first_or_Last_Date(LinearLayout layout, String labelText) {
        ((TextView) layout.getChildAt(0)).setText(labelText);
        ((TextView) layout.getChildAt(1)).setText(labelText);
    }
}
