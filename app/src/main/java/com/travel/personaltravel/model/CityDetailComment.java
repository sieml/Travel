package com.travel.personaltravel.model;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/4
 * Time: 10:23
 * email:AndroidZern@163.com
 */
public class CityDetailComment {
    private String avatar ;
    private String avatarSmall ;
    private String birthday ;
    private String gender ;
    private String id ;
    private String isBlocked ;
    private int level ;
    private String memo ;
    private String nickName ;
    private long userId ;
    private String residence ;
    private ExpertInfo expertInfo ;

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
