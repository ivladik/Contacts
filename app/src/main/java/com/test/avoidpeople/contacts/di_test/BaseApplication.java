package com.test.avoidpeople.contacts.di_test;

/**
 * Created by admin on 09.11.2017.
 */

import android.app.Application;
import android.content.Context;

import com.test.avoidpeople.contacts.di_test.modules.NetworkModule;

public class BaseApplication extends Application {
    ApplicationComponent applicationComponent;

    /**
     * Helper method to obtain SampleApplication class from context.
     * @param context
     * @return SampleApplication
     */
    public static BaseApplication getApplication(Context context){
        return (BaseApplication)context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Init dagger application component
        applicationComponent = DaggerApplicationComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();
    }

    /**
     * Helper method to facilitate testing
     * @param component
     */
    public void setComponent(ApplicationComponent component){
        this.applicationComponent = component;
    }
}
