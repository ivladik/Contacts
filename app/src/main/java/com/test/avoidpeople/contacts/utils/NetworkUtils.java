package com.test.avoidpeople.contacts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import rx.Observable;

/**
 * Created by admin on 09.11.2017.
 */

public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public static Observable<Boolean> isNetworkAvailableObservable(Context context) {
        return Observable.just(isNetworkAvailable(context));
    }
}
