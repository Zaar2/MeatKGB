package com.zaar2.meatKGB_w.server;

import android.content.Context;

import com.zaar2.meatKGB_w.DB.DB_0_EntryToDatabaseUtilities;
import com.zaar2.meatKGB_w.Params_http;
import com.zaar2.meatKGB_w.R;
import com.zaar2.meatKGB_w.Utilities.Entry_toUtilities;
import com.zaar2.meatKGB_w.Utilities.Util_common;
import com.zaar2.meatKGB_w.parsTo.Entry_parserTo;
import com.zaar2.meatKGB_w.parsTo.parsedClass;

import java.util.ArrayList;

public class Response_processing {

    private static final String RESULT_OK = "ok";
    private static final String RESULT_FALSE = "false";

    /**
     * Производим операции с локальной БД
     *
     * @param serverResponse
     * @param context
     * @return
     */
    public static String response_processing(
            String serverResponse,
            String clarificationQueryType,
            String destinationObject,
            Context context
    ) {
        if (!serverResponse.equals("null")) {
            String typeOf_parsedClass;
            String[] destinationTable = null;
            if (destinationObject != null) {
                destinationTable = Entry_toUtilities.determine_tableDB_byRequesterParams(
                        context.getString(R.string.typeOfSide_local),
                        destinationObject,
                        clarificationQueryType,
                        context.getResources()
                );
            } else {
                if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_identification_user))) {
                    destinationTable = new String[]{context.getString(R.string.strDB_NAME_table_dbConnParam)};
                }
            }
            assert destinationObject != null;
            if (destinationObject.equals(context.getString(R.string._update_name_product))) {
                typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_productList);
            } else if (destinationObject.equals(context.getString(R.string.label_meat_shop))
                    || destinationObject.equals(context.getString(R.string.label_cold_shop))
            ) {
                if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_sumReport_forPeriod_forShop))) {
                    typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_reportShop);
                } else
                    typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_recordsShop);
            } else if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_identification_user))) {
                typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_identification_user);
            }

//            if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_viewRecords))) {
//            typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_recordShop);
//        } else if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_sumReport_forPeriod_forShop))) {
//            typeOf_parsedClass = context.getString(R.string.typeOf_parsedClass_reportShop);
//        }else if (clarificationQueryType.equals(context.getString(R.string.clarificationRequest_insertRecords_oneRecord))){
//            if
//        }
            else {
                Entry_toUtilities.viewAlert("Не определен тип конфигурации переменных для parsedClass: " +
                        "Response_processing.response_processing(...)24стр", context);
                return "Error response_processing: typeOf_parsedClass is NULL";
            }
            ArrayList<String[][]> formalizedString;
            ArrayList<parsedClass> array_parsedClass = Entry_parserTo.parse_toParsedClass(
                    serverResponse,
                    typeOf_parsedClass,
                    context
            );
//        for (parsedClass parsedClass : array_parsedClass) {
//            parsedClass.convertDate();
//        }
//            if (array_parsedClass.size() >= 0) {
            formalizedString = Entry_parserTo.parseTo_formalizedString(
                    array_parsedClass,
                    context
            );
//            } else
//                if (array_parsedClass.size() == 0){
//
//            }else
//                return "Error response_processing: size<=0";
            if (!clarificationQueryType.equals(context.getString(R.string.clarificationRequest_identification_user))) {
                assert destinationTable != null;
                DB_0_EntryToDatabaseUtilities.deletingRows(
                        destinationTable[0],
                        context
                );
                DB_0_EntryToDatabaseUtilities.insertRows(
                        formalizedString,
                        destinationTable[0],
                        context
                );
            } else {
                return processingFor_identification_user(
                        formalizedString,
                        context
                );
            }
            return RESULT_OK;
        } else
            return RESULT_FALSE;
    }

    private static String processingFor_identification_user(
            ArrayList<String[][]> formalizedString,
            Context context
    ) {
        boolean identificationFlag = false;
        String
                resultStr = null,
                username = null,
                pass = null,
                serverFile = null,
                nameShort = null,
                appointment = null,
                workshop = null;
        for (String[][] row : formalizedString) {
            for (String[] pair : row) {
                if (pair[0].equals("result")) {
                    if (pair[1].equals("true")) {
                        identificationFlag = true;
                    } else {
                        resultStr = pair[1];
                    }
                }
                if (pair[0].equals(context.getString(R.string.tagsServ_username))) {
                    username = pair[1];
                }
                if (pair[0].equals(context.getString(R.string.tagsServ_pass))) {
                    pass = pair[1];
                }
                if (pair[0].equals(context.getString(R.string.tagsServ_serverFile))) {
                    serverFile = pair[1];
                }
                if (pair[0].equals(context.getString(R.string.column_parameterDB_name_short))) {
                    nameShort = pair[1];
                }
                if (pair[0].equals(context.getString(R.string.column_parameterDB_appointment))) {
                    appointment = pair[1];
                }
                if (pair[0].equals(context.getString(R.string.column_parameterDB_workshop))) {
                    workshop = pair[1];
                }
            }
        }
        if (identificationFlag) {
            Params_http.getINSTANCE().setUSER_NAME(username);
            Params_http.getINSTANCE().setPASS(pass);
            Params_http.getINSTANCE().setPhpServerFile(serverFile);
            Params_http.getINSTANCE().setInit(true);
            Params_http.getINSTANCE().setAppointment(appointment);
            Params_http.getINSTANCE().setNameShort(nameShort);
            Params_http.getINSTANCE().setWorkshop(workshop);
        } else
            return resultStr;
        return RESULT_OK;
    }
}