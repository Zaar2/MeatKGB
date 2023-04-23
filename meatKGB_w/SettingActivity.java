package com.zaar2.meatKGB_w;

import static com.zaar2.meatKGB_w.Const.ALFA_BACKGROUND;
import static com.zaar2.meatKGB_w.Const.APP_PREFERENCES;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zaar2.meatKGB_w.Fragments.Fragment_settings_dbConn;
import com.zaar2.meatKGB_w.Fragments.Fragment_utilities;

public class SettingActivity extends AppCompatActivity {

    private Button button_settingOf_dbConnection;

    private boolean setting_dbConnection_isVisible;

    private Fragment_settings_dbConn fragment_settings_dbConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initVariables();
        settingViews();
        fillView();

        button_settingOf_dbConnection_onClick();
        button_resetIdentification();
//        button_updateProductList_onClick();
    }

    private void initView() {
        findViewById(R.id.layout_settingActivity).getBackground().setAlpha(ALFA_BACKGROUND);

        button_settingOf_dbConnection = findViewById(R.id.btn_settingOf_dbConnection);
    }

    private void initVariables() {
        setting_dbConnection_isVisible = false;
    }

    private void settingViews() {
    }

    private void fillView() {
    }

//    private void button_updateProductList_onClick(){
//        findViewById(R.id.btn_update_nameProduct).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String result;
//                if (Utilities.check_countParamsOf_connParams(getApplicationContext())) {
//                    result = Entry_toServerUtilities.exchange_messages_with_server(
//                            null,
//                            getResources().getString(R.string.typeQuery_select),
//                            getString(R.string.clarificationRequest_viewRecords),
//                            getString(R.string._update_name_product),
//                            null, "null","null",
//                            getApplicationContext()
//
//                    );
//                    initItemsListView(typeListView, null);
//                    initListView(itemsForLists, typeListView);
//                }
//            }
//        });
//    }

    private void button_settingOf_dbConnection_onClick() {
        button_settingOf_dbConnection.setOnClickListener(v ->
                setting_dbConnection_isVisible = Fragment_utilities.manageFragments_settings(
                        setting_dbConnection_isVisible,
                        fragment_settings_dbConn,
                        getString(R.string._dbConnection),
                        getSupportFragmentManager().beginTransaction(),
                        null,
                        this
                ));
    }

    private void button_resetIdentification() {
        findViewById(R.id.btn_resetIdentification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent=new Intent();
                intent.putExtra("result","reset");
                setResult(-1,intent);
                finish();
            }
        });
    }
}