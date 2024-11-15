package com.ltq27.baotrimaylanh.activity.customer;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    private static final String PREF_NAME = "user_session";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_ROLE = "user_role";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public void saveUserRole(String role) {
        editor.putString(KEY_USER_ROLE, role);
        editor.apply();
    }

    public String getUserRole() {
        return sharedPreferences.getString(KEY_USER_ROLE, null);
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }
}