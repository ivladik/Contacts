package com.test.avoidpeople.contacts.fragments_test.contacts;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.test.avoidpeople.contacts.ProgressBarProvider;
import com.test.avoidpeople.contacts.api.ContactsApi;
import com.test.avoidpeople.contacts.models.Contact;
import com.test.avoidpeople.contacts.storage.Preferences;
import com.test.avoidpeople.contacts.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by admin on 09.11.2017.
 */

public class ContactsPresenter implements ContactsContract.Presenter<ContactsContract.View> {

    private static final String TAG = ContactsPresenter.class.getSimpleName();

    private ProgressBarProvider progressBarProvider;
    private ContactsApi contactsApi;
    private ContactsContract.View view;

    private Call<List<Contact>> listCall;
    private Context context;

    @Inject
    public ContactsPresenter(ContactsApi contactsApi,
                             ProgressBarProvider progressBarProvider){
        this.contactsApi = contactsApi;
        this.progressBarProvider = progressBarProvider;
    }

    @Override
    public void getContacts() {
        this.progressBarProvider.showProgressBar();
        Preferences preferences = new Preferences(context);

        if (NetworkUtils.isNetworkAvailable(context)) {

            listCall = contactsApi.getContacts();

            listCall.enqueue(new Callback<List<Contact>>() {
                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                    if (response.isSuccessful()) {
                        preferences.setList(response.body());
                        view.updateContactsList(response.body());
                    }

                    progressBarProvider.hideProgressBar();
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {
                    Log.e(TAG, "Error calling getContacts", t);

                    progressBarProvider.hideProgressBar();
                }
            });
        } else {
            List<Contact> list = preferences.getList();
            view.updateContactsList(list);
            progressBarProvider.hideProgressBar();
        }
    }

    @Override
    public void start(Context context) {
        this.context = context;
        getContacts();
    }

    @Override
    public void stop() {
        if(listCall != null){
            listCall.cancel();
        }
    }

    @Override
    public void setView(ContactsContract.View view) {
        this.view = view;
    }
}
