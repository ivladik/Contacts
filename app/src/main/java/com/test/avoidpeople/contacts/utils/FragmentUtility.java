package com.test.avoidpeople.contacts.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by admin on 09.11.2017.
 */

public class FragmentUtility {
    public static void goToFragment(FragmentManager fragmentManager,
                                    Fragment fragment,
                                    int containerId,
                                    boolean addToBackstack) {

        FragmentTransaction ft = fragmentManager.beginTransaction();

        String tag = fragment.getClass().getSimpleName();

        if (addToBackstack) {
            ft.addToBackStack(tag);
        }

        ft.replace(containerId, fragment, tag)
                .commit();
    }
}
