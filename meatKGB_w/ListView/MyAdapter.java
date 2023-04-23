package com.zaar2.meatKGB_w.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaar2.meatKGB_w.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private ArrayList<myItemListView> itemsList = null;
    private final String[][] matching_columnDB_to_labelView;
    private String nameColumn_dateProduced;
    private final SimpleDateFormat dateFormat_income = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat dateFormat_outcome = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    /**
     * @param itemsList набор данных для отображения в View ( ArrayList&lt;myItemForListView> ).
     *                  <p>в зависимости от количества тегов/колонок существующих в поданном классе myItemForListView,
     *                  изменяется и макет/layout для каждого item-а.
     *                  Это значение возвращается не статическим методом  - int myItemForListView.getCountTags()</p>
     */
    public MyAdapter(ArrayList<myItemListView> itemsList, String[][] matching_columnDB_to_labelView, Context context) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (itemsList == null) {
            this.itemsList.add(new myItemListView());
        } else this.itemsList = itemsList;
        this.matching_columnDB_to_labelView = matching_columnDB_to_labelView;
        nameColumn_dateProduced = context.getString(R.string.column_parameterDB_date);
    }


    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        myItemListView itemForList = getItemForList(position);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.label_item_list_view, parent, false);
        }
        assert view != null;
        /**
         Заполняем элементы строки
         */
        LinearLayout view_textView = ((LinearLayout) ((LinearLayout) view).getChildAt(0));
        for (int i = 0; i < view_textView.getChildCount(); i++) {
            int id = itemForList.find_idName(matching_columnDB_to_labelView[i][1]);
            String value;
            if (id != -1) {
                value = itemForList.getValue_byID(id);
                if (matching_columnDB_to_labelView[i][1].equals(nameColumn_dateProduced)) {
                    value = convertDate(value);
                }
            } else {
                value = "non";
            }
            ((TextView) ((LinearLayout) view_textView).getChildAt(i)).setText(value);
        }
        ((CheckBox) ((LinearLayout) view).getChildAt(1)).setChecked(false);
        return view;
    }

    private myItemListView getItemForList(int position) {
        return (myItemListView) getItem(position);
    }

    public String convertDate(String date_servFormat) {
        String date_appFormat = "non";
        if (!date_servFormat.equals("non")) {
            try {
                if (!date_servFormat.equals("null")) {
                    Date parseDate = dateFormat_income.parse(date_servFormat);
                    assert parseDate != null;
                    date_appFormat = dateFormat_outcome.format(parseDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date_appFormat;
    }
}