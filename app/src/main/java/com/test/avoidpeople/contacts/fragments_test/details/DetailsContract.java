package com.test.avoidpeople.contacts.fragments_test.details;

import com.test.avoidpeople.contacts.models.Contact;
import com.test.avoidpeople.contacts.mvp.BasePresenter;
import com.test.avoidpeople.contacts.mvp.BaseView;

/**
 * Created by admin on 09.11.2017.
 */

public class DetailsContract {
    public interface Presenter<T extends BaseView> extends BasePresenter<View> {
//        void getDetails();
    }

    public interface View extends BaseView {
//        Contact getCurrentDetails();
    }
}
