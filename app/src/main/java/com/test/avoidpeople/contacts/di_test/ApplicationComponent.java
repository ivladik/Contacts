package com.test.avoidpeople.contacts.di_test;

import com.test.avoidpeople.contacts.api.ContactsApi;
import com.test.avoidpeople.contacts.di_test.modules.NetworkModule;
import com.test.avoidpeople.contacts.di_test.scopes.ApplicationScope;

import dagger.Component;

/**
 * Created by admin on 09.11.2017.
 */

@ApplicationScope
@Component(modules = { NetworkModule.class })
public interface ApplicationComponent {

    ContactsApi getContactsApi();
}
