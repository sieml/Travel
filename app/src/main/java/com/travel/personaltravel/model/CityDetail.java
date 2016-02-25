package com.travel.personaltravel.model;

import java.util.List;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/3
 * Time: 23:56
 * email:AndroidZern@163.com
 */
public class CityDetail {
    private String desc ;
    private String enName ;
    private String id ;
    private int imageCnt ;
    private List<CityDetailImg> images ;
    private boolean isFavorite ;
    private boolean isVote ;
    private CityDetailLocation location ;
    private String playGuide ;
    private String  shoppingTitles;
    private String  timeCostDesc;
    private String  travelMonth;
    private String  zhName;
    private int  voteCnt;
    private boolean  traveled;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImageCnt() {
        return imageCnt;
    }

    public void setImageCnt(int imageCnt) {
        this.imageCnt = imageCnt;
    }

    public List<CityDetailImg> getImages() {
        return images;
    }

    public void setImages(List<CityDetailImg> images) {
        this.images = images;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isVote() {
        return isVote;
    }

    public void setIsVote(boolean isVote) {
        this.isVote = isVote;
    }

    public CityDetailLocation getLocation() {
        return location;
    }

    public void setLocation(CityDetailLocation location) {
        this.location = location;
    }

    public String getPlayGuide() {
        return playGuide;
    }

    public void setPlayGuide(String playGuide) {
        this.playGuide = playGuide;
    }

    public String getShoppingTitles() {
        return shoppingTitles;
    }

    public void setShoppingTitles(String shoppingTitles) {
        this.shoppingTitles = shoppingTitles;
    }

    public String getTimeCostDesc() {
        return timeCostDesc;
    }

    public void setTimeCostDesc(String timeCostDesc) {
        this.timeCostDesc = timeCostDesc;
    }

    public String getTravelMonth() {
        return travelMonth;
    }

    public void setTravelMonth(String travelMonth) {
        this.travelMonth = travelMonth;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public int getVoteCnt() {
        return voteCnt;
    }

    public void setVoteCnt(int voteCnt) {
        this.voteCnt = voteCnt;
    }

    public boolean isTraveled() {
        return traveled;
    }

    public void setTraveled(boolean traveled) {
        this.traveled = traveled;
    }


    @Override
    public String toString() {
        return "CityDetail{" +
                "desc='" + desc + '\'' +
                ", enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", imageCnt=" + imageCnt +
                ", images=" + images +
                ", isFavorite=" + isFavorite +
                ", isVote=" + isVote +
                ", location=" + location +
                ", playGuide='" + playGuide + '\'' +
                ", shoppingTitles='" + shoppingTitles + '\'' +
                ", timeCostDesc='" + timeCostDesc + '\'' +
                ", travelMonth='" + travelMonth + '\'' +
                ", zhName='" + zhName + '\'' +
                ", voteCnt=" + voteCnt +
                ", traveled=" + traveled +
                '}';
    }


}
