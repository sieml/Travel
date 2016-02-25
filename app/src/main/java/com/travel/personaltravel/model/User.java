package com.travel.personaltravel.model;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
  String  nickName;
    String    avatarSmall;
    String  level;
    String  userId;



    public static  User parseJson(String json)
    {
        User user=null;
        Gson gson=new Gson();
        try {
            JSONObject obj=new JSONObject(json);
            JSONObject obj2=obj.getJSONObject("result");
             user = gson.fromJson(obj2.toString(), User.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarSmall() {
        return avatarSmall;
    }

    public void setAvatarSmall(String avatarSmall) {
        this.avatarSmall = avatarSmall;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
