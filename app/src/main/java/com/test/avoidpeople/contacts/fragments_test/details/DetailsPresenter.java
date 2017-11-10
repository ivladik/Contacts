package com.test.avoidpeople.contacts.fragments_test.details;

import android.content.Context;
import android.util.Log;


import com.test.avoidpeople.contacts.ProgressBarProvider;
import com.test.avoidpeople.contacts.api.ContactsApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 09.11.2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter<DetailsContract.View> {

    private static final String TAG = DetailsPresenter.class.getSimpleName();

    private DetailsContract.View view;

    private ProgressBarProvider progressBarProvider;
    private ContactsApi contactsApi;

    @Inject
    public DetailsPresenter(ContactsApi contactsApi, ProgressBarProvider progressBarProvider){
        this.contactsApi = contactsApi;
        this.progressBarProvider = progressBarProvider;
    }

    /*@Override
    public void getDetails() {

        Repository repository = view.getCurrentRepository();

        if(repository != null) {

            progressBarProvider.showProgressBar();

            contentCall = gitHubAPI.GetRepoContents(repository.getOwner().getLogin(), repository.getName());

            contentCall.enqueue(new Callback<List<Content>>() {
                @Override
                public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                    if(response.isSuccessful()) {
                        view.updateContentList(response.body());
                    }

                    progressBarProvider.hideProgressBar();
                }

                @Override
                public void onFailure(Call<List<Content>> call, Throwable t) {
                    Log.e(TAG, "Error calling GetRepoContents", t);
                    progressBarProvider.hideProgressBar();
                }
            });
        }
    }*/

    @Override
    public void start(Context context) {
//        getDetails();
    }

    @Override
    public void stop() {
        /*if(contentCall != null){
            contentCall.cancel();
        }*/
    }

    @Override
    public void setView(DetailsContract.View view) {
        this.view = view;
    }
}
