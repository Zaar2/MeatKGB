package com.zaar2.meatKGB_w;

import static com.zaar2.meatKGB_w.Const.ALFA_BACKGROUND;
import static com.zaar2.meatKGB_w.Const.APP_PREFERENCES;

import com.zaar2.meatKGB_w.Fragments.Fragment_identificationUser;
import com.zaar2.meatKGB_w.ListView.ListView_utilities;
import com.zaar2.meatKGB_w.ListView.MyAdapter;
import com.zaar2.meatKGB_w.ListView.myItemListView;
import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;
import com.zaar2.meatKGB_w.server.Entry_toServerUtilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Fragment_identificationUser.MyDialogListener {

    /**
     * для какого цеха создан этот экземпляр активности - зависит от идентификации юзера
     */
    private String typeShop_label;
    private Button button_insert;
    private Button button_delete;
    //    private Button button_update;
    private Button button_select;
    private Button button_setting;
    private TextView textView_dateProduced;

    private Spinner spinner_hour;
    private Spinner spinner_nameProduct;
    private ListView listView_main;

    private boolean inward_clearance = false;
    private int numView_OfValue_parameter;
    private ArrayList<myItemListView> itemsForLists = new ArrayList<>();
    private String clarificationRequest_default = "null";
    //    private String[] spinnerItemsList_nameProduct;
    private boolean fragment_identificationUser_isVisible;
    private String shortStr_worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        identificationUser();
//        start();
    }

    private void start() {
        if (inward_clearance) {
            setContentView(R.layout.activity_main);
            initVariables();
            initViews();
            initValueViews();
            settingViews();
            initListView(itemsForLists);

            buttonInsert_onClick();
            buttonDelete_onClick();
//        buttonUpdate_onClick();
            buttonSelect_onClick();
            buttonSetting_onClick();
            listView_onItemClick();
            textViewDateProduced_onClick();
        }
    }

    private void initVariables() {
        typeShop_label = getString(R.string.label_meat_shop);
        initItemsListView(getString(R.string.typeListView_records_forShop_forPeriod), null);
        clarificationRequest_default = getString(R.string.clarificationRequest_viewRecords);
        fragment_identificationUser_isVisible = false;
        shortStr_worker =
                initShortNameOf_shop(Params_http.getINSTANCE().getWorkshop())
                        + "/"
                        + Params_http.getINSTANCE().getAppointment()
                        + " - "
                        + Params_http.getINSTANCE().getNameShort();
    }

    private String initShortNameOf_shop(String nameShop) {
        if (Params_http.getINSTANCE().isInit()) {
            if (nameShop.equals(getString(R.string.tblServ_meatShop)))
                return getString(R.string.labelMini_meat_shop);
            if (nameShop.equals(getString(R.string.tblServ_coldShop)))
                return getString(R.string.labelMini_cold_shop);
            if (nameShop.equals(getString(R.string.servNameShop_admin)))
                return getString(R.string.labelMini_admin_shop);
        }
        return "null";
    }

    private void initViews() {
        Entry_toUtilities.check_countParamsOf_connParams(this);
        findViewById(R.id.layout_mainActivity).getBackground().setAlpha(ALFA_BACKGROUND);
//        findViewById(R.id.layout_mainActivity).setAlpha(0.8f);

        button_insert = findViewById(R.id.btn_insert);
        button_delete = findViewById(R.id.btn_delete);
//        button_update = findViewById(R.id.btn_search);
        button_select = findViewById(R.id.btn_select);
        button_setting = findViewById(R.id.btn_setting);

        textView_dateProduced = findViewById(R.id.textView_dateProduced);

        spinner_hour = findViewById(R.id.spinner_hour);
        spinner_nameProduct = findViewById(R.id.spinner_product);

        listView_main = findViewById(R.id.listView_main);

        findViewById(R.id.btn_search).setVisibility(View.GONE);
    }

    private void initValueViews() {
        numView_OfValue_parameter =
                ((LinearLayout)
                        ((LinearLayout) findViewById(R.id.layout_panelOfOutgoingData)).getChildAt(2)
                ).getChildCount() - 1;
        Entry_toUtilities.initView_panel_labelView(
                findViewById(R.id.layout_panelOfOutgoingData),
                getResources().getStringArray(R.array.label_outgoingData)
        );
        Entry_toUtilities.initView_DateProduced(textView_dateProduced, getString(R.string._current), getResources());
        Entry_toUtilities.initLabels_forViewsOf_linearLayout(
                findViewById(R.id.label_forListView_mainActivity),
                Entry_toUtilities.obtaining_twoLevelArray(R.array.matching_columnDB_to_labelView_PRODUCED, getResources())
        );
        spinner_hour.setAdapter(
                fillingAdapter_spinner(this, R.layout.spiner_item_custom, R.array.time_of_produced)
        );
        String[] spinnerItemsList = init_spinnerItemsList(getString(R.string.label_product));
        spinner_nameProduct.setAdapter(
                fillingAdapter_spinner(this, R.layout.spiner_item_custom, spinnerItemsList)
        );
        ((TextView) findViewById(R.id.textView_worker)).setText(shortStr_worker);
    }

    private String[] init_spinnerItemsList(String object) {
        String[] spinnerItemsList = null;
        if (object.equals(getString(R.string.label_product))) {
            Entry_toServerUtilities.exchange_messages_with_server(
//                    findViewById(R.id.layout_panelOfOutgoingData),
                    getResources().getString(R.string.typeQuery_select),
                    getString(R.string.clarificationRequest_viewRecords),
                    getString(R.string._update_name_product),
                    null,
//                    null, null,
                    this
            );
            spinnerItemsList = Entry_toUtilities.initItemsList_Spinner(
                    getString(R.string.tblServ_meatShop),
                    getString(R.string.typeListView_records_forProductList),
                    getString(R.string.tblServ_nameProduct),
                    this
            );
        }
        if (spinnerItemsList == null) {
            spinnerItemsList = new String[1];
            spinnerItemsList[0] = "";
        }
        return spinnerItemsList;
    }

    private void settingViews() {
//        button_update.setEnabled(false);
    }

    private void initItemsListView(String typeListView, String[][] intermediateDate) {
        String tableDB = null;
        if (typeShop_label.equals(getString(R.string.label_meat_shop))) {
            tableDB = getString(R.string.tblServ_meatShop);
        }
        itemsForLists = ListView_utilities.initItemsList_listView(typeListView, tableDB, null, intermediateDate, this);
    }

    private void initListView(ArrayList<myItemListView> itemsForLists) {
        MyAdapter myAdapter = null;
        String[][] matching_columnDB_to_labelView = Entry_toUtilities.obtaining_twoLevelArray(R.array.matching_columnDB_to_labelView_PRODUCED, getResources());

        if (itemsForLists != null) {
            myAdapter = new MyAdapter(itemsForLists, matching_columnDB_to_labelView, this);
        }
        listView_main.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_main.setAdapter(myAdapter);
    }

    private ArrayAdapter<String> fillingAdapter_spinner(
            Context context,
            int resID_itemLayout,
            int resID_arrayStrings
    ) {
        return new ArrayAdapter<>(
                context,
                resID_itemLayout,
                context.getResources().getStringArray(resID_arrayStrings)
        );
    }

    private ArrayAdapter<String> fillingAdapter_spinner(
            Context context,
            int resID_itemLayout,
            String[] spinnerItemsList
    ) {
        return new ArrayAdapter<>(
                context,
                resID_itemLayout,
                spinnerItemsList
        );
    }

    private void updateActivityData(String countReset) {
        initItemsListView(getString(R.string.typeListView_records_forShop_forPeriod), null);
        initListView(itemsForLists);
        reset(textView_dateProduced.getText().toString(), countReset);
    }

    private void reset(String dateReset, String countReset) {
        Entry_toUtilities.initView_DateProduced(textView_dateProduced, dateReset, getResources());
        Entry_toUtilities.initCount_default(
                ((EditText) ((LinearLayout) findViewById(R.id.layout_count)).getChildAt(numView_OfValue_parameter)),
                countReset
        );
        Entry_toUtilities.init_idDB_default(findViewById(R.id.textView_idDB));
    }

    private void textViewDateProduced_onClick() {
        textView_dateProduced.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Entry_toUtilities.callDatePicker(textView_dateProduced, MainActivity.this);
                    }
                }
        );
    }

    private void buttonInsert_onClick() {
        button_insert.setOnClickListener(v -> {
            String countReset = null;
            if (Entry_toUtilities.check_countParamsOf_connParams(this)) {
                Bundle bundleVal = Entry_toUtilities.prepare_bundleVal_forPrepareMessage(
                        findViewById(R.id.layout_panelOfOutgoingData),
                        getResources().getString(R.string.typeQuery_insert),
                        this
                );
                String result = Entry_toServerUtilities.exchange_messages_with_server(
//                        findViewById(R.id.layout_panelOfOutgoingData),
                        getResources().getString(R.string.typeQuery_insert),
                        getString(R.string.clarificationRequest_insertRecords_oneRecord),
                        typeShop_label,
                        bundleVal,
//                        "null", "null",
                        this
                );
                if (result == null) {
                    countReset = ((EditText) ((LinearLayout) findViewById(R.id.layout_count)).getChildAt(numView_OfValue_parameter)).getText().toString();
                }
//                button_update.setEnabled(false);
                updateActivityData(countReset);
            }
        });
    }

    private void buttonDelete_onClick() {
        button_delete.setOnClickListener(v -> {
            if (Entry_toUtilities.check_countParamsOf_connParams(this)) {
                Bundle bundleVal = Entry_toUtilities.prepare_bundleVal_forPrepareMessage(
                        findViewById(R.id.layout_panelOfOutgoingData),
                        getResources().getString(R.string.typeQuery_delete),
                        this
                );
                Entry_toServerUtilities.exchange_messages_with_server(
//                        findViewById(R.id.layout_panelOfOutgoingData),
                        getResources().getString(R.string.typeQuery_delete),
                        getString(R.string.clarificationRequest_deleteRecords_oneRecord),
                        typeShop_label,
                        bundleVal,
//                        "null", "null",
                        this
                );
//                button_update.setEnabled(false);
                updateActivityData(null);
            }
        });
    }

//    private void buttonUpdate_onClick() {
//        button_update.setOnClickListener(v -> {
//            if (Entry_toUtilities.check_countParamsOf_connParams(this)) {
//                Entry_toServerUtilities.exchange_messages_with_server(
////                        findViewById(R.id.layout_panelOfOutgoingData),
//                        getResources().getString(R.string.typeQuery_select),
//                        getString(R.string.clarificationRequest_viewRecords),
//                        typeShop,
//                        ,
////                        "null", "null",
//                        this
//                );
////                button_update.setEnabled(false);
//                updateActivityData();
//            }
//        });
//    }

    private void buttonSelect_onClick() {
        button_select.setOnClickListener(v -> {
            if (Entry_toUtilities.check_countParamsOf_connParams(this)) {
                Entry_toServerUtilities.exchange_messages_with_server(
//                        findViewById(R.id.layout_panelOfOutgoingData),
                        getString(R.string.typeQuery_select),
                        getString(R.string.clarificationRequest_viewRecords),
                        typeShop_label,
                        null,
//                        "null", "null",
                        this
                );
//                button_update.setEnabled(false);
                updateActivityData(null);
            }
        });
    }

    private void buttonSetting_onClick() {
        button_setting.setOnClickListener(v -> {
            Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
            startActivityForResult(intent_setting, 1);
//                initNewActivity(SettingActivity.class, getResources().getString(R.string.btn_setting)));
        });
    }

    private void listView_onItemClick() {
        listView_main.setOnItemClickListener((parent, view, position, id) -> {
            Entry_toUtilities.initValue_panel_fromItemListView(
                    findViewById(R.id.layout_panelOfOutgoingData),
                    (LinearLayout) ((LinearLayout) view).getChildAt(0)
            );
//            button_update.setEnabled(true);
        });
    }

    private void initNewActivity(Class<?> cls, String valueExtra) {
        Intent intent = new Intent(
                getApplicationContext(),
                cls
        );
        if (valueExtra != null)
            intent.putExtra(getResources().getString(R.string._type_listView), valueExtra);
        startNewActivity(intent);
    }

    private void startNewActivity(Intent intent) {
        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * <p></p>
     * <p>Достаем из preferences логин и пароль пользователя.</p>
     * <p>Если они есть, то отсылаем их на сервер для верификации.</p>
     * <p>Если логин и пароль верифицированны, то сервер высылает нам текущие параметры для доступа к БД.
     * Если нет - высылает false</p>
     * <p>Записываем параметры в class:Params_http.</p>
     * <p>Если логина и пароля нет в preferences или они неверифицированы,
     * то запускаем идентификацию нового пользователя.</p>
     */
    private void identificationUser() {
        SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        if (
                preferences.contains(getString(R.string.column_parameterDB_user_login))
                        && preferences.contains(getString(R.string.column_parameterDB_pass))
        ) {
            String user_login = preferences.getString(getString(R.string.column_parameterDB_user_login), "");
            String usrPass = preferences.getString(getString(R.string.column_parameterDB_pass), "");
            if (!user_login.equals("") && !usrPass.equals("")) {
                Bundle bundleVal = new Bundle();
                bundleVal.putString(getString(R.string.tagsServ_type), getString(R.string._identificationUser));
                bundleVal.putString(getString(R.string.tagsServ_query_type), getString(R.string.typeQuery_identification_user));
                bundleVal.putString(getString(R.string.column_parameterDB_user_login), user_login);
                bundleVal.putString(getString(R.string.column_parameterDB_pass), usrPass);

                verificationUser(bundleVal);
            } else {
                //run identification new user
                identification_new_user();
            }
        } else {
            //run identification new user
            identification_new_user();
        }
    }

    private void identification_new_user() {
        Fragment_identificationUser fragment_identificationUser = new Fragment_identificationUser();
        FragmentManager manager = getSupportFragmentManager();
        String tag = getString(R.string._identificationUser);
        fragment_identificationUser.show(manager, tag);
    }

    @Override
    public void myDialogResult(Bundle incoming_bound) {
        verificationUser(incoming_bound);
    }

    private void verificationUser(Bundle inputBundle) {
        SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        Bundle bundleVal = new Bundle();
        Set<String> keys = inputBundle.keySet();
        for (String key : keys) {
            if (
                    !key.equals(getString(R.string.tagsServ_type))
                            && !key.equals(getString(R.string.tagsServ_query_type))
            ) {
                bundleVal.putString(key, inputBundle.getString(key));
            }
        }
        String
                queryType = inputBundle.getString(getString(R.string.tagsServ_query_type)),
                clarificationQueryType = getString(R.string.clarificationRequest_identification_user),
                destinationObject = inputBundle.getString(getString(R.string.tagsServ_type));

        String response = Entry_toServerUtilities.exchange_messages_with_server(
                queryType,
                clarificationQueryType,
                destinationObject,
                bundleVal,
                this
        );
        if (response.equals("ok")) {
            inward_clearance = true;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getString(R.string.column_parameterDB_user_login), bundleVal.getString(getString(R.string.column_parameterDB_user_login)));
            editor.putString(getString(R.string.column_parameterDB_pass), bundleVal.getString(getString(R.string.column_parameterDB_pass)));
            editor.apply();
        } else {
            if (
                    preferences.contains(getString(R.string.column_parameterDB_user_login))
                            && preferences.contains(getString(R.string.column_parameterDB_pass))
            ) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
            }
        }
        if (inward_clearance) start();
        else identificationUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String responseActivitySetting = data.getStringExtra("result");
        if (responseActivitySetting.equals("reset")) {
            finish();
        }
    }
}