package com.test.avoidpeople.contacts.di_test.modules;


import android.content.Context;

import com.test.avoidpeople.contacts.ProgressBarProvider;
import com.test.avoidpeople.contacts.api.ContactsApi;
import com.test.avoidpeople.contacts.di_test.scopes.ActivityScope;
import com.test.avoidpeople.contacts.fragments_test.contacts.ContactsContract;
import com.test.avoidpeople.contacts.fragments_test.contacts.ContactsPresenter;
import com.test.avoidpeople.contacts.fragments_test.details.DetailsContract;
import com.test.avoidpeople.contacts.fragments_test.details.DetailsPresenter;
import com.test.avoidpeople.contacts.storage.Preferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 09.11.2017.
 */
@Module
public class PresenterModule {

    private ProgressBarProvider progressBarProvider;
    private Context context;
    public PresenterModule(ProgressBarProvider progressBarProvider, Context context){
        this.progressBarProvider = progressBarProvider;
        this.context = context;
    }

    @Provides
    @ActivityScope
    public ProgressBarProvider provideProgressBarProvider(){
        return progressBarProvider;
    }

    @Provides
    @ActivityScope
    public ContactsContract.Presenter provideContactsPresenter(ContactsApi contactsApi, Preferences preferences){
        return new ContactsPresenter(contactsApi, progressBarProvider, preferences);
    }

    @Provides
    @ActivityScope
    public DetailsContract.Presenter provideDetailsPresenter(ContactsApi contactsApi){
        return new DetailsPresenter(contactsApi, progressBarProvider);
    }

    @Provides
    @ActivityScope
    public Preferences providePreferences() {
        return new Preferences(context);
    }
}
