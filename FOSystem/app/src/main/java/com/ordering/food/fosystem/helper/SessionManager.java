package com.ordering.food.fosystem.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ordering.food.fosystem.activity.LoginFragment;
import com.ordering.food.fosystem.activity.MainActivity;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "user";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)

    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_LNAME = "lname";

    public static final String KEY_FNAME = "fname";

    public static final String KEY_ID = "id";




    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email,String id){
        // Storing login value as TRUE

        System.out.println("-----Create LOGIN SESSION1-----");
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        //store id
        editor.putString(KEY_ID, id);

        // commit changes
        editor.commit();
    }

    public void createLoginSession(String fname, String lname){
        // Storing login value as TRUE

        System.out.println("-----Create LOGIN SESSION2-----");
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_FNAME, fname);

        // Storing email in pref
        editor.putString(KEY_LNAME, lname);

        // commit changes
        editor.commit();
        System.out.print("-------2-------");
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            System.out.println("-----INSIDE---");
            // user is not logged in redirect him to Login Fragment
            Intent i = new Intent(_context, LoginFragment.class);
            System.out.print("in check.......1....");
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        } else {
            System.out.print("in check.......2....");

        }
    }
    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        //user id
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        user.put(KEY_FNAME, pref.getString(KEY_FNAME, null));

        user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));

        // return user
        return user;
    }




    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN,false);
    }
}