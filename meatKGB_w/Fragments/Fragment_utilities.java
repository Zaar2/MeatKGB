package com.zaar2.meatKGB_w.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zaar2.meatKGB_w.R;

public class Fragment_utilities {

    public static boolean manageFragments_settings(
            boolean flag_isVisible,
            Fragment fragment,
            String typeFragment,
            FragmentTransaction fragmentTransaction,
            Bundle bundleVal,
            Context context
    ) {
        if (!flag_isVisible) {
            flag_isVisible = openFragment_settingsDbConn(fragment, typeFragment, fragmentTransaction, bundleVal, context);
        } else {
            if (fragment != null) {
                flag_isVisible = closeFragment(fragment, fragmentTransaction);
            }
        }
        return flag_isVisible;
    }

    private static boolean openFragment_settingsDbConn(
            Fragment fragment,
            String typeFragment,
            FragmentTransaction fragmentTransaction,
            Bundle bundleVal,
            Context context
    ) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (typeFragment.equals(context.getString(R.string._dbConnection))) {
            fragment = new Fragment_settings_dbConn();
            fragment.setArguments(bundleVal);
            fragmentTransaction.add(R.id.fragment_settings_dbConn, fragment);
        } else if (typeFragment.equals(context.getString(R.string._identificationUser))) {
            fragment = new Fragment_settings_dbConn();
            fragment.setArguments(bundleVal);
            fragmentTransaction.add(R.id.fragment_identificationUser, fragment);
        } else return false;
        fragmentTransaction.commit();
        return true;
    }

    private static boolean closeFragment(
            Fragment fragment,
            FragmentTransaction fragmentTransaction
    ) {
        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
        return false;
    }
}