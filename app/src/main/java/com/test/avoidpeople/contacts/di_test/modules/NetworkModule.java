package com.test.avoidpeople.contacts.di_test.modules;

import com.google.gson.GsonBuilder;
import com.test.avoidpeople.contacts.api.ContactsApi;
import com.test.avoidpeople.contacts.di_test.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 09.11.2017.
 */

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(OkHttpClient client){

        GsonBuilder builder = new GsonBuilder(); // Set up the custom GSON converters

        return new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideHttpClient(HttpLoggingInterceptor logging){

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return logging;
    }

    @Provides
    @ApplicationScope
    ContactsApi provideGithubAPI(Retrofit retrofit){
        return retrofit.create(ContactsApi.class);
    }
}
