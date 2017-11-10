package com.test.avoidpeople.contacts.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.avoidpeople.contacts.models.Contact;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by User on 10.11.2017.
 */

public class Preferences {

    final static String FILE_NAME = "preferences";

    final static String PREF_CONTACT = "contact";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setList(List<Contact> list) {
        SharedPreferences.Editor editor = getEditor();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(PREF_CONTACT, json);
        editor.commit();

    }

    public List<Contact> getList() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Contact>>() {}.getType();
        String json = preferences.getString(PREF_CONTACT, "");
        return gson.fromJson(json, type);
    }
}
