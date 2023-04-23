package com.zaar2.meatKGB_w.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zaar2.meatKGB_w.R;

import java.util.Objects;

public class Fragment_identificationUser extends DialogFragment {
    private View mainView;
    private MyDialogListener myDialogListener;
    private Boolean passVisible = false;

//    String str_RESULT = "result";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_identification_user, container, false);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);

        initViews();
        button_OK_onClick();
        pass_open_close_onClick();
        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();
        int dialogWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.9f);
//        int dialogHeight = (int) (getResources().getDisplayMetrics().heightPixels * 0.8f);
        if (getDialog().getWindow() == null) return;
        getDialog().getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void initViews() {
    }

    private void button_OK_onClick() {
        mainView.findViewById(R.id.btn_OK_identificationUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_positive_productList(getString(R.string.typeQuery_identification_user));
                dismiss();
            }
        });
    }

    private void onClick_positive_productList(String source) {
        String
                log = ((EditText) mainView.findViewById(R.id.editText_userLogin)).getText().toString(),
                pass = ((EditText) mainView.findViewById(R.id.editText_userPass)).getText().toString();
        if (log.equals("")) log = " ";
        if (pass.equals("")) pass = " ";
        Bundle outbound_bundle = new Bundle();
        outbound_bundle.putString(getString(R.string.tagsServ_type), getString(R.string._identificationUser));
        outbound_bundle.putString(getString(R.string.tagsServ_query_type), source);
        //pairs of values: columns->value
        outbound_bundle.putString(getString(R.string.column_parameterDB_user_login), log);
        outbound_bundle.putString(getString(R.string.column_parameterDB_pass), pass);
        myDialogListener.myDialogResult(outbound_bundle);
    }

    private void pass_open_close_onClick() {
        mainView.findViewById(R.id.img_pass_open_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_pass = mainView.findViewById(R.id.editText_userPass);
                if (!passVisible) {
                    et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ((ImageView) v).setImageResource(R.drawable.pass_open);
                    passVisible = true;
                } else {
                    et_pass.setInputType(
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    );
                    et_pass.setSelection(et_pass.getText().length());
                    ((ImageView) v).setImageResource(R.drawable.pass_close);
                    passVisible = false;
                }
            }
        });
    }

    public interface MyDialogListener {
        void myDialogResult(Bundle outbound);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            myDialogListener = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DataListener");
        }
    }
}