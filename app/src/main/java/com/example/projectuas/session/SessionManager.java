package com.example.projectuas.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    //LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    //shared preferences
    public static SharedPreferences pref;

    public static SharedPreferences.Editor editor;
    public static Context _context;

    //shared pref mode
    int PRIVATE_MODE = 0;

    //shared pref file name;
    public static final String PREF_NAME= "dwiromadonskom";

    public static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_SKIP = "isSkip";
    private static final String KEY_SESSID = "Sessid";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setSkip(boolean isSkip) {

        editor.putBoolean(KEY_IS_SKIP, isSkip);

        // commit changes
        editor.commit();

        Log.d(TAG, "User skip login screen modified!");
    }

    public boolean isSkip(){
        return pref.getBoolean(KEY_IS_SKIP, false);
    }

    public void setSessid(Integer sessid) {

        editor.putInt(KEY_SESSID, sessid);

        // commit changes
        editor.commit();

        Log.d(TAG, "User session id modified!");
    }

    public Integer getSessid(){
        return pref.getInt(KEY_SESSID, 0);
    }
}