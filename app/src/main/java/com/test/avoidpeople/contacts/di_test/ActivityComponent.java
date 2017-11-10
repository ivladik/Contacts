package com.test.avoidpeople.contacts.di_test;

import com.test.avoidpeople.contacts.di_test.modules.PresenterModule;
import com.test.avoidpeople.contacts.di_test.scopes.ActivityScope;
import com.test.avoidpeople.contacts.fragments_test.ContactsFragment;
import com.test.avoidpeople.contacts.fragments_test.DetailsFragment;
import com.test.avoidpeople.contacts.fragments_test.contacts.ContactsPresenter;
import com.test.avoidpeople.contacts.fragments_test.details.DetailsPresenter;

import dagger.Component;

/**
 * Created by admin on 09.11.2017.
 */
@ActivityScope
@Component(modules = {PresenterModule.class},
        dependencies = {ApplicationComponent.class})
public interface ActivityComponent {
    void inject(ContactsFragment fragment);

    void inject(DetailsFragment fragment);

    ContactsPresenter repositoryPresenter();

    DetailsPresenter contactPresenter();
}
