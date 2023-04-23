package com.zaar2.meatKGB_w.Fragments;

import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
//    private View view;
//    private Spinner spinner_workshop;
//    private int id_layout = -1;
//    private MyDialogListener myDialogListener;
//    private Bundle outbound_bundle;
//    private Bundle income_bundle;
//    private String type_thisFragment = null;
//    private String tag;
//    private String[] names = null;
//    private String[] values = null;
//    private Boolean passVisible = false;

//    String str_RESULT = "result";

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        initVariables();
//
//        assert id_layout != -1;
//        view = inflater.inflate(id_layout, null);
//        if (
//                type_thisFragment.equals(getString(R.string._update_usrNames))
//                        || type_thisFragment.equals(getString(R.string._update_usrNames) + "_new")
//        )
//            initListeners_usrName();
//        else if (type_thisFragment.equals(getString(R.string._update_name_product)))
//            initListeners_productName();
//        initViews();
//        fillingViews();
//        return view;
//    }

//    private void initVariables() {
//        type_thisFragment = getTag();
//        assert type_thisFragment != null;
//        if (type_thisFragment.equals(getString(R.string._update_usrNames))) {
//            assert this.getArguments() != null;
//            income_bundle = this.getArguments();
//            id_layout = R.layout.fragment_edit_usr_names;
//            names = income_bundle.getStringArray(getString(R.string._name));
//            values = income_bundle.getStringArray(getString(R.string._value));
//        }
//        if (type_thisFragment.equals(getString(R.string._update_name_product))) {
//            id_layout = R.layout.fragment_insert_product_name;
//        }
//        if (type_thisFragment.equals(getString(R.string._update_usrNames) + "_new")) {
//            id_layout = R.layout.fragment_edit_usr_names;
//        }
//        outbound_bundle = new Bundle();
//    }

//    private void initViews() {
//        if (type_thisFragment.equals(getString(R.string._update_usrNames))
//                || type_thisFragment.equals(getString(R.string._update_usrNames) + "_new")
//        ) {
//            view.findViewById(R.id.btn_search).setVisibility(View.GONE);
//            spinner_workshop = view.findViewById(R.id.spinner_workshopWorker);
//            spinner_workshop.setAdapter(new ArrayAdapter<>(
//                    getContext(),
//                    R.layout.spiner_item_custom,
//                    getResources().getStringArray(R.array.allShops_forUsrNames)
//            ));
//            if (type_thisFragment.equals(getString(R.string._update_usrNames) + "_new")) {
//                view.findViewById(R.id.btn_delete).setVisibility(View.GONE);
//                view.findViewById(R.id.btn_modify).setVisibility(View.GONE);
//                view.findViewById(R.id.btn_select).setVisibility(View.GONE);
//            } else {
//                view.findViewById(R.id.btn_insert).setVisibility(View.GONE);
//            }
//        } else {
//            spinner_workshop = view.findViewById(R.id.spinner_workshopProductName);
//            spinner_workshop.setAdapter(new ArrayAdapter<>(
//                    getContext(),
//                    R.layout.spiner_item_custom,
//                    getResources().getStringArray(R.array.allShops_forProductList)
//            ));
//        }
//    }

//    private void fillingViews() {
//        if (type_thisFragment.equals(getString(R.string._update_usrNames))) {
//            for (int i = 0; i < names.length; i++) {
//                String name = names[i];
//                if (name.equals(getString(R.string.column_parameterDB_name_full))) {
//                    ((EditText) view.findViewById(R.id.editText_insert_fullName)).setText(values[i]);
//                } else if (name.equals(getString(R.string.column_parameterDB_name_short))) {
//                    ((EditText) view.findViewById(R.id.editText_insert_shortName)).setText(values[i]);
//                } else if (name.equals(getString(R.string.column_parameterDB_user_login))) {
//                    ((EditText) view.findViewById(R.id.editText_insert_usrLogin)).setText(values[i]);
//                } else if (name.equals(getString(R.string.column_parameterDB_appointment))) {
//                    ((EditText) view.findViewById(R.id.editText_insert_appointment)).setText(values[i]);
//                } else if (name.equals(getString(R.string.column_parameterDB_workshop))) {
////                    String valShop = Spinner_utilities.servName_to_label(values[i], requireContext());
//                    spinner_workshop.setSelection(
//                            Spinner_utilities.selectIndex_forSpinner(spinner_workshop, -1, values[i])
//                    );
//                } else if (name.equals(getString(R.string.column_parameterDB_pass))) {
//                    ((EditText) view.findViewById(R.id.editText_insert_pass)).setText(values[i]);
//                } else if (name.equals(getString(R.string.column_parameterDB_user_id))) {
//                    ((TextView) view.findViewById(R.id.textView_usrID)).setText(values[i]);
//                }
//            }
//        }
//    }

//    private void initListeners_productName() {
//        btnOk_onClick();
//        btnCancel_onClick();
//    }

//    private void initListeners_usrName() {
//        pass_open_close_onClick();
//        btnInsert();
//        btnDelete();
//        btnUpdate();
//    }

//    private void pass_open_close_onClick() {
//        view.findViewById(R.id.img_pass_open_close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText et_pass = view.findViewById(R.id.editText_insert_pass);
//                if (!passVisible) {
//                    et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    ((ImageView) v).setImageResource(R.drawable.pass_open);
//                    passVisible = true;
//                } else {
//                    et_pass.setInputType(
//                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
//                    );
//                    et_pass.setSelection(et_pass.getText().length());
//                    ((ImageView) v).setImageResource(R.drawable.pass_close);
//                    passVisible = false;
//                }
//            }
//        });
//    }

//    private void btnOk_onClick() {
//        view.findViewById(R.id.btn_OK).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type_thisFragment != null) {
//                    if (type_thisFragment.equals(getString(R.string._update_name_product)))
//                        onClick_positive_productList(getString(R.string.typeQuery_insert));
//                    dismiss();
//                }
//            }
//        });
//    }

//    private void onClick_positive_productList(String source) {
//        outbound_bundle = new Bundle();
//        //title
//        outbound_bundle.putBoolean(str_RESULT + "_flag", true);
//        //pairs of values: columns->value
//        outbound_bundle.putString(getString(R.string.tagsServ_type), getString(R.string._update_name_product));
//        outbound_bundle.putString(getString(R.string.tagsServ_query_type), source);
//        outbound_bundle.putString(getString(R.string.column_parameterDB_product), ((EditText) view.findViewById(R.id.editText_insertProductName)).getText().toString());
//        outbound_bundle.putString(getString(R.string.column_parameterDB_me), ((EditText) view.findViewById(R.id.editText_insertME)).getText().toString());
//        outbound_bundle.putString(
//                getString(R.string.column_parameterDB_workshop),
//                Entry_toUtilities.labelName_to_servName(spinner_workshop.getSelectedItem().toString(), requireContext())
//        );
//        myDialogListener.myDialogResult(outbound_bundle);
//    }

//    private void btnInsert() {
//        view.findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type_thisFragment.equals(getString(R.string._update_usrNames) + "_new"))
//                    onClick_button_usrNames(getString(R.string.typeQuery_insert));
//                dismiss();
//            }
//        });
//    }

//    private void btnDelete() {
//        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type_thisFragment.equals(getString(R.string._update_usrNames)))
//                    onClick_button_usrNames(getString(R.string.typeQuery_delete));
//                dismiss();
//            }
//        });
//    }

//    private void btnUpdate() {
//        view.findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type_thisFragment.equals(getString(R.string._update_usrNames)))
//                    onClick_button_usrNames(getString(R.string.typeQuery_update));
//                dismiss();
//            }
//        });
//    }

//    private void onClick_button_usrNames(String source) {
//        outbound_bundle = new Bundle();
//        outbound_bundle.putString(getString(R.string.tagsServ_type), getString(R.string._update_usrNames));
//        outbound_bundle.putString(getString(R.string.tagsServ_query_type), source);
//        outbound_bundle.putString(
//                getString(R.string.column_parameterDB_user_id),
//                ((TextView) view.findViewById(R.id.textView_usrID)).getText().toString()
//        );
//        if (!source.equals(getString(R.string.typeQuery_delete))) {
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_name_full),
//                    ((EditText) view.findViewById(R.id.editText_insert_fullName)).getText().toString()
//            );
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_name_short),
//                    ((EditText) view.findViewById(R.id.editText_insert_shortName)).getText().toString()
//            );
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_user_login),
//                    ((EditText) view.findViewById(R.id.editText_insert_usrLogin)).getText().toString()
//            );
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_appointment),
//                    ((EditText) view.findViewById(R.id.editText_insert_appointment)).getText().toString()
//            );
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_workshop),
//                    Entry_toUtilities.labelName_to_servName(spinner_workshop.getSelectedItem().toString(), requireContext())
////                    ((EditText) view.findViewById(R.id.spinner_insert_workshop)).getText().toString()
//            );
//            outbound_bundle.putString(
//                    getString(R.string.column_parameterDB_pass),
//                    ((EditText) view.findViewById(R.id.editText_insert_pass)).getText().toString()
//            );
//        }
//        outbound_bundle.putBoolean(str_RESULT + "_flag", true);
//        myDialogListener.myDialogResult(outbound_bundle);
//    }

//    private void btnCancel_onClick() {
//        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                outbound_bundle=new Bundle();
//                outbound_bundle.putBoolean(str_RESULT, false);
//                myDialogListener.myDialogResult(outbound_bundle);
//                dismiss();
//            }
//        });
//    }


//    public interface MyDialogListener {
//        void myDialogResult(Bundle outbound);
//    }

//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        try {
//            myDialogListener = (MyDialogListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement DataListener");
//        }
//    }
}