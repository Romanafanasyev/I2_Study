package com.example.i2study;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //  Is user authorized
    public void setIsAuthorized(boolean isAuthorized){
        editor.putBoolean("KEY_IS_AUTHORIZED",isAuthorized);
        editor.commit();
    }

    public boolean getIsAuthorised(){
        return sharedPreferences.getBoolean("KEY_IS_AUTHORIZED", false);
    }

    // USER ID
    public void setUserId(Integer userId){
        editor.putInt("KEY_USERID",userId);
        editor.commit();
    }

    public Integer getUserId(){
        return sharedPreferences.getInt("KEY_USERID", 0);
    }

    // USER LOGIN
    public void setLogin(String login){
        editor.putString("KEY_LOGIN",login);
        editor.commit();
    }

    public String getLogin(){
        return sharedPreferences.getString("KEY_LOGIN", "");
    }


    // USER NAME
    public void setUsername(String username){
        editor.putString("KEY_USERNAME",username);
        editor.commit();
    }

    public String getUsername(){
        return sharedPreferences.getString("KEY_USERNAME", "");
    }


    // USER ROLE
    public void setRole(String role){
        editor.putString("KEY_ROLE",role);
        editor.commit();
    }

    public String getRole(){
        return sharedPreferences.getString("KEY_ROLE", "");
    }


    // USER GROUP ID
    public void setUserGroupId(Integer userGroupId){
        editor.putInt("KEY_USER_GROUP_ID",userGroupId);
        editor.commit();
    }

    public Integer getUserGroupId(){
        return sharedPreferences.getInt("KEY_USER_GROUP_ID", 0);
    }


    public void setUserGroupName(String userGroupName){
        editor.putString("KEY_USER_GROUP_NAME", userGroupName);
        editor.commit();
    }

    public String getUserGroupName(){
        return  sharedPreferences.getString("KEY_USER_GROUP_NAME", "");
    }

    public void setUserProfilePic(String userProfilePic){
        editor.putString("KEY_USER_PROFILE_PIC", userProfilePic);
        editor.commit();
    }

    public String getUserProfilePic(){
        return sharedPreferences.getString("KEY_USER_PROFILE_PIC", "");
    }

    // TO DO: finish get+set profile pic, get+set password
}
