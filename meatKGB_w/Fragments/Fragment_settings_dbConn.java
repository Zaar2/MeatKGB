package com.zaar2.meatKGB_w.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zaar2.meatKGB_w.R;
import com.zaar2.meatKGB_w.Params_http;

public class Fragment_settings_dbConn extends Fragment {
    private View mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_settings_db_conn, container, false);

        initViews();
//        button_save_dbConnParams_onClick();
//        button_delete_dbConnParams_onClick();
        return mainView;
    }

    private void initViews() {
        fillViews_dbConnection(
                R.id.groupOf_setupOptionOf_dbConnection,
                R.array.labels_settingActivity_dbConnection
//                R.string.strDB_NAME_table_dbConnParam
        );
    }

    /**
     * <p>Заполнение группы лайаутов, каждый из которых содержит 2 TextViews:
     * [0] - название; [1] - значение.</p>
     * <p>label - название параметра, value - значение параметра (достается из локальной БД)</p>
     * @param id_viewGroup id лайаута содержащего группу
     * @param id_arrStr_label id строкового массива с названиями параметров
//     * @param id_str_nameTableDB id строки с названием таблицы в локальной БД, из которой достаются данные
     */
    private void fillViews_dbConnection(
            int id_viewGroup,
            int id_arrStr_label
//            int id_str_nameTableDB
    ){
        fillViews_dbConnParam_labels(
                mainView.findViewById(id_viewGroup),
                id_arrStr_label
        );
        fillViews_dbConnParam_values(
                mainView.findViewById(id_viewGroup),
                id_arrStr_label
//                id_str_nameTableDB
        );
    }

    /**
     * Заполнение textViews с названиями параметров, в каждом из лайаутов группы
     * @param groupView id лайаута группы
     * @param id_arrStr_label id строкового массива с названиями/labels
     */
    private void fillViews_dbConnParam_labels(
            LinearLayout groupView,
            int id_arrStr_label
    ) {
        String[] labels = getResources().getStringArray(id_arrStr_label);
        for (int i = 0; i < groupView.getChildCount(); i++) {
            ((TextView) ((LinearLayout) groupView.getChildAt(i)).getChildAt(0)).setText(
                    labels[i]
            );
        }
    }

    /**
     * Заполнение textViews со значениями параметров, в каждом из лайаутов группы
     * @param groupView id лайаута группы
     * @param id_arrStr_label id строкового массива с названиями/labels
//     * @param id_str_nameTableDB id строки с названием таблицы в локальной БД, из которой достаются данные
     */
    private void fillViews_dbConnParam_values(
            LinearLayout groupView,
            int id_arrStr_label
//            int id_str_nameTableDB
    ) {
        String[] labels = getResources().getStringArray(id_arrStr_label);
        if (Params_http.getINSTANCE().isInit()) {
            for (int i = 0; i < labels.length; i++) {
                if (labels[i].equals(getString(R.string.username)))
                    ((EditText) ((LinearLayout) groupView.getChildAt(i)).getChildAt(1)).setText(
                            Params_http.getINSTANCE().getUSER_NAME()
                    );
                if (labels[i].equals(getString(R.string.password)))
                    ((EditText) ((LinearLayout) groupView.getChildAt(i)).getChildAt(1)).setText(
                            Params_http.getINSTANCE().getPASS()
                    );
            }
        }

    }
//        for (int i = 0; i < groupView.getChildCount(); i++) {
//            String[][][] value = DB_0_EntryToDatabaseUtilities.select(
//                    getString(id_str_nameTableDB),
//                    new String[]{getString(R.string._value)},
//                    new String[][]{{getString(R.string._name), labels[i]}},
//                    null,
//                    true,
//                    getContext()
//            );
//            if (value != null) {
//                ((EditText) ((LinearLayout) groupView.getChildAt(i)).getChildAt(1)).setText(
//                        value[0][0][1]
//                );
//            }
//        }
//    }
//
//    private void button_delete_dbConnParams_onClick(){
//        mainView.findViewById(R.id.btn_delete_dbConnParams).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DB_0_EntryToDatabaseUtilities.deletingRows(
////                        getString(R.string.strDB_NAME_table_dbConnParam),
////                        requireContext()
////                );
//                Params_http.getINSTANCE().setInit(false);
//                Params_http.getINSTANCE().setUSER_NAME(null);
//                Params_http.getINSTANCE().setPASS(null);
//                fillViews_dbConnection(
//                        R.id.groupOf_setupOptionOf_dbConnection,
//                        R.array.labels_settingActivity_dbConnection
////                        R.string.strDB_NAME_table_dbConnParam
//                );
//            }
//        });
//    }
//    private void button_save_dbConnParams_onClick() {
//        mainView.findViewById(R.id.btn_save_dbConnParams).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LinearLayout groupView = mainView.findViewById(R.id.groupOf_setupOptionOf_dbConnection);
//                ArrayList<String[][]> formalizedString = new ArrayList<>();
//                for (int i = 0; i < groupView.getChildCount(); i++) {
//                    String value = ((EditText) ((LinearLayout) groupView.getChildAt(i)).getChildAt(1)).getText().toString();
//                    String label = ((TextView) ((LinearLayout) groupView.getChildAt(i)).getChildAt(0)).getText().toString();
//                    if (!value.equals("")) {
//                        DB_0_EntryToDatabaseUtilities.deletingRows(
//                                getString(R.string.strDB_NAME_table_dbConnParam),
//                                new String[][]{{getString(R.string._name), label}},
//                                requireContext()
//                        );
//                        formalizedString.add(new String[][]{
//                                {getString(R.string._name), label},
//                                {getString(R.string._value), value}
//                        });
//                    }
//                }
//                if (formalizedString.size() > 0) {
//                    DB_0_EntryToDatabaseUtilities.insertRows(
//                            formalizedString,
//                            getString(R.string.strDB_NAME_table_dbConnParam),
//                            requireContext()
//                    );
//                }
////                Params_http.getINSTANCE().init_or_update_variables(requireContext());
//            }
//        });
//    }
}