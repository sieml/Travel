package com.travel.personaltravel.model.searchresult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travel.personaltravel.model.ExpertInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/5 [16:45]
 */
public class SearchLocalExpert {

    //-----------------------------------
    //TODO 重写

    private String avatar;
    private String avatarSmall;
    private String birthday;
    private String gender;
    private String id;
    private String isBlocked;
    private int level;
    private String memo;
    private String nickName;
    private long userId;
    private String residence;
    private ExpertInfo expertInfo;


    public static List<SearchLocalExpert> parseJSON(String json) throws JSONException {

        List<SearchLocalExpert> ret = null;
        JSONObject jsonObject = new JSONObject(json);
        JSONArray array = jsonObject.getJSONArray("result");

        Gson gson = new Gson();
        TypeToken<List<SearchLocalExpert>> token = new TypeToken<List<SearchLocalExpert>>() {
        };
        ret = gson.fromJson(array.toString(), token.getType());
        return ret;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarSmall() {
        return avatarSmall;
    }

    public void setAvatarSmall(String avatarSmall) {
        this.avatarSmall = avatarSmall;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public ExpertInfo getExpertInfo() {
        return expertInfo;
    }

    public void setExpertInfo(ExpertInfo expertInfo) {
        this.expertInfo = expertInfo;
    }

    @Override
    public String toString() {
        return "CityDetailComment{" +
                "avatar='" + avatar + '\'' +
                ", avatarSmall='" + avatarSmall + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", isBlocked='" + isBlocked + '\'' +
                ", level=" + level +
                ", memo='" + memo + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userId=" + userId +
                ", residence='" + residence + '\'' +
                ", expertInfo=" + expertInfo +
                '}';
    }

}
