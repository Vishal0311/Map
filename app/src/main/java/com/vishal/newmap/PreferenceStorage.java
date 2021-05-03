package com.vishal.newmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceStorage {
    private SharedPreferences preferences;


    public PreferenceStorage(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }
    public String getString(String key, String defaultVale) {
        return preferences.getString(key, defaultVale);
    }
}
