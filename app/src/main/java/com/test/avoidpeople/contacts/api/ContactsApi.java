package com.test.avoidpeople.contacts.api;


import com.test.avoidpeople.contacts.models.Contact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by admin on 09.11.2017.
 */

public interface ContactsApi {
    @GET("users")
    Call<List<Contact>> getContacts(); // TODO: observable
}
