package com.test.avoidpeople.contacts.fragments_test.contacts;

import com.test.avoidpeople.contacts.models.Contact;
import com.test.avoidpeople.contacts.mvp.BasePresenter;
import com.test.avoidpeople.contacts.mvp.BaseView;

import java.util.List;

/**
 * Created by admin on 09.11.2017.
 */

public class ContactsContract {
    public interface Presenter<T extends BaseView> extends BasePresenter<View> {
        void getContacts();
    }

    public interface View extends BaseView {
        void updateContactsList(List<Contact> repositories);
        void onContactsItemClick(Contact item);
    }
}
