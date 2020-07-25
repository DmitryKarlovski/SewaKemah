package com.app.sewakemah.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_USER_REGISTERED ="user", KEY_PASS_REGISTERED ="pass", KEY_EMAIL_REGISTERED ="email";
    static final String KEY_USERNAME_LOGGED_IN = "Username_logged_in";
    static final String KEY_EMAIL_LOGGED_IN = "Email_logged_in";
    static final String KEY_STATUS_LOGGED_IN = "Status_logged_in";
    static final String KEY_USER_FORGET = "username";
    static final String KEY_USER_ADDRESS = "address";
    static final String KEY_USER_PHONE = "phonenumber";
    static final String KEY_USER_PHOTO = "photo";
    static final String KEY_USER_STORE = "store";
    static final String KEY_USER_STORE_PHONE = "storePhone";
    static final String KEY_USER_STORE_ADDRESS = "storeAddress";
    static final String KEY_USER_STORE_PHOTO = "storePhoto";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /** Setter KEY_USER_REGISTERED username */
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_REGISTERED, username);
        editor.apply();
    }
    /** Getter KEY_USER_REGISTERED username */
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_REGISTERED,"");
    }


    /** Setter KEY_PASS_REGISTERED password */
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_REGISTERED, password);
        editor.apply();
    }
    /** Getter KEY_PASS_REGISTERED password */
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_REGISTERED,"");
    }


    /** Setter KEY_EMAIL_REGISTERED email */
    public static void setRegisteredEmail(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_EMAIL_REGISTERED, email);
        editor.apply();
    }
    /** Getter KEY_EMAIL_REGISTERED email */
    public static String getRegisteredEmail(Context context){
        return getSharedPreference(context).getString(KEY_EMAIL_REGISTERED,"");
    }


    /** Setter KEY_USER_ADDRESS address */
    public static void setKeyUserAddress(Context context, String addr){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_ADDRESS, addr);
        editor.apply();
    }
    /** Getter KEY_USER_ADDRESS address */
    public static String getKeyUserAddress(Context context) {
        return getSharedPreference(context).getString(KEY_USER_ADDRESS,"");
    }


    /** Setter KEY_USER_PHONE phonenumber */
    public static void setKeyUserPhone(Context context, String num){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_PHONE, num);
        editor.apply();
    }
    /** Getter KEY_USER_PHONE phonenumber */
    public static String getKeyUserPhone(Context context) {
        return getSharedPreference(context).getString(KEY_USER_PHONE,"");
    }


    /** Setter KEY_USER_PHOTO photo */
    public static void setKeyUserPhoto(Context context, String photo){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_PHOTO, photo);
        editor.apply();
    }
    /** Getter KEY_USER_PHOTO photo */
    public static String getKeyUserPhoto(Context context) {
        return getSharedPreference(context).getString(KEY_USER_PHOTO,"");
    }



    /** Setter KEY_USERNAME_LOGGED_IN */
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_LOGGED_IN, username);
        editor.apply();
    }
    /** Getter KEY_USERNAME_LOGGED_IN */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_LOGGED_IN,"");
    }

    /** Setter KEY_EMAIL_LOGGED_IN */
    public static void setKeyEmailLoggedIn(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_EMAIL_LOGGED_IN, email);
        editor.apply();
    }
    /** Getter KEY_EMAIL_LOGGED_IN */
    public static String getKeyEmailLoggedIn(Context context){
        return getSharedPreference(context).getString(KEY_EMAIL_LOGGED_IN,"");
    }

    /** Setter KEY_STATUS_LOGGED_IN */
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_LOGGED_IN,status);
        editor.apply();
    }
    /** Getter key KEY_STATUS_LOGGED_IN */
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_LOGGED_IN,false);
    }


    public static void setKeyUserForget(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_FORGET, username);
        editor.apply();
    }

    public static String getKeyUserForget(Context context){
        return getSharedPreference(context).getString(KEY_USER_FORGET,"");
    }


    /** Setter KEY_USER_STORE store */
    public static void setKeyUserStore(Context context, String storee){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_STORE, storee);
        editor.apply();
    }
    /** Getter KEY_USER_STORE store */
    public static String getKeyUserStore(Context context) {
        return getSharedPreference(context).getString(KEY_USER_STORE,"");
    }

    /** Setter KEY_USER_STORE_PHONE storePhone */
    public static void setKeyUserStorePhone(Context context, String storeePhone){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_STORE_PHONE, storeePhone);
        editor.apply();
    }
    /** Getter KEY_USER_STORE_PHONE storePhone */
    public static String getKeyUserStorePhone(Context context) {
        return getSharedPreference(context).getString(KEY_USER_STORE_PHONE,"");
    }

    /** Setter KEY_USER_STORE_ADDRESS storeAddress */
    public static void setKeyUserStoreAddress(Context context, String storeeAdd){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_STORE_ADDRESS, storeeAdd);
        editor.apply();
    }
    /** Getter KEY_USER_STORE_ADDRESS storeAddress */
    public static String getKeyUserStoreAddress(Context context) {
        return getSharedPreference(context).getString(KEY_USER_STORE_ADDRESS,"");
    }

    /** Setter KEY_USER_STORE_PHOTO storePhoto */
    public static void setKeyUserStorePhoto(Context context, String storeePhoto){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_STORE_PHOTO, storeePhoto);
        editor.apply();
    }
    /** Getter KEY_USER_STORE_PHOTO storePhoto */
    public static String getKeyUserStorePhoto(Context context) {
        return getSharedPreference(context).getString(KEY_USER_STORE_PHOTO,"");
    }



    /**Clear Logged In Data*/
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_LOGGED_IN);
        editor.remove(KEY_STATUS_LOGGED_IN);
        editor.apply();
    }

    public static void clearForgetPassUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USER_FORGET);
        editor.apply();
    }
}

