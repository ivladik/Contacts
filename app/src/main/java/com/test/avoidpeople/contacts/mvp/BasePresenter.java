package com.test.avoidpeople.contacts.mvp;

import android.content.Context;

/**
 * Created by admin on 09.11.2017.
 */

public interface BasePresenter<T extends BaseView> {
    void start(Context context);
    void stop();
    void setView(T view);
}
